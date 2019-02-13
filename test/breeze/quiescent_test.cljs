(ns breeze.quiescent-test
  (:require [breeze.quiescent :as q]
            [breeze.quiescent.dom :as d]
            [cljsjs.react]))

(q/defcomponent custom-component [input]
  (d/h4 nil input))

(defn stuff []
  (d/div nil
         (d/h1 nil "React is working")
         (custom-component "components also work")))

(defn add-to-page! []
  (.render js/ReactDOM (stuff)
           (.getElementById js/document "main-area")))

(add-to-page!)
