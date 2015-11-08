(ns ig-webapi-sample.api-sample
  (:use [clojure.core]
        [clojure.pprint] :reload-all)
  (:require [ig-webapi-sample.api-client :as apiclient])
  (:gen-class :main true))

(defn -main [& args]
  (if (not= (count args) 4)
    (println "Syntax: <username> <password> <api key> [:test|:uat|:demo|:live]")
    (do
      (println ">>> Authenticating user" (first args))
      (pprint (apiclient/authenticate (first args) (second args) (nth args 2) (nth args 3)))

      (println ">>> Retrieving positions for accountId=" (:currentAccountId @apiclient/context))
      (let [response (apiclient/get-positions @apiclient/context)]
        (pprint response)))))
