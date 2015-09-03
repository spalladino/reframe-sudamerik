(ns reframe-sudamerik.views.home
  (:require [re-frame.core :as re-frame]
            [re-com.core :as re-com]))

(defn home-title []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [re-com/title
       :label "Bienvenido"
       :level :level1])))

(defn home-panel []
  [re-com/v-box
   :gap "1em"
   :children [[home-title]]])
