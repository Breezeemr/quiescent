(ns quiescent
  [:require-macros [quiescent :as q]])

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
                         (this-as this
                                  (or
                                    (not= (aget (.-props this) "value")
                                          (aget next-props "value"))
                                    (let [this-state (or (.-state this) (js-obj))
                                          next-state (or next-state (js-obj))]
                                      (not= (aget this-state "value")
                                            (aget next-state "value"))))))
                      :render
                       (fn []
                         (this-as this
                                  (binding [*component* this]
                                    (apply renderer
                                           (aget (.-props this) "value")
                                           (aget (.-props this) "statics")))))}
                 (or (:displayName m) (not-empty (.-name renderer)))
                 (q/set-prop! -displayName (or (:displayName m)
                                               (not-empty (.-name renderer))))
                 (:getInitialState m)
                 (q/set-prop! -getInitialState
                              (fn []
                                (this-as this
                                         (binding [*component* this]
                                           #js {:value (get-initial-state)}))))
                 (:getDefaultProps m)
                 (q/set-prop! -getDefaultProps (:getDefaultProps m))
                 (:componentWillMount m)
                 (q/set-prop! -componentWillMount (:componentWillMount m))
                 (:componentDidMount m)
                 (q/set-prop! -componentDidMount (:componentDidMount m))
                 (:componentWillReceiveProps m)
                 (q/set-prop! -componentWillReceiveProps (:componentWillReceiveProps m))
                 (:shouldComponentUpdate m)
                 (q/set-prop! -shouldComponentUpdate (:shouldComponentUpdate m))
                 (:componentWillUpdate m)
                 (q/set-prop! -componentWillUpdate (:componentWillUpdate m))
                 (:componentDidUpdate m)
                 (q/set-prop! -componentDidUpdate (:componentDidUpdate m))
                 (:componentWillUnmount m)
                 (q/set-prop! -componentWillUnmount (:componentWillUnmount m)))
        react-component (.createClass js/React react-map)
        q-wrapper (fn [value & static-args]
                    (react-component #js {:value value :statics static-args}))]
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
