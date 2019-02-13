(ns breeze.quiescent.dom
  (:refer-clojure :exclude [map mask meta time])
  (:require-macros [breeze.quiescent.dom :as dm])
  (:require cljsjs.react
            cljsjs.react.dom
            [goog.object :as gobj]))

(defn- js-props-iter [^not-native it]
  (let [o #js{}]
    (while ^boolean (.hasNext it)
      (let [^not-native e (.next it)]
        (gobj/set o (-name ^not-native (-key e)) (-val e))))
    o))

(defn- js-props-rkv [m]
  (reduce-kv (fn [o k v] (gobj/set o (-name ^not-native k) v) o) #js{} m))

(defn- js-props-rm [m]
  (reduce (fn [o ^not-native e]
            (gobj/set o (-name ^not-native (-key e)) (-val e)) o) #js{} m))

(defn js-props
  "Takes an object which is (possibly) a ClojureScript map. If the value is a
  ClojureScript map, convert it to a JS object shallowly. Otherwise, return the
  argument unchanged.

  Used to convert maps to js before passing to react components that
  expect raw js objects."
  [obj]
  (cond
    (nil? obj) nil
    (map? obj) (cond
                 (iterable? obj)
                 (js-props-iter (iter obj))

                 (satisfies? IKVReduce obj)
                 (js-props-rkv obj)

                 :else
                 (js-props-rm obj))
    :else obj))

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
