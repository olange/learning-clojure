(ns session-4.core-test
  (:require [clojure.test :refer :all]
            [session-4.core :refer :all]))

(deftest set-winner-is-a
  (testing "Gagnant du jeu est A"
    (is (= :a (set-winner [7 6])))))

(deftest set-winner-is-b
  (testing "Gagnant du jeu est B"
    (is (= :b (set-winner [3 6])))))

(deftest winner-is-a
  (testing "Gagnant de la partie est A"
    (is (= :a (winner [[6 4] [6 0] [6 2]])))))

(deftest winner-is-b
  (testing "Gagnant de la partie est B"
    (is (= :b (winner [[0 6] [6 3] [3 6] [5 7]])))))
