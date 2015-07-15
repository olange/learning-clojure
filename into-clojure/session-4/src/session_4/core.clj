(ns session-4.core
  "Identification des gagnants d'un jeu ou d'une partie de tennis"
  (:require [schema.core :as s])
  (:gen-class))

(def MinScore
  "A schema that validates that a number is >= 6"
  (s/pred #(>= % 6) 'GreaterOrEqualTo6?))
  ; 1. Un des deux doit être supérieur ou égal à 6
  ; 2. La différence est >= 2 pour le cas où le vainqueur a 6 jeux
  ; 3. La différence est exactement de 2 si le vainqueur a > 6 jeux
  ; 4. à l'exception du tie-break, où le score est de 7-6 ou 6-7.

(def SetScore
  "A schema for the score of a set"
  (s/both
    [ MinScore ]
    [ (s/one s/Int 'integer?) (s/one s/Int 'integer?) ]))

(def GameScore
  "A schema for the score of a set"
  [ SetScore ])

(def Winner
  "A schema for the winner of a set or game"
  (s/enum :a :b))

(s/defn set-winner :- Winner
  [[a b] :- SetScore]
  (if (> a b)
      :a :b))

(s/defn winner :- Winner
  [score :- GameScore]
  (let [sets-won-by (frequencies
                       (map set-winner score))]
    (if (> (sets-won-by :a 0)
           (sets-won-by :b 0))
       :a :b)))

(defn -main
  "Prints the winner of a game, that is expected to be given
  in the form [[6 0] [5 7] [5 2] …]"
  [& game-score]
  (s/with-fn-validation
    (let [score (winner game-score)]
      (println "Score of game" game-score "is" score))))
