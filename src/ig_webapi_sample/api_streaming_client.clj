(ns ig-webapi-sample.api-streaming-client
  (:import (com.lightstreamer.ls_client LSClient))
  (:import (com.lightstreamer.ls_client ConnectionInfo))
  (:import (com.lightstreamer.ls_client ExtendedConnectionListener)))

(defn- create-connection-info [context username lsendpoint]
  (let [cxInfo ConnectionInfo.]
    (.user cxInfo username)
    (.password cxInfo (str "CST-" (:CST context) "|XST-" (:XST context)))
    (.pushServerUrl cxInfo lsendpoint)
    cxInfo))

(defn- create-connection-listener [context]
  (nil))

(defn- login-request [context username lsendpoint]
  (let [lsclient (LSClient.)]
    (.openConnection lsclient (create-connection-info context username lsendpoint) (create-connection-listener context))
    lsclient))

(defn login [context username lsendpoint]
  (let [response (login-request context username lsendpoint)]
    (if (= (:status response) 200)
      {:content (:body response)
       :context {:CST         (:CST (:headers response))
                 :XST         (:X-SECURITY-TOKEN (:headers response))
                 :environment (:environment request)
                 :apikey      (:apikey request)}
       }
      )))