;; 4Clojure Problem 26 «Fibonacci Sequence»
;; http://www.4clojure.com/problem/46

(fn [n] (take n (
  (fn fibo-seq []
    ((fn fib [a b]
      (cons a (lazy-seq (fib b (+ a b)))))
      1 1)))))
