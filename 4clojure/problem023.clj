;; 4Clojure Problem 23 «Reverse a Sequence»
;; http://www.4clojure.com/problem/23

(fn rev
  ([l1] (rev l1 (list) ))
  ([l1 l2]
    (if (empty? l1) l2
      (recur (rest l1) (cons (first l1) l2))
  ))
)