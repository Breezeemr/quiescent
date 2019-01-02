(ns breeze.quiescent.dom
  (:refer-clojure :exclude [map mask meta time])
  (:require-macros [breeze.quiescent.dom :as dm])
  (:require cljsjs.react
            cljsjs.react.dom
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
  ([element mountpoint] (js/ReactDOM.render element mountpoint))
  ([element mountpoint callback] (js/ReactDOM.render element mountpoint callback)))

(defn unmountComponentAtNode
  "`ReactDOM.unmountComponentAtNode` wrapper"
  [mountpoint] (js/ReactDOM.unmountComponentAtNode mountpoint))

(defn findDOMNode
  "`ReactDOM.findDOMNode` wrapper"
  [component] (js/ReactDOM.findDOMNode component))

(def ^not-native batchedUpdates
  "The React batchUpdates addon. Takes one function which should call
  forceUpdate (possibly multiple times) inside it. This function will be called
  later and all components updated will have their forceUpdates reordered and
  applied in a single React transaction."
  js/ReactDOM.unstable_batchedUpdates)
