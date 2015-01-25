(ns snake.bots-test
  (:use midje.sweet)
  (:require [snake.bots :as bots :refer :all]))

(fact "Neighbours of [4 5]"
	(bots/neighbours [4 5]) => nil)
