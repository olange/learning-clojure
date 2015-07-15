(defproject session-4 "0.1.0-SNAPSHOT"
  :description "Exercices de la session 4 du meetup (into Clojure)"
  :url "https://github.com/olange/learning-clojure/tree/master/into-clojure"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [prismatic/schema "0.4.3"]]
  :main ^:skip-aot session-4.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
