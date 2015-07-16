(ns prismatic-schema.core
  (:require [schema.core :as s]
            [prismatic-schema.my-model :as my-model])
  (:import  [prismatic_schema.my_model StampedNames]))

(s/defn stamped-names :- StampedNames
  [names :- [s/Str]]
  (StampedNames. (System/currentTimeMillis) names))

(s/with-fn-validation
  (stamped-names ["Olivier"]))
