(ns ig-webapi-sample.api-client-test
  (:require [clojure.test :refer :all]
            [ig-webapi-sample.api-client :refer :all]
            [cheshire.core :as json]))

(deftest login-test
  (testing "Authenticate a user."
    (is (= "{\"foo\":\"a\"}" (json/encode (login "lucasigm" "AbcAbc123" "9621d917dbc07c072cec9306805d067dec50e562"))))))
