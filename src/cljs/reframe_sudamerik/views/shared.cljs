(ns reframe-sudamerik.views.shared
  (:require [re-frame.core :as re-frame]
            [re-com.core :as re-com]))

(defn link-to-section [[path name]]
  [re-com/hyperlink-href
   :label name
   :href path])

(defn nav-toolbar []
  [re-com/h-box
   :gap "1em"
   :children (map link-to-section [
         ["#/" "Inicio"]
         ["#/about" "Acerca de"]
         ["#/products" "Productos"]
         ["#/requests" "Pedidos"]])])
