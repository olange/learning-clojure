(ns session-3.core
  (:gen-class))

(defn fibo-
  "Retourne le n-ième terme de la suite de Fibonnacci"
  [n]
  (if (<= n 1) n
      (+' (fibo (dec n))
          (fibo (dec (dec n))))))

(def fibo
  (memoize fibo-))

(defn fibo-suite
  "Retourne une séquence infinie de termes de la suite de Fibonnacci"
  []
  (defn fibnext [s]
    (cons (+' (first s) (second s)) s))
  (iterate fibnext [0 1]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
