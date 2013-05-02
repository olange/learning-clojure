;; 4Clojure Problem 19 «Last Element»
;; http://www.4clojure.com/problem/19

(rand-nth
  (list
    (fn [x] (first (reverse x)))
    (fn [x] (nth x (- (count x) 1)))
))