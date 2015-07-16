(ns session-4.core-test
  (:require [clojure.test :refer :all]
            [schema.core :as s]
            [schema.test :as st]
            [session-4.core :refer :all])
  (:import  (clojure.lang ExceptionInfo)))

(use-fixtures :once schema.test/validate-schemas)

(deftest set-winner-score-check
  (testing "Validation des scores d'un jeu: "
    (testing "leur somme doit être 6 au minimum"
      (is (thrown-with-msg? ExceptionInfo #"does not match schema: .*SumGreaterOrEqualTo6\?"
          (set-winner [1 3]))))
    (testing "chacun devrait être un nombre entier"
      (is (thrown-with-msg? ExceptionInfo #"does not match schema: .*integer\?"
          (set-winner [:not-a-number 7]))))))

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
