;; 4Clojure Problem 92 «Read Roman numerals»
;; http://www.4clojure.com/problem/92

;; Take One

(fn roman
  ([s] (roman (seq s) 0))
  ([s v]
    (let [c1 (first s)
          c2 (second s)
          c12 (list c1 c2)]
      (if (empty? s) v
        (cond
          (= c12 '(\I \V)) (roman (drop 2 s) (+ v 4))
          (= c12 '(\I \X)) (roman (drop 2 s) (+ v 9))
          (= c12 '(\X \L)) (roman (drop 2 s) (+ v 40))
          (= c12 '(\X \C)) (roman (drop 2 s) (+ v 90))
          (= c12 '(\C \D)) (roman (drop 2 s) (+ v 400))
          (= c12 '(\C \M)) (roman (drop 2 s) (+ v 900))
          (= c1 \M) (roman (rest s) (+ v 1000))
          (= c1 \D) (roman (rest s) (+ v 500))
          (= c1 \C) (roman (rest s) (+ v 100))
          (= c1 \L) (roman (rest s) (+ v 50))
          (= c1 \X) (roman (rest s) (+ v 10))
          (= c1 \V) (roman (rest s) (+ v 5))
          (= c1 \I) (roman (rest s) (+ v 1))
        )
      )
    )
  )
)

;; Take Two

(fn roman
  ([s] (roman (seq s) 0))
  ([s v]
    (let [
          rdigits {\M 1000, \D 500, \C 100, \L 50, \X 10, \V 5, \I 1}
          sdigits {"IV" 4, "IX" 9, "XL" 40, "XC" 90, "CD" 400, "CM" 900}
          c1  (first s)
          c12 (str c1 (second s))
          v1  (get rdigits c1 0)
          v12 (get sdigits c12)]
      (if (empty? s) v
        (if (nil? v12)
          (roman (rest s) (+ v v1))
          (roman (drop 2 s) (+ v v12))
        )
      )
    )
  )
)

;; Take Three

(fn roman
  ([s] (roman (seq s) 0))
  ([s v]
    (let [rdigits {\M 1000, \D 500, \C 100, \L 50, \X 10, \V 5, \I 1}
          sdigits {"IV" 4, "IX" 9, "XL" 40, "XC" 90, "CD" 400, "CM" 900}
          c1  (first s)
          c12 (str c1 (second s))]
      (if (empty? s)
        v
        (if (contains? sdigits c12)
          (roman (drop 2 s) (+ v (get sdigits c12)))
          (roman (rest s) (+ v (get rdigits c1 0)))
        )
      )
    )
  )
)