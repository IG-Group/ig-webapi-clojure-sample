(ns ig-webapi-sample.api-client-test
  (:require [clojure.test :refer :all]
            [ig-webapi-sample.api-client :as apiclient]
            [ig-webapi-sample.client.login :as login]))

(deftest login-test
  (testing "Authenticate a user."
    (with-redefs [login/login-request (fn [request version] {:status   200
                                                             :headers {:CST "MY_CST_TOKEN"
                                                                       :XST "MY_XST_TOKEN"}
                                                             })]
      (let [response (apiclient/authenticate "myuser" "mypwd" "myapikey" :demo)]
        (is (= "MY_CST_TOKEN" (:CST (:context response))))
        )
      )))
