;; 4Clojure Problem 21 «Nth Element»
;; http://www.4clojure.com/problem/21

(fn [s n]
  (if (zero? n) (first s)
    (recur (rest s) (- n 1))))