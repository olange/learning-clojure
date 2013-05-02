;; 4Clojure Problem 126 «Through the Looking Class (Brain teaser)»
;; http://www.4clojure.com/problem/126

(let [x java.lang.Class]
  (and (= (class x) x) x))