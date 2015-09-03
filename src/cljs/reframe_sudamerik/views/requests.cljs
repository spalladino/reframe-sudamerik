(ns reframe-sudamerik.views.requests
  (:require [re-frame.core :as re-frame]
            [re-com.core :as re-com]))

(defn requests-title []
  [re-com/title
   :label "Pedidos"
   :level :level1])

(defn requests-list [] [:div])

(defn requests-form [] [:div])

(defn requests-panel []
  [re-com/v-box
   :gap "1em"
   :children [[requests-title] [requests-form] [requests-list]]])
