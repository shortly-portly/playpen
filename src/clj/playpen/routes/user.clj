(ns playpen.routes.user)

(def user-schema
  [[:first_name st/required st/string]
  [:last_name st/required st/string]
  [:email st/required st/email]])

(defn validate-message [params]
  "Validate the given parameters against the user schema"
  (first (st/validate params user-schema)))

(defn create-user! [{:keys [params]}]
  "Create a user record."
  (if-let [errors (validate-user params)]
    (-> (response/found "/"
                        (assoc :flash (assoc params :errors errors)))
        (do
          (db/save-user!
           (assoc params :timestamp (java.util.Date.)))
          (response/found "/")))))
