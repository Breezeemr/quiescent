(ns quiescent.dom
  (:refer-clojure :exclude [map mask meta time])
  (:require-macros [quiescent.dom :as dm])
  (:require cljsjs.react
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
  ([element mountpoint] (js/React.render element mountpoint))
  ([element mountpoint callback] (js/React.render element mountpoint callback)))

(defn unmountComponentAtNode
  "`ReactDOM.unmountComponentAtNode` wrapper"
  [mountpoint] (js/React.unmountComponentAtNode mountpoint))

(defn findDOMNode
  "`ReactDOM.findDOMNode` wrapper"
  [component] (js/React.findDOMNode component))
