(ns ig-webapi-sample.api-sample
  (:use [clojure.core] :reload-all)
  (:require [ig-webapi-sample.api-client :as apiclient])
  (:gen-class :main true))

(defn -main [& args]
  (if (< (count args) 3)
    (println "Syntax: <username> <password> <api key>")
    (do
      (println "Authenticating...")
      (let [response (apiclient/login (first args) (second args) (get args 2))]
      (println (str "Response: " response)))
      )))
