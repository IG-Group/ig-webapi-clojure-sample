(ns ig-webapi-sample.api-sample
  (:use [clojure.core]
        [clojure.pprint] :reload-all)
  (:require [ig-webapi-sample.api-client :as apiclient])
  (:require [ig-webapi-sample.api-streaming-client :as api-streaming-client])
  (:import [java.util ArrayList])
  (:gen-class :main true))

(defn -main [& args]
  (if (not= (count args) 4)
    (println "Syntax: <username> <password> <api key> [:demo|:live]")
    (do
      (println ">>> Authenticating user" (first args))
      (let [response (apiclient/authenticate (first args) (second args) (nth args 2) (nth args 3))]
        (pprint (:content response))
        (let [lsclient-ref (api-streaming-client/connect-new-thread (:context response) (:currentAccountId (:content response)) (:lightstreamerEndpoint (:content response)))
              subscriptions (ArrayList.)]     ; List of subscription keys
          (api-streaming-client/subscribe-heartbeat @lsclient-ref subscriptions)
          (println ">>> Retrieving positions for accountId=" (:currentAccountId (:content response)))
          (pprint (apiclient/get-positions (:context response)))
          (println ">>> Retrieving market details")
          (pprint (apiclient/get-markets (:context response) "KA.D.3UKSLN.CASH.IP" ))
          (println ">>> Waiting 10 seconds")
          (Thread/sleep 10000)
          (api-streaming-client/unsubscribe-all @lsclient-ref subscriptions)
          (api-streaming-client/disconnect @lsclient-ref)
          )))))
