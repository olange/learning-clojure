(defproject prismatic-schema "0.1.0-SNAPSHOT"
  :description "Learning to use [Prismatic Schema](https://github.com/Prismatic/schema)"
  :url "https://github.com/olange/learning-clojure/prismatic-schema"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [prismatic/schema "0.3.3"]]
  :main ^:skip-aot prismatic-schema.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
