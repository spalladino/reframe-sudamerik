(ns reframe-sudamerik.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]
              [clojure.string :as string]))

(re-frame/register-sub
 :name
 (fn [db]
   (reaction (:name @db))))

(re-frame/register-sub
 :active-panel
 (fn [db _]
   (reaction (:active-panel @db))))

(re-frame/register-sub
 :products
 (fn [db]
   (reaction (:products @db))))

(re-frame/register-sub
 :requests
 (fn [db]
   (reaction (:requests @db))))

(re-frame/register-sub
 :products-filter
 (fn [db]
   (reaction (:products-filter @db))))

(re-frame/register-sub
 :filtered-products
 (fn [db]
   (reaction
     (let [fstr  (:products-filter @db)
           prods (:products @db)
           in-f  (fn [f] (fn [{name :name}] (not= -1 (.indexOf (string/upper-case name) (string/upper-case f)))))]
       (if-not fstr prods (filter (in-f fstr) prods))))))

(re-frame/register-sub
 :product-selected
 (fn [db]
   (reaction
     (let [sel-id (:product-selected-id @db)
           prods  (:products @db)]
       (if-not sel-id nil (first (filter (fn [{id :id}] (= id sel-id)) prods)))))))
