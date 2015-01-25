(ns prismatic-schema.simple
  (:require [schema.core :as s]))

(def Data
  "A schema for a nested data type"
  {:a {:b s/Str
       :c s/Int}
   :d [{:e s/Keyword
        :f [s/Num]}]})

(defn case1 []
  (s/validate
    Data
    {:a {:b "abc"
         :c 123}
     :d [{:e :bc
          :f [12.2 13 100]}
         {:e :bc
          :f [-1]}]}))

(defn case2 []
  (s/validate
    Data
    {:a {:b 123
         :c "ABC"}}))

(defn various-cases []
  (s/validate s/Num 42)
  ;; 42

  (s/validate s/Num "42")
  ;; RuntimeException: Value does not match schema: (not (instance java.lang.Number "42"))

  (s/validate s/Keyword :whoa)
  ;; :whoa
  (s/validate s/Keyword 123)
  ;; RuntimeException: Value does not match schema: (not (keyword? 123))

  ;; On the JVM, you can use classes for instance? checks
  (s/validate java.lang.String "schema")

  ;; list of strings
  (s/validate [s/Str] ["a" "b" "c"])

  ;; nested map from long to String to double
  (s/validate {long {s/Keyword double}}
            {1 {:abc 3.0 :cde 5.0}}))