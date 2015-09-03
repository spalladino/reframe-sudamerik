(ns reframe-sudamerik.views
    (:require [re-frame.core :as re-frame]
              [re-com.core :as re-com]
              [reframe-sudamerik.views.about :as about]
              [reframe-sudamerik.views.home :as home]
              [reframe-sudamerik.views.products :as products]
              [reframe-sudamerik.views.requests :as requests]
              [reframe-sudamerik.views.shared :as shared]))

(defmulti panels identity)
(defmethod panels :home-panel [] [home/home-panel])
(defmethod panels :about-panel [] [about/about-panel])
(defmethod panels :products-panel [] [products/products-panel])
(defmethod panels :requests-panel [] [requests/requests-panel])
(defmethod panels :default [] [:div])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [re-com/v-box
       :height "100%"
       :children [[shared/nav-toolbar] (panels @active-panel)]])))
