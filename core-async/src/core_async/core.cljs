(ns core-async.core
	(:require-macros [cljs.core.async.macros :refer [go]])
	(:require [goog.dom :as dom]
						[goog.events :as events]
						[cljs.core.async :refer [put! chan <!]])
	(:import	[goog.net Jsonp]
          	[goog Uri]))

(def wiki-search-url
  "http://en.wikipedia.org/w/api.php?action=opensearch&format=json&search=")

(enable-console-print!)

(println "Hello world!")

(.log js/console (dom/getElement "query"))

(defn listen [el type]
  (let [out (chan)]
    (events/listen el type
      (fn [e] (put! out e)))
    out))

(let [clicks (listen (dom/getElement "search") "click")]
  (go (while true
        (.log js/console (<! clicks)))))

(defn jsonp [uri]
  (let [out (chan)
        req (Jsonp. (Uri. uri))]
    (.send req nil (fn [res] (put! out res)))
    out))

(defn query-url [q]
  (str wiki-search-url q))

(go (.log js/console (<! (jsonp (query-url "cats")))))
