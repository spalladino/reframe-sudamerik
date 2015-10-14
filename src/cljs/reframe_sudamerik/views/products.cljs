(ns reframe-sudamerik.views.products
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]
            [re-com.core :as re-com]
            [clojure.string :as string]))

(defn products-title []
  [re-com/title
   :label "Productos"
   :level :level1])

(defn product-details []
  (let [product (re-frame/subscribe [:product-selected])]
    (fn []
      (if-not @product [:div]
        (let [{id :id, code :code, name :name, price :price, unit :unit, unitqty :unitqty} @product]
          [re-com/v-box :children
            [[re-com/title :label name :level :level2]
             [:span (str "Codigo " code ". " "$" price " por " unitqty unit ".")]
             [re-com/hyperlink :label "Cerrar" :on-click #(re-frame/dispatch [:product-selected nil])]]])))))

(defn products-filter []
  (let [filter-string (re-frame/subscribe [:products-filter])
        show-clear    (reaction (string/blank? @filter-string))]
  (fn []
    [re-com/h-box :gap "1em" :children
      [[re-com/input-text
        :model (or @filter-string "")
        :placeholder "Search for a product"
        :on-change #(re-frame/dispatch [:products-searched %])
        :change-on-blur? false]
       [re-com/hyperlink
        :label "Limpiar"
        :class (if @show-clear "hidden" "")
        :on-click #(re-frame/dispatch [:products-clear-search])]]])))

(defn product-item [{id :id, name :name, price :price, unit :unit}]
  [:li
    {:key id}
    [re-com/label
     :label name
     :on-click #(re-frame/dispatch [:product-selected id])]
  ])

(defn products-list []
  (let [items (re-frame/subscribe [:filtered-products])]
    (fn []
      [:ul
        (map product-item @items)])))

(defn products-panel []
  [re-com/v-box :gap "1em" :children
    [[products-title]
     [re-com/h-box :gap "10em" :children
        [[re-com/v-box :gap "1em" :children [[products-filter] [products-list]]]
         [product-details]]]]])
