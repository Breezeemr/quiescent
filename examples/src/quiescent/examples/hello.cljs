(ns quiescent.examples.hello
  (:require [clojure.browser.repl]
            [goog.dom :as dom]
            [quiescent :as q :refer-macros [defcomponent]]
            [quiescent.dom :as d]))

(defcomponent catapiller [{:keys [hi]}]
  (d/div nil hi))

(defn ^:export main
  []
  (d/render (catapiller {:hi "new"})
    (dom/getElement "react-root"))
  (js/alert "hello, world!"))

(main)
