# ig-webapi-clojure-sample

IG WebAPI Clojure sample
 This sample authenticates a user via the IG REST API and retrieves a list of positions.
 See api-sample.clj for further details.

To run in the REPL:
(require '[ig-webapi-sample.api-sample])
(ig-webapi-sample.api-sample/-main "myuser" "mypwd" "myapikey" :demo)

where :demo refers to the DEMO environment (use :live to connect to the LIVE environment)

API reference:
 http://labs.ig.com/
