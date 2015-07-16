(defproject tron "0.1.0-SNAPSHOT"
  :description "Tron Geneva 2013 / Olivier"
  :url "https://github.com/cgrand/tron-geneva2013.git"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [quil "1.6.0"]
                 [org.jeromq/jeromq "0.2.0"]]

  :profiles {
  	:dev {
      :dependencies [[midje "1.5.1"]]
      :repl-options { :init-ns tron.bots }
  	}})
