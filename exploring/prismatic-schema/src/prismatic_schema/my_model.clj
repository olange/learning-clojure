(ns prismatic-schema.my-model
  (:require [schema.core :as s]))

(s/defrecord StampedNames
  [date :- Long
   names :- [s/Str]])

