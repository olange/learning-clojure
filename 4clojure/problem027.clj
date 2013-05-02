;; 4Clojure Problem 27 «Palindrome Detector»
;; http://www.4clojure.com/problem/27

(fn palindrome? [x]
  (= (seq x) (reverse x)))