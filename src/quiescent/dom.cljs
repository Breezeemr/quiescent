(ns quiescent.dom
  (:refer-clojure :exclude [map meta time])
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

#_(dm/define-tag-functions)
