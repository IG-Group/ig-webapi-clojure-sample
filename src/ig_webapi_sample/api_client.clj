(ns ig-webapi-sample.api-client
  (:require [clj-http.client :as client]
            [cheshire.core :as json]))

(def ^{:dynamic true} *clientToken* "")                     ; bind CST to a thread-local var
(def ^{:dynamic true} *accountToken* "")                    ; bind X-SECURITY-TOKEN to a thread-local var

(defn- add-sso-headers [headers]
  (let [sso-headers {"CST" *clientToken* "X-SECURITY-TOKEN" *accountToken*}]
    (conj headers sso-headers)))

(defn- get-url [href]
  (str "https://net-api.ig.com/gateway/deal" href))

(defn- wrap-create-api-request
  [client-fn]
  (fn [body headers]
    (let [request {:body         (json/encode body)
                   :headers      headers
                   :conn-timeout 10000
                   :content-type :json
                   :accept       :json
                   :as           :json
                   :insecure?    true}]
      (println (str "Creating request: " json/encode request))
      (client-fn request))))

(defn- wrap-enrich-api-response
  [client-fn]
  (fn [request]
    (let [response (client-fn request)]
      (assoc response :date (java.util.Date.)))))

;(defn- wrap-api-request
;  [request]
;  (-> request
;      wrap-create-api-request
;      wrap-enrich-api-response))

(defn- http-post
  "Send an HTTP POST request"
  [href body headers]
  (client/post (get-url href) body headers))

(defn- http-get
  "Send an HTTP GET request"
  [href headers]
  (client/get (get-url href) headers))

(def wrap-api-request
  (-> http-post
      wrap-create-api-request
      wrap-enrich-api-response))

; API calls
(defn login [username password apikey]
  (let [body {"identifier" username, "password" password, "encryptedPassword" false}
        headers {"X-IG-API-KEY" apikey, "VERSION" 2}]
    (wrap-api-request "/session" body headers)))

(defn getMarketData [epic apikey]
  (let [headers {"X-IG-API-KEY" apikey, "VERSION" 1}]
    (wrap-api-request (http-get (str "/markets/" epic) headers))))
