(ns quiescent.dom
  (:refer-clojure :exclude [map mask meta time])
  (:require-macros [quiescent.dom :as dm])
  (:require [react]
            ["react-dom" :as rdom :refer [unstable_batchedUpdates]]
            [goog.object :as gobj]))

(defn js-props
  "Utility function. Takes an object which is (possibly) a
  ClojureScript map. If the value is a ClojureScript map, convert it
  to a JavaScript properties object. Otherwise, return the argument
  unchanged."
  [obj]
  (cond
    (nil? obj) nil

    (satisfies? IKVReduce obj)
    (reduce-kv (fn [o k v] (gobj/set o (name k) v) o) #js{} obj)

    (map? obj)
    (reduce (fn [o [k v]] (gobj/set o (name k) v) o) #js{} obj)

    :else obj))

(dm/define-tag-functions)

(defn render
  "`ReactDOM.render` wrapper"
  ([element mountpoint] (rdom/render element mountpoint))
  ([element mountpoint callback] (rdom/render element mountpoint callback)))

(defn unmountComponentAtNode
  "`ReactDOM.unmountComponentAtNode` wrapper"
  [mountpoint] (rdom/unmountComponentAtNode mountpoint))

(defn findDOMNode
  "`ReactDOM.findDOMNode` wrapper"
  [component] (rdom/findDOMNode component))

(def ^not-native batchedUpdates
  "The React batchUpdates addon. Takes one function which should call
  forceUpdate (possibly multiple times) inside it. This function will be called
  later and all components updated will have their forceUpdates reordered and
  applied in a single React transaction."
  unstable_batchedUpdates)
