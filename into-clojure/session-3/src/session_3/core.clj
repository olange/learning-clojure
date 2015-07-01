(ns session-3.core
  (:gen-class))

(declare fibo)

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
  (defn fibnext [[a b]]
    [b (+' a b)])
    (map first
      (iterate fibnext [0 1])))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (do
    (dorun
      (map println
        (for [i (range 11)]
          (format "(fibo %d): %d" i (fibo i)))))
    (println "(take 11 (fibo-suite)):\n"
              (take 11 (fibo-suite)))))
