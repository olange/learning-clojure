(defproject core-async "0.1.0-SNAPSHOT"
  :description "Learning core.async and ClojureScript, following ClojureScript 101 (http://swannodette.github.io/2013/11/07/clojurescript-101/)"
  :url "https://github.com/olange/learning-clojure/tree/master/core-async"

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2173"]
                 [org.clojure/core.async "0.1.256.0-1bf8cf-alpha"]]

  :plugins [[lein-cljsbuild "1.0.2"]]

  :source-paths ["src"]

  :cljsbuild { 
    :builds [{:id "core-async"
              :source-paths ["src"]
              :compiler {
                :output-to "core_async.js"
                :output-dir "out"
                :optimizations :none
                :source-map true}}]})
