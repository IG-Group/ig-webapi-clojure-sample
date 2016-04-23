(ns ig-webapi-sample.api-client
  (:require [ig-webapi-sample.client.request :as request])
  (:require [ig-webapi-sample.client.login :as login])
  (:require [clj-http.client :as client])
  (:import (ig_webapi_sample.client.login LoginRequest)))

(defn authenticate [username password apikey environment]
  (login/login (LoginRequest. username password apikey environment)))

(defn get-positions [context]
  (request/get-generator context "/positions" 2))

(defn get-working-orders [context]
  (request/get-generator context "/workingorders" 2))

(defn get-open-sprints [context]
  (request/get-generator context "/positions/sprintmarkets" 2))

(defn get-watchlists [context]
  (request/get-generator context "/watchlists" 1))

(defn get-accounts [context]
  (request/get-generator context "/accounts" 1))

(defn get-transactions [context type]
  (request/get-with-query-params-generator context "/history/transactions" 2 {"type" type }))

(defn get-applications [context]
  (request/get-generator context "/operations/application" 1))

(defn get-market [context epic]
  (request/get-generator context (str "/markets/" epic)  3))

(defn get-markets [context epics]
  (request/get-with-query-params-generator context "/markets"  2 {"epics" epics}))

(defn get-market-sentiment [context marketId]
  (request/get-generator context (str "/clientsentiment/" marketId) 1))

(defn find-epic [context search-term]
  (request/get-with-query-params-generator context "/markets" 1 {"searchTerm" search-term }))

(defrecord Otc-limit-order [direction epic size level expiry currencyCode forceOpen orderType])

(defn create-otc-order [context body]
  (request/post-generator context "/positions/otc" (client/json-encode body)  2))
