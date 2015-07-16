(ns tron.core
  (:require [quil.core :as q])
  (:use [clojure.repl :only [pst]]))

(def size "size of the square arena" 60)
(def scale 10)
(def sleep-length "time in ms between turns" 100)

(def arena
  (mapv vec (partition size
              (repeatedly (* size size) #(ref nil)))))

(defn blank-arena []
  (dosync
    (doseq [row arena r row]
      (ref-set r nil))))

(defn setup []
  (q/color-mode :hsb)
  (q/smooth)
  (q/frame-rate 10))

(defn draw []
  (q/background 0)
  (dosync 
    (doseq [x (range 0 size)
            y (range 0 size)]
      (when-let [[hue b] (some-> arena (get-in [x y]) deref deref)]
        (q/fill (q/color hue 255 b))
        (q/rect (* scale x) (* scale y) scale scale)))))

(q/defsketch tron
  :title "TRON"
  :setup setup
  :draw draw
  :size [(* scale size) (* scale size)])

(defn valid-pos? [[i j]]
  (and (< -1 i size) (< -1 j size)))

(def legal-moves #{[0 1] [1 0] [0 -1] [-1 0]})

(defn valid-move? [from to]
  (contains? legal-moves (map - to from)))

(def ^:private bots-gen (atom 0))

(defn stop! [] (swap! bots-gen inc))

(defn- alive? [[hue b]] (when (= 255 b) hue))

(def ^:dynamic *mute-stdout* false)

(defn biker [arena strategy]
  (let [look (fn [pos] (if (valid-pos? pos)
                         (some-> arena (get-in pos) deref deref alive?)
                         :wall))
        gen @bots-gen] 
    (fn self [{:keys [state hueref sym] :as agt-state}]
	    (dosync
	      (let [t (java.lang.System/currentTimeMillis)
              state' (try 
                       (binding
                        [*out* (if *mute-stdout* 
                                 (java.io.FileWriter.
                                   "/dev/null")
                                 *out*)]
                        (strategy look state))
                       (catch Exception e
                         (pst e)))
              t (- (java.lang.System/currentTimeMillis) t)
              pos' (:pos state')
              moved (when (and (< t sleep-length) 
                            (valid-move? (:pos state) pos')
                            (nil? (look pos')))
                      (ref-set (get-in arena (:pos state')) hueref))]
         (if (and (= gen @bots-gen) moved)
	        (do
	          (Thread/sleep (- sleep-length t))
	          (send-off *agent* self)
	          (assoc agt-state :state state'))
	        (let [[hue] @hueref]
            (send-off (agent 255)
              (fn fadeout [b]
                (dosync
                  (let [b (- b 30)]
                    (if (pos? b) 
                      (do
                        (ref-set hueref [hue b])
                        (Thread/sleep 200)
                        (send-off *agent* fadeout)
                        b)
                      (ref-set hueref nil))))))
	          (println "arghhh" sym )
	          (assoc agt-state :dead true))))))))

(defn spawn-biker
  ([strategy]
    (spawn-biker strategy (rand-int 255)))
  ([strategy hue sym]
    (send-off (agent {:state {:pos [(rand-int size)
                                    (rand-int size)]}
                      :hueref (ref [hue 255])
                      :sym sym})
      (biker arena strategy))))

(defmacro run 
  "small macro to avoid repeating bots names"
  [& bots]
  `(doseq [[s# sym# hue#] 
           (map vector
             ~(vec bots)
             '~bots
             (iterate (partial + 25) 0))]
     (spawn-biker s# hue# sym#)))
