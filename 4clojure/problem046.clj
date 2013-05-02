;; 4Clojure Problem 46 «Flipping out»
;; http://www.4clojure.com/problem/46

(fn flip-fn-args [f]
  (fn [& rest] (apply f (reverse rest))))