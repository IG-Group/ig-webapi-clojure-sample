(ns ig-webapi-sample.client.login
  (:require [ig-webapi-sample.client.request :as request])
  (:require [clj-http.client :as client]))

(defn- generate-login-body [context]
  (client/json-encode (assoc {} :identifier (:identifier context) :password (:password context))))

(defn- generate-login-headers [context version]
  (assoc {} :X-IG-API-KEY (:apikey context)  :VERSION version))


(defn- login-request [context version]
  (client/post (request/get-url "/session" context) {
                                     :body (generate-login-body context)
                                     :headers (generate-login-headers context version)
                                     :conn-timeout 10000
                                     :content-type :json
                                     :accept       :json
                                     :as           :json
                                     :insecure?    true}
               ))

(defn login [context]
  (let [response (login-request context 2)]
    (if (= (:status response) 200)
      (assoc context :clientId (:clientId (:body response ))
                 :lightstreamerEndpoint (:lightstreamerEndpoint (:body response))
                 :currentAccountId (:currentAccountId (:body response))
                 :CST (:CST (:headers response))
                 :XST (:X-SECURITY-TOKEN (:headers response))))))
