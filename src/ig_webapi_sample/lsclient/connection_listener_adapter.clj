(ns ig-webapi-sample.lsclient.connection-listener-adapter
  (:use    [clojure.pprint] :reload-all)
  (:import [com.lightstreamer.ls_client ConnectionListener PushServerException PushConnException]))

(defn create []
  (reify
    ConnectionListener
    (onConnectionEstablished [this] (println "onConnectionEstablished"))
    (onSessionStarted [this value] (println "onSessionStarted" value))
    (onNewBytes [this value] ())
    (onDataError [this exception] (println "onDataError e=" exception))
    (onActivityWarning [this value] (println "onActivityWarning value=" value))
    (onClose [this] (println "onClose"))
    (onEnd [this value] (println "onEnd value=" value))
    ;(onFailure ^void [this ^PushServerException exception] (println "onServerFailure e=" exception))
    ;(onFailure [this ^PushConnException exception] (println "onConnFailure e=" exception))
    ))