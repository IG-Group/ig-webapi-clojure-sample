(ns ig-webapi-sample.api-sample
  (:use [clojure.core]
        [clojure.pprint] :reload-all)
  (:require [ig-webapi-sample.api-client :as apiclient])
  (:gen-class :main true))

(defn -main [& args]
  (if (not= (count args) 4)
    (println "Syntax: <username> <password> <api key> [:demo|:live]")
    (do
      (println ">>> Authenticating user" (first args))
      (let [response (apiclient/authenticate (first args) (second args) (nth args 2) (nth args 3))]
        (pprint (:content response))
        (println ">>> Retrieving positions for accountId=" (:currentAccountId (:content response)))
        (pprint (apiclient/get-positions (:context response)))
        ))))
