(ns reframe-sudamerik.views.requests
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [reagent.core  :as r]
            [re-frame.core :as re-frame]
            [re-com.core   :as rc]
            [reframe-sudamerik.utils :as utils]))

(defn requests-title []
  [rc/title
   :label "Pedidos"
   :level :level1])

(defn request-item [{id :id, amount :amount, product :product}]
  (let [{name :name, unit :unit} product]
    [rc/label :label (str
      name " " (utils/total-amount product amount) unit " "
      "$" (utils/total-price product amount))]))

(defn requests-list []
  (let [ requests (re-frame/subscribe [:requests]) ]
    (fn []
      [rc/v-box :gap "1px" :children (map request-item @requests)])))


(defn requests-form []
  (let [ products   (re-frame/subscribe [:products])
         product-id (r/atom nil)
         amount     (r/atom "1")
         product    (reaction (first (filter #(= @product-id (:id %)) @products)))]
    (fn []
      [rc/v-box :children [
        [rc/h-box :gap "1em" :children [
          [rc/single-dropdown
            :width "200px"
            :model product-id
            :on-change #(reset! product-id %)
            :filter-box? true
            :placeholder "Selecione un producto"
            :choices products
            :label-fn :name]
          [rc/input-text
            :width "80px"
            :model amount
            :on-change #(reset! amount %)
            :validation-regex #"^\d*(\.(\d*))?$"]
          [rc/label :label (str (:unit-qty @product) (:unit @product))]]]
        (if @product
          [rc/h-box :children [
            [rc/label :label (str
              "Total " (utils/total-amount @product @amount) (:unit @product) " "
              "$" (utils/total-price @product @amount))]
            [rc/md-circle-icon-button
              :md-icon-name "zmdi-plus"
              :size :smaller
              :tooltip "Agregar nuevo pedido"
              :on-click (fn []
                (re-frame/dispatch [:new-request @product @amount])
                (reset! product-id nil)
                (reset! amount "")
                true)]
          ]])]])))

(defn requests-new []
  [rc/v-box :children [
    [rc/title :label "Nuevo" :level :level2]
    [requests-form]
  ]])

(defn requests-panel []
  [rc/v-box
   :gap "1em"
   :children [[requests-title] [requests-list] [requests-new]]])
