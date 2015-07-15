(ns session-4.core
  "Identification des gagnants d'un jeu ou d'une partie de tennis"
  (:gen-class))

(defn set-winner
  [[a b]]
  (if (> a b)
      :a
      :b))

(defn winner
  [score]
  (let [sets-won-by (frequencies
                       (map set-winner score))]
    (if (> (sets-won-by :a 0)
           (sets-won-by :b 0))
       :a :b)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
