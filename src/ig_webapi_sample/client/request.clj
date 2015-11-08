(ns ig-webapi-sample.client.request
  (:require [clj-http.client :as client]))

(defrecord Context [identifier password apikey environment])

(defn- generate-headers [context version]
  (assoc {} :CST (:CST context)
            :X-SECURITY-TOKEN (:XST context)
            :X-IG-API-KEY (:apikey context)
            :VERSION version))

(defn- build-url [href env]
  (str "https://" env "api.ig.com/gateway/deal" href))

(defn get-url [href context]
  (cond
    (= :test (:environment context)) (build-url href "net-")
    (= :uat (:environment context)) (build-url href "web-")
    (= :demo (:environment context)) (build-url href "demo-")
    (= :live (:environment context)) (build-url href "")
    :default (throw (Throwable. "Unknown environment"))))

(defn get-generator [context href version]
  (client/get (get-url href context) {
                              :headers (generate-headers context version)
                              :conn-timeout 10000
                              :content-type :json
                              :accept       :json
                              :as           :json
                              :insecure?    true}))

(defn get-with-query-params-generator [context href version query-param-map]
  (client/get (get-url href context) {
                              :headers (generate-headers context version)
                              :query-params query-param-map
                              :conn-timeout 10000
                              :content-type :json
                              :accept       :json
                              :as           :json
                              :insecure?    true}))

(defn post-generator [context href body version]
  (client/post (get-url href context) {
                               :body body
                               :headers (generate-headers context version)
                               :conn-timeout 10000
                               :content-type :json
                               :accept       :json
                               :as           :json
                               :insecure?    true}))
