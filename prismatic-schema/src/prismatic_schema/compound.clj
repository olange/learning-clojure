(ns prismatic-schema.compound
  (:require [schema.core :as s]))

(s/defrecord Segment [
  rel :- {:type s/Str
          :data {(s/maybe s/Keyword) (s/maybe s/Str)}}
  partner :- { :noCli s/Str :noDos s/Str }])

(s/defrecord ParsedGraph [
  is-numeric? :- s/Bool
  owners :- [Segment]])

(defn case1 []
  (s/validate Segment (map->Segment {
    :rel { :type "BVM-KOLL" :data {}}
    :partner {:noCli "H1234" :noDos "00"}})))

(s/defn ->parsed-graph :- ParsedGraph
  [some-entity :- s/Any]
  (ParsedGraph. false [(Segment. {:type "withRelation" :data {}}
                {:noCli "H12345" :noDos "00"})]))
