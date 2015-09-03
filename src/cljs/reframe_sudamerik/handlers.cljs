(ns reframe-sudamerik.handlers
    (:require [re-frame.core :as re-frame]
              [reframe-sudamerik.db :as db]))

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/register-handler
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))

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

