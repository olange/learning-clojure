(ns session-3.core-test
  (:require [clojure.test :refer :all]
            [session-3.core :refer :all]))

(deftest fibo-test
  (testing "the first numbers of the Fibonnacci suite"
    (is (= 0 (fibo 0)))
    (is (= 1 (fibo 1)))
    (is (= 1 (fibo 2)))
    (is (= 2 (fibo 3)))
    (is (= 3 (fibo 4)))
    (is (= 5 (fibo 5)))
    (is (= 8 (fibo 6)))))

(deftest fibo-suite-test
  (testing "the first 11 numbers of the Fibonnacci suite"
    (is (= (list 0 1 1 2 3 5 8 13 21 34 55)
           (take 11 (fibo-suite))))))
