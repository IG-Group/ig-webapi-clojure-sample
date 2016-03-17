(ns ig-webapi-sample.api-client-test
  (:require [clojure.test :refer :all]
            [ig-webapi-sample.api-client :as apiclient]))

(deftest login-test
  (testing "Authenticate a user."
    (let [response (apiclient/authenticate "myuser" "mypassword" "myapikey" :test)]
      (is (= "myuser" (:identifier response)))
      (is (= "100092153" (:clientId response))))
    ))
