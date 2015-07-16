(ns prismatic-schema.core
  "Learning to use [Prismatic Schema](https://github.com/prismatic/schema),
  a Clojure(Script) library for declarative data description and validation.

  See also:
  * [Schema for Clojure(Script) Data Shape Declaration and Validation](http://blog.getprismatic.com/schema-for-clojurescript-data-shape-declaration-and-validation/)"
  (:require [schema.core :as s]
            [schema.utils :as su]
            [prismatic-schema.simple :as simple]
            [prismatic-schema.compound :as compound])
  (:import  [prismatic_schema.compound ParsedGraph]))

(defn -main
  "Validating a few datastructures"
  [& args]
  ;; (simple/case1)
  ;; (simple/case2)
  ;; (simple/various-cases)
  ;; (compound/case1)

  (println (s/explain ParsedGraph))

  (println (s/explain (s/fn-schema compound/->parsed-graph)))

  ;; And you can turn on validation to catch bugs in your functions and schemas
  (s/with-fn-validation
    (stamped-names ["Olivier"])))

(println (-main))
