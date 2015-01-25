(ns snake.core
  (:require [snake.bots :as bots])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Neighbours of [4 5]" (bots/neighbours [4 5])))
