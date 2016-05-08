(ns ig-webapi-sample.api-streaming-client
  (:require [ig-webapi-sample.lsclient.connection-listener-adapter :as connection-listener])
  (:require [ig-webapi-sample.lsclient.subscription-listener-adapter :as subscription-listener])
  (:import [com.lightstreamer.ls_client LSClient ConnectionInfo ExtendedTableInfo]))

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

(defn disconnect [lsclient]
  (.closeConnection lsclient))

(defn- subscribe [lsclient subscriptions listener items mode fields]
  (println ">>> Subscribing to Lightstreamer items=" (clojure.string/join "," items))
  (let [subscription-key (.subscribeTable lsclient (ExtendedTableInfo. items mode fields false) listener false)]
    (.add subscriptions subscription-key)
    ))

(defn subscribe-heartbeat [lsclient subscriptions]
  (subscribe lsclient subscriptions (subscription-listener/create-default-listener) (into-array String ["TRADE:HB.U.HEARTBEAT.IP"]) "MERGE" (into-array String ["HEARTBEAT"])))

(defn unsubscribe [lsclient subscription-key]
  (.forceUnsubscribeTable lsclient subscription-key))

(defn unsubscribe-all [lsclient subscriptions]
  (doseq [key subscriptions] (unsubscribe lsclient key)))
