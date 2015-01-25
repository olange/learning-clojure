(ns snake.bots)

(def  dirs #{[0 1] [0 -1] [-1 0] [1 0]})
(defn addv [a b] (mapv + a b))
(defn subv [a b] (mapv - a b))

(defn are-neighbours? [a b]
  (contains? dirs (subv a b)))

(defn neighbours [pos]
  (map #(addv pos %) dirs))

(defn valid-neighbours
  [look pos]
  (remove look (neighbours pos)))

(defn valid-path
  [look pos dir]
  (take-while #(look %)
  	          (iterate (partial addv pos) dir)))
