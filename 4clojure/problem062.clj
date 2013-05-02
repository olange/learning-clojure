;; 4Clojure Problem 62 «Re-implement Iterate»
;; http://www.4clojure.com/problem/62

;; Take 1 (noter que lazy-cat retourne toujours une liste;
;; et que son implémentation utilise lazy-seq)
(defn iter1 [f x] (lazy-cat [x] (iter f (f x) ) ) )

(= (take 5 (iter1 #(* 2 %) 1))
   [1 2 4 8 16])
(= (take 100 (iter1 inc 0))
   (take 100 (range)))
(= (take 9 (iter1 #(inc (mod % 3)) 1))
   (take 9 (cycle [1 2 3])))

;; Take 2 (implémentation actuelle de iterate en Clojure)
(defn iter2 [f x] (cons x (lazy-seq (iter f (f x) ) ) ) )

(= (take 5 (iter2 #(* 2 %) 1))
   [1 2 4 8 16])
(= (take 100 (iter2 inc 0))
   (take 100 (range)))
(= (take 9 (iter2 #(inc (mod % 3)) 1))
   (take 9 (cycle [1 2 3])))

;; Stack overflow
;; (defn iter [f x] (concat [x] [ (iter f (f x)) ] ) )

(= (take 5 (iter1 #(* 2 %) 1)) [1 2 4 8 16])
(= (take 5 (iter2 #(* 2 %) 1)) [1 2 4 8 16])
