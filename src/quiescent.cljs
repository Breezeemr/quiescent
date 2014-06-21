(ns quiescent
  (:require-macros [quiescent :as q]
                   [om.core :as om]))

(def ^:dynamic *component*
  "Within a component render function, will be bound to the raw
  ReactJS component." nil)

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
       get-initial-state (:getInitialState m)
         react-map
         (cond-> #js {:shouldComponentUpdate
                       (fn [next-props next-state]
                         (om/allow-reads
                           (this-as this
                                    (or
                                      (not= (aget (.-props this) "value")
                                            (aget next-props "value"))
                                      (let [this-state (or (.-state this) (js-obj))
                                            next-state (or next-state (js-obj))]
                                        (not= (aget this-state "value")
                                              (aget next-state "value")))))))
                      :render
                       (fn []
                         (om/allow-reads
                           (this-as this
                                    (binding [*component* this]
                                      (apply renderer
                                             (aget (.-props this) "value")
                                             (aget (.-props this) "statics"))))))}
                 (or (:displayName m) (not-empty (.-name renderer)))
                 (q/set-prop! -displayName (or (:displayName m)
                                               (not-empty (.-name renderer))))
                 (:getInitialState m)
                 (q/set-prop! -getInitialState
                              (fn []
                                (om/allow-reads
                                  (this-as this
                                           (binding [*component* this]
                                             #js {:value (get-initial-state)})))))
                 (:getDefaultProps m)
                 (q/set-prop! -getDefaultProps (fn [] (om/allow-reads (.apply (:getDefaultProps m) (js-this) (js-arguments)))))
                 (:componentWillMount m)
                 (q/set-prop! -componentWillMount (fn [] (om/allow-reads (.apply (:componentWillMount m) (js-this) (js-arguments)))))
                 (:componentDidMount m)
                 (q/set-prop! -componentDidMount (fn [] (om/allow-reads (.apply (:componentDidMount m) (js-this) (js-arguments)))))
                 (:componentWillReceiveProps m)
                 (q/set-prop! -componentWillReceiveProps (fn [] (om/allow-reads (.apply (:componentWillReceiveProps m) (js-this) (js-arguments)))))
                 (:shouldComponentUpdate m)
                 (q/set-prop! -shouldComponentUpdate (fn [] (om/allow-reads (.apply (:shouldComponentUpdate m) (js-this) (js-arguments)))))
                 (:componentWillUpdate m)
                 (q/set-prop! -componentWillUpdate (fn [] (om/allow-reads (.apply (:componentWillUpdate m) (js-this) (js-arguments)))))
                 (:componentDidUpdate m)
                 (q/set-prop! -componentDidUpdate (fn [] (om/allow-reads (.apply (:componentDidUpdate m) (js-this) (js-arguments)))))
                 (:componentWillUnmount m)
                 (q/set-prop! -componentWillUnmount (fn [] (om/allow-reads (.apply (:componentWillUnmount m) (js-this) (js-arguments))))))
        react-component (.createClass js/React react-map)
        q-wrapper (fn [value & static-args]
                    (let [props #js {:value value :statics static-args}]
                      (when-let [key (get (first static-args) :react/key)]
                        (aset props "key" key))
                      (when-let [ref (get (first static-args) :react/ref)]
                        (aset props "ref" ref))
                      (react-component props)))]
    (when-let [displayName (.-displayName react-map)]
      (set! (.-displayName q-wrapper) displayName))
    (when-let [example (:exampleArg m)]
      (set! (.-exampleArg q-wrapper) example))
    (when-let [doc (:doc m)]
      (set! (.-doc q-wrapper) doc))
    (when-let [wrapper (:exampleContext m)]
      (set! (.-exampleContext q-wrapper) wrapper))
    q-wrapper))

(defn render
  "Given a ReactJS component, immediately render it, rooted to the
  specified DOM node."
  [component node]
  (.renderComponent js/React component node))

(defn set-state
  "Set the \"value\" key of a component's state."
  [component value]
  (.setState component #js {:value value}))

(defn get-state
  "Get the \"value\" key of a component's state."
  ([] (get-state *component*))
  ([component] (aget (.-state component) "value")))
