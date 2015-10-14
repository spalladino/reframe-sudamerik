(ns reframe-sudamerik.handlers
    (:require [re-frame.core :as re-frame]
              [ajax.core :refer [GET]]
              [reframe-sudamerik.db :as db]
              [reframe-sudamerik.settings :as settings]))

; Globals

(re-frame/register-handler
  :initialize-db
  (fn  [_ _]
    (re-frame/dispatch [:products-request-load nil])
    db/default-db))

(re-frame/register-handler
  :set-active-panel
  (fn [db [_ active-panel]]
    (assoc db :active-panel active-panel)))


; Products

(re-frame/register-handler
  :products-request-load
  (fn [db _]
    (if-not
      (db :products-loading)
      (do
        (GET
          (str "http://" settings/host "/products")
          {:response-format :json
           :keywords?       true
           :handler         #(re-frame/dispatch [:products-load %1])
           :error-handler   #(re-frame/dispatch [:products-load-error %1])})
        (assoc db :products-loading true)))))

(re-frame/register-handler
  :products-load
  (fn [db [_ products]]
    (-> db
      (assoc :products products)
      (assoc :products-loading false))))

(re-frame/register-handler
  :products-load-error
  (fn [db [_ error]]
    (js/console.log "Error loading products" error)
    (assoc db :products-loading false)))

(re-frame/register-handler
  :products-searched
  (fn [db [_ pattern]]
    (assoc db :products-filter pattern)))

(re-frame/register-handler
  :products-clear-search
  (fn [db _]
    (assoc db :products-filter nil)))

(re-frame/register-handler
  :product-selected
  (fn [db [_ id]]
    (assoc db :product-selected-id id)))


; Requests

(re-frame/register-handler
  :new-request
  (fn [db [_ product amount]]
    (let [ requests (:requests db)
           new-request { :id (count requests), :amount amount, :product product}]
      (assoc db :requests (cons new-request requests)))))
