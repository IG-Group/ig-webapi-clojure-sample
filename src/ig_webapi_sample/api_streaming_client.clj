(ns ig-webapi-sample.api-streaming-client
  (:require [ig-webapi-sample.lsclient.connection-listener-adapter :as connection-listener])
  (:import [com.lightstreamer.ls_client LSClient ConnectionInfo]))

(defn- create-connection-info [context username lsendpoint]
  (let [cxInfo (ConnectionInfo.)]
    (set! (.user cxInfo) username)
    (set! (.password cxInfo) (str "CST-" (:CST context) "|XST-" (:XST context)))
    (set! (.pushServerUrl cxInfo) lsendpoint)
    cxInfo))

(defn- create-connection-listener []
  (connection-listener/create))

(defn- connect [context username lsendpoint]
  (println ">>> Connecting to Lightstreamer with user=" username)
  (let [lsclient (LSClient.)]
    (.openConnection lsclient (create-connection-info context username lsendpoint) (create-connection-listener))
    lsclient))

(defn connect-new-thread [context username lsendpoint]
  (future (connect context username lsendpoint)))
