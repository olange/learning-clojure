(ns session-3.core-test
  (:require [clojure.test :refer :all]
            [session-3.core :refer :all]))

(deftest fibo-test
  (testing "the first numbers of the Fibonnacci suite"
    (are [exp val] (= exp (fibo val))
        1 1
        1 2
        2 3
        3 4
        5 5
        8 6)))

(deftest fibo-suite-test
  (testing "the first 11 numbers of the Fibonnacci suite"
    (is (= (list 0 1 1 2 3 5 8 13 21 34 55)
           (take 11 (fibo-suite))))))
