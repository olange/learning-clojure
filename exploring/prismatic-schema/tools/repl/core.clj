;; Anything you type in here will be executed
;; immediately with the results shown on the
;; right.

(require '[schema.core :as s]
         '[prismatic-schema.simple :as simple]
         '[prismatic-schema.compound :as compound])


(s/validate {long {s/Keyword double}}
            {1 {:abc 3.0 :cde 5.0}})


(s/defrecord Segment [
  rel :- {:type s/Str
          :data {s/Keyword s/Str}}
  partner :- { :noCli s/Str :noDos s/Str }])

(s/defrecord ParsedGraph [
  is-numeric? :- s/Bool
  owners :- [Segment]])

(s/validate Segment (map->Segment {
  :rel { :type "BVM-KOLL" :data {}}
  :partner {:noCli "H1234" :noDos "00"}}))


(s/defrecord StampedNames
  [date :- Long
   names :- [s/Str]])

(s/defn stamped-names :- StampedNames
  [names :- [s/Str]]
  (StampedNames. (System/currentTimeMillis) names))

(s/explain StampedNames)

(s/explain (s/fn-schema stamped-names))
(s/with-fn-validation
  (stamped-names ["Olivier" "Patrick"]))
