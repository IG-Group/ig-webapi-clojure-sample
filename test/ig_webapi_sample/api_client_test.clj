(ns ig-webapi-sample.api-client-test
  (:require [clojure.test :refer :all]
            [ig-webapi-sample.api-client :as apiclient]))

(deftest login-test
  (testing "Authenticate a user."
    (let [response (apiclient/authenticate "lucasigm" "AbcAbc123" "9621d917dbc07c072cec9306805d067dec50e562" :test)]
      (is (= "lucasigm" (:identifier response)))
      (is (= "100092153" (:clientId response))))
    ))
