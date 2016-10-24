(ns quiescent
  (:require cljsjs.react)
  (:require-macros [quiescent :as q :refer [wrapped-lifecycle-method]]))

(def ^:dynamic *component*
  "Within a component render function, will be bound to the raw
  ReactJS component." nil)

(def ^:private no-this-state #js{})
(def ^:private no-next-state #js{})

(defn- default-shouldComponentUpdate [next-props next-state]
  (or (not= (aget (.-props *component*) "value") (aget next-props "value"))
    (let [this-state (if (nil? (.-state *component*))
                       no-this-state
                       (.-state *component*))
          next-state (if (nil? next-state)
                       no-next-state
                       next-state)]
      (not= (aget this-state "value") (aget next-state "value")))))

(defn- assemble-props [value [fsa & rsa :as static-args]]
  (let [props #js {:value value :statics static-args}]
    (when-some [key (get fsa :react/key)]
      (aset props "key" key))
    (when-some [ref (get fsa :react/ref)]
      (aset props "ref" ref))
    (when-not (and (undefined? (aget props "key"))
                (undefined? (aget props "ref")))
      (aset props "statics"
        (conj rsa (dissoc fsa :react/key :react/ref))))
    props))

(defn component
  "Return a function that will return a ReactJS component, using the
  provided function as the implementation for React's 'render' method
  on the component.

  The given render function should take a single immutable value as
  its first argument, and return a single ReactJS component.
  Additional arguments to the component constructor will be passed as
  additional arguments to the render function whenever it is invoked,
  but will *not* be included in any calculations regarding whether the
  component should re-render."
  [renderer]
  (let [m (meta renderer)
        react-map #js {:shouldComponentUpdate (wrapped-lifecycle-method
                                                (default-shouldComponentUpdate
                                                  (aget args 0) (aget args 1)))
                       :render                (wrapped-lifecycle-method
                                                (apply (if-some [wrap-render (:wrap-render m)]
                                                         (wrap-render renderer)
                                                         renderer)
                                                  (aget (. *component* -props) "value")
                                                  (aget (. *component* -props) "statics")))}]
    (when-some [n (or (:displayName m) (not-empty (.-name renderer)))]
      (set! (.-displayName react-map) n))
    (when-some [f (:getInitialState m)]
      (set! (.-getInitialState react-map)
        (wrapped-lifecycle-method #js {:value (f)})))
    (when-some [f (:getDefaultProps m)]
      (set! (.-getDefaultProps react-map)
        (wrapped-lifecycle-method (. f apply *component* args))))
    (when-some [f (:componentWillMount m)]
      (set! (.-componentWillMount react-map)
        (wrapped-lifecycle-method (. f apply *component* args))))
    (when-some [f (:componentDidMount m)]
      (set! (.-componentDidMount react-map)
        (wrapped-lifecycle-method (. f apply *component* args))))
    (when-some [f (:componentWillReceiveProps m)]
      (set! (.-componentWillReceiveProps react-map)
        (wrapped-lifecycle-method (. f apply *component* args))))
    (when-some [f (:shouldComponentUpdate m)]
      (set! (.-shouldComponentUpdate react-map)
        (wrapped-lifecycle-method (. f apply *component* args))))
    (when-some [f (:componentWillUpdate m)]
      (set! (.-componentWillUpdate react-map)
        (wrapped-lifecycle-method (. f apply *component* args))))
    (when-some [f (:componentDidUpdate m)]
      (set! (.-componentDidUpdate react-map)
        (wrapped-lifecycle-method (. f apply *component* args))))
    (when-some [f (:componentWillUnmount m)]
      (set! (.-componentWillUnmount react-map)
        (wrapped-lifecycle-method (. f apply *component* args))))
    (let [react-component (.createClass js/React react-map)
          q-wrapper (fn [value & statics]
                      (.createElement js/React react-component (assemble-props value statics)))]
      (when-some [displayName (.-displayName react-map)]
        (set! (.-displayName q-wrapper) displayName))
      (when-some [example (:exampleArg m)]
        (set! (.-exampleArg q-wrapper) example))
      (when-some [doc (:doc m)]
        (set! (.-doc q-wrapper) doc))
      (when-some [wrapper (:exampleContext m)]
        (set! (.-exampleContext q-wrapper) wrapper))
      q-wrapper)))

(defn set-state
  "Set the \"value\" key of a component's state."
  ([value] (set-state *component* value))
  ([component value]
    (.setState component #js {:value value})))

(defn get-state
  "Get the \"value\" key of a component's state."
  ([] (get-state *component*))
  ([component] (aget (.-state component) "value")))

(defn swap-state
  "Swap the \"value\" key of a component's state."
  ([tx-fn] (swap-state *component* tx-fn))
  ([component tx-fn]
    ;; TODO: setstate callback fn
    ;; TODO: swap-state tx-fn accepts old-props as second arg?
   (.setState component (fn [s _]
                          (let [old-state (aget s "value")
                                new-state (tx-fn old-state)]
                            (if (= old-state new-state)
                              #js{}
                              #js{:value new-state}))))))

(defn get-props
  "Get the render args from a component's props."
  ([] (get-props *component*))
  ([component] [(aget (.-props component) "value")
                (aget (.-props component) "statics")]))
