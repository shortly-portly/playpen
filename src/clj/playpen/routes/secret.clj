(ns playpen.routes.secret
  (:require
   [playpen.layout :as layout]
   [playpen.db.core :as db]
   [clojure.java.io :as io]
   [playpen.middleware :as middleware]
   [ring.util.response]
   [ring.util.http-response :as response]))

(defn secret-page [request]
  (layout/render request "home.html"))

(defn secret-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats
                 middleware/wrap-restricted]}
   ["/secret" {:get secret-page}]
   ])
