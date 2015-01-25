(ns prismatic-schema.compound
  (:require [schema.core :as s]))

(s/defrecord Segment [
  rel :- {:type s/Str
          :data {s/Keyword s/Str}}
  partner :- { :noCli s/Str :noDos s/Str }])

(s/defrecord ParsedGraph [
  is-numeric? :- s/Bool
  owners :- [Segment]])

(defn case1 []
  (s/validate Segment (map->Segment {
    :rel { :type "BVM-KOLL" :data {}}
    :partner {:noCli "H1234" :noDos "00"}})))

(s/defrecord StampedNames
  [date :- Long
   names :- [s/Str]])

(s/defn stamped-names :- StampedNames
  [names :- [s/Str]]
  ;; This would fail, because the String is not a Long:
  ;; (StampedNames. (str (System/currentTimeMillis)) names))
  ;;
  ;; ==> RuntimeException: Output of stamped-names does not match schema:
  ;;     {:date (not (instance? java.lang.Long "1378267311501"))}
  (StampedNames. (System/currentTimeMillis) names))
