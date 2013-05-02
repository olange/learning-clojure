# 4Clojure Problem 22 «Count a Sequence»
# http://www.4clojure.com/problem/22

(fn cnt
  ([l] (cnt l 0))
  ([l c] (if (empty? l) c
    (recur (rest l) (+ c 1))
  ))
)