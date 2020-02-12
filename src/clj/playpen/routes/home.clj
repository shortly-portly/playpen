(ns playpen.routes.home
  (:require
   [playpen.layout :as layout]
   [playpen.db.core :as db]
   [clojure.java.io :as io]
   [playpen.middleware :as middleware]
   [ring.util.response]
   [ring.util.http-response :as response]))

(defn home-page [request]
  (layout/render request "home.html"))

(defn home-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get home-page}]
   ["/docs" {:get (fn [_]
                    (-> (response/ok (-> "docs/docs.md" io/resource slurp))
                        (response/header "Content-Type" "text/plain; charset=utf-8")))}]
   ["/logon" {:get (fn [{:keys [session]}]
                     (-> {:status 200 :body "Thanks"}
                         (response/header "Content-Type" "text/plain; charset=utf-8")
                         (assoc :session (assoc session :identity "foo"))))}]

   ["/logoff" {:get (fn [{:keys [session]}]
                      (-> {:status 200 :body "Goodbye"}
                          (response/header "Content-Type" "text/plain; charset=utf-8")
                          (assoc :session (assoc session :identity nil))))}]])
