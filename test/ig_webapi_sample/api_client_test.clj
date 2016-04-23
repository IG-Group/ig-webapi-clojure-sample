(ns ig-webapi-sample.api-client-test
  (:require [clojure.test :refer :all]
            [ig-webapi-sample.api-client :as apiclient]))

(deftest login-test
  (testing "Authenticate a user."
    (let [response (apiclient/authenticate "myuser" "mypwd" "myapikey" :demo)]
      (is (= "100000000" (:clientId (:content response))))
      (is (= "CFD" (:accountType (:content response))))
      )))
