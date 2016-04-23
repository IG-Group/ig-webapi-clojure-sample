(defproject ig-webapi-clojure-sample "0.1.0-SNAPSHOT"
  :description "A sample application connecting to IG's Web API"
  :url "http://github.com/gutgal/ig-webapi-clojure-sample"
  :license {:name "BSD-3"
            :url "http://opensource.org/licenses/BSD-3-Clause"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                          [clj-http "2.0.0"]
                          [cheshire "5.5.0"]
                 [com.lightstreamer/sdk.client.java.se "5.1.1.1623.2"]]
  :main ig-webapi-sample.api-sample)
