(ns tron.bots
  (:require [tron.core :as tron]))

(defn empty-look 
  "A mock look function which just checks for the arena
   boundaries."
  [pos]
  (when-not (tron/valid-pos? pos) :wall))

(defn mock-look 
  "Returns a mock look function which checks for the arena
   boundaries and the specified occupied positions."
  [& occupied-poses]
  (let [occupied-poses (set occupied-poses)]
    (fn [pos]
      (or (occupied-poses pos)
        (when-not (tron/valid-pos? pos) :wall)))))

(def dirs #{[0 1] [0 -1] [-1 0] [1 0]})

(defn addv [a b] (mapv + a b))

(defn subv [a b] (mapv - a b))

(defn buzz 
  "To infinity and beyond!"
  [look {[x y] :pos}]
  {:pos [(inc x) y]})

(defn right [[x y]]
  [(inc x) y])

(defn down [[x y]]
  [x (inc y)])

(defn down-or-right
  [look {pos :pos}]
  (if (look (down pos))
    {:pos (right pos)}
    {:pos (down pos)}))

(defn are-neighbours? [a b]
  (contains? dirs (subv a b)))

(defn neighbours
  "returns a seq or coll of the 4 neighbours of the current
   pos, irrespective of whether these positions are valid o
   free."
  [pos]
  ;; {:post [(every? are-neighbours? %)]}
  (map #(addv pos %) dirs))

(defn valid-neighbours
  "returns a seq or coll of the neighbours of the current
   pos that are free to go (ie look returns nil for those)."
  [look pos]
  ;; {:post [(not-any? look %) (every? are-neighbours? %)]}
  (remove look (neighbours pos)))

(defn valid-path
  "returns a seq of the all the next positions that are free to
  go in the given direction until one occupied one is reached."
  [look pos dir]
  ;; {:post [(not-any? look %) (every? (fn [[a b]] (= dir (subv b a))) (partition 2 1 %))]}
  (take-while (complement look)
              (next (iterate (partial addv dir) pos))))

(defn valid-paths
  [look pos]
  (filter seq
          (map (partial valid-path look pos) dirs)))

(defn shortest
  [look {pos :pos :as game}]
  (let [all-valid-paths (valid-paths look pos)]
    (when (seq all-valid-paths)
      (let [shortest-path (apply min-key count all-valid-paths)]
        (assoc game
               :pos (first shortest-path))))))

(defn longest
  [look {pos :pos :as game}]
  (let [all-valid-paths (valid-paths look pos)]
    (when (seq all-valid-paths)
      (let [longest-path (apply max-key count all-valid-paths)]
        (assoc game
               :pos (first longest-path))))))

(defn fuzzy
  [look {pos :pos :as game}]
  (assoc game
         :pos (first (shuffle (valid-neighbours look pos)))))

(defn mixin
  [look {pos :pos strategy :strategy :as game}]
  (cond
    (nil? strategy)
      (shortest look
                (assoc game :strategy :to-border))
    (= strategy :to-border)
      (let [new-game (shortest look game)]
        (if (nil? (:pos new-game))
          (longest look
                    (assoc game :strategy :longest))
          new-game))
    (= strategy :longest)
          (longest look game)))

;; launch bots
(println "(tron/run)")
(tron/run mixin)
; (tron/run mixin fuzzy shortest longest buzz down-or-right)
