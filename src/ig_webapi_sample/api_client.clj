(ns ig-webapi-sample.api-client
  (:require [ig-webapi-sample.client.request :as request])
  (:require [ig-webapi-sample.client.login :as login])
  (:require [clj-http.client :as client])
  (:import (ig_webapi_sample.client.request Context)))

(defn authenticate [username password apikey environment]
  (login/login (Context. username password apikey environment)))

(defn get-positions [app]
  (request/get-generator app "/positions" 2))

(defn get-working-orders [app]
  (request/get-generator app "/workingorders" 2))

(defn get-open-sprints [app]
  (request/get-generator app "/positions/sprintmarkets" 2))

(defn get-watchlists [app]
  (request/get-generator app "/watchlists" 1))

(defn get-accounts [app]
  (request/get-generator app "/accounts" 1))

(defn get-transactions [app]
  (request/get-generator app "/history/transactions" 2))

(defn get-applications [app]
  (request/get-generator app "/operations/application" 1))

(defn get-epics [app epic]
  (request/get-generator app (str "/markets/" epic)  3))

(defn get-market-sentiment [app marketId]
  (request/get-generator app (str "/clientsentiment/" marketId) 1))

(defn find-epic [app search-term]
  (request/get-with-query-params-generator app "/markets" 1 {"searchTerm" search-term }))

(defrecord Otc-limit-order [direction epic size level expiry currencyCode forceOpen orderType])

(defn create-otc-order [app body]
  (request/post-generator app "/positions/otc" (client/json-encode body)  2))
