(defrecord Cell [x y])

(defn neighbours [cell]
  (for [dx [-1 0 1]
        dy (if (zero? dx)
              [-1 1]
              [-1 0 1])]
    (Cell. (+ (:x cell) dx) (+ (:y cell) dy))))

    ; (for [[cell n] (doto (frequencies (mapcat neighbours cells))
    ;                      prn)

(defn step [cells]
  (set
    (for [[cell n] (frequencies (mapcat neighbours cells))
      :when (or (= n 3) (and (= n 2) (cells cell)))]
      cell)))

(def world
  (set (take 200
    (repeatedly (fn [] (Cell. (rand-int 20) (rand-int 20)))))))

(dotimes [_ 5]
  (time (nth (iterate step world) 1000)))

