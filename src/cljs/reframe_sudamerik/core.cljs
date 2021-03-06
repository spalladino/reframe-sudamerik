(ns reframe-sudamerik.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [reframe-sudamerik.handlers]
              [reframe-sudamerik.subs]
              [reframe-sudamerik.routes :as routes]
              [reframe-sudamerik.views :as views]))

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init [] 
  (routes/app-routes)
  (re-frame/dispatch-sync [:initialize-db])
  (mount-root))
