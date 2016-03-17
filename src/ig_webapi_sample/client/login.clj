(ns ig-webapi-sample.client.login
  (:require [ig-webapi-sample.client.request :as request])
  (:require [clj-http.client :as client]))

(defrecord LoginRequest [identifier password apikey environment])

(defn- generate-login-body [request]
  (client/json-encode (assoc {} :identifier (:identifier request) :password (:password request))))

(defn- generate-login-headers [request version]
  (assoc {} :X-IG-API-KEY (:apikey request) :VERSION version))

(defn- login-request [request version]
  (client/post (request/get-url "/session" request) {
                                                     :body         (generate-login-body request)
                                                     :headers      (generate-login-headers request version)
                                                     :conn-timeout 10000
                                                     :content-type :json
                                                     :accept       :json
                                                     :as           :json
                                                     :insecure?    true}))

(defn login [request]
  (let [response (login-request request 2)]
    (if (= (:status response) 200)
      {:content (:body response)
        :context {:CST         (:CST (:headers response))
                  :XST         (:X-SECURITY-TOKEN (:headers response))
                  :environment (:environment request)
                  :apikey (:apikey request)}}
        )))
