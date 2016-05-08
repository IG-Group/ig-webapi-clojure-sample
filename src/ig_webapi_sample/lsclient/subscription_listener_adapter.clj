(ns ig-webapi-sample.lsclient.subscription-listener-adapter
  (:use    [clojure.pprint] :reload-all)
  (:import [com.lightstreamer.ls_client HandyTableListener]))

(defn create-default-listener []
  (reify
    HandyTableListener
    (onUpdate [this position name update-info] (println "onUpdate item=" name " info=" (.toString update-info)))
    (onUnsubscr [this position name] (println "onUnscribe item=" name))
    (onUnsubscrAll [this] (println "onUnsubscribeAll"))
    ))
