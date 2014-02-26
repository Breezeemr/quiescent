(ns quiescent)

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
  (let [react-component
        (.createClass js/React
           #js {:shouldComponentUpdate
                (fn [next-props _]
                  (this-as this
                           (not= (aget (.-props this) "value")
                                 (aget next-props "value"))))
                :render
                (fn []
                  (this-as this
                           (binding [*component* this]
                             (apply renderer
                                    (aget (.-props this) "value")
                                    (aget (.-props this) "statics")))))})]
    (fn [value & static-args]
      (react-component #js {:value value :statics static-args}))))

(defn render
  "Given a ReactJS component, immediately render it, rooted to the
  specified DOM node."
  [component node]
  (.renderComponent js/React component node))
