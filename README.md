# ig-webapi-clojure-sample
[![Build Status](https://travis-ci.org/IG-Group/ig-webapi-clojure-sample.svg?branch=master)](https://travis-ci.org/IG-Group/ig-webapi-clojure-sample)

IG WebAPI Clojure sample
 This sample authenticates a user via the IG REST API, subscribes to Lightstreamer heartbeat messages and retrieves a list of positions and market data.
 See api-sample.clj for further details.

To run in the REPL:
(require '[ig-webapi-sample.api-sample])
(ig-webapi-sample.api-sample/-main "myuser" "mypwd" "myapikey" :demo)

where :demo refers to the DEMO environment (use :live to connect to the LIVE environment)

API reference:
 http://labs.ig.com/
