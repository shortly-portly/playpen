(ns playpen.routes.user)

(defn create-user! [{:keys [params]}]
  "Create a user record."
  (if-let [errors (validate-user params)]
    (-> (response/found "/"
                        (assoc :flash (assoc params :errors errors)))
        (do
          (db/save-user!
           (assoc params :timestamp (java.util.Date.)))
          (response/found "/")))))
