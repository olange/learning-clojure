(ns prismatic-schema.core
  "Learning to use [Prismatic Schema](https://github.com/prismatic/schema),
  a Clojure(Script) library for declarative data description and validation.

  See also:
  * [Schema for Clojure(Script) Data Shape Declaration and Validation](http://blog.getprismatic.com/schema-for-clojurescript-data-shape-declaration-and-validation/)"
  (:gen-class)
  (:require [prismatic-schema.simple :as simple]
            [prismatic-schema.compound :as compound]
            [schema.core :as s]))
  ;; (:import  [prismatic-schema.compound.StampedNames :as StampedNames]))

(defn -main
  "Validating a few datastructures"
  [& args]
  ;; (simple/case1)
  ;; (simple/case2)
  ;; (simple/various-cases)
  ;; (compound/case1)

  ;; (s/explain StampedNames)
  ;; ==> (record user.StampedNames {:date java.lang.Long, :names [java.lang.String]})

  ;; (s/explain (s/fn-schema compound/stamped-names))
  ;; ==> (=> (record user.StampedNames {:date java.lang.Long, :names [java.lang.String]})
  ;;         [java.lang.String])

  ;; And you can turn on validation to catch bugs in your functions and schemas
  (s/with-fn-validation
    (compound/stamped-names ["Olivier"]))
)

(-main)
