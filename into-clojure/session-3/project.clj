(defproject session-3 "0.1.0-SNAPSHOT"
  :description "Exercices de la session 3 du meetup (into Clojure)"
  :url "https://github.com/olange/learning-clojure/tree/master/into-clojure"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :main ^:skip-aot session-3.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
