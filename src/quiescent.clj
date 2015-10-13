(ns quiescent)

(defmacro defcomponent
  "Creates a ReactJS component with the given name, an (optional)
  docstring, an argument vector and a body which will be used as the
  rendering function to quiescent/component.

  Shorthand for:

  (def name (quiescent/component (fn [value] body)))"
  [name & forms]
  (let [has-docstr? (string? (first forms))
        docstr (if has-docstr? (first forms) "")
        argvec (if has-docstr? (second forms) (first forms))
        body (if has-docstr? (drop 2 forms) (drop 1 forms))
        m (meta name)
        m (if (contains? m :displayName) m (assoc m :displayName (str name)))
        m (if has-docstr? (assoc m :doc docstr) m)]
    `(def ~name ~docstr (quiescent/component
                          (with-meta (fn ~argvec ~@body) ~m)))))

(defmacro wrapped-lifecycle-method
  "A very unsafe not-hygenic macro."
  [& body]
  `(fn []
     (let [~'this (cljs.core/js-this)
           ~'args (cljs.core/js-arguments)]                 ; Bound for the body!
       (binding [quiescent/*component* ~'this]
         ~@body))))

(defmacro createMixin [& proto+specs]
  ;; TODO: Combine different signatures.
  (let [[obj-specs proto+specs] (split-with (complement symbol?) proto+specs)
        {obj-props false obj-meths true} (group-by (comp vector? second)
                                                   obj-specs)
        o (gensym)]
    `(let [~o (~'js-obj)]
       ~@(for [[pname & pbody] obj-props]
           `(set! (. ~o ~(symbol (str \- (name pname))))
                  (do ~@pbody)))
       ~@(for [[mname margs & mbody] obj-meths]
           `(set! (. ~o ~(symbol (str \- (name mname))))
                  (fn ~(subvec margs 1)
                    (this-as
                      ~(first margs)
                      ~@mbody))))
       ~(if (empty? proto+specs)
          o
          `(specify! ~o ~@proto+specs)))))

(defmacro createClass [& specs]
  `(js/React.createClass (createMixin ~@specs)))

(defmacro defclass [name docstr & specs]
  (assert (symbol? name) "Must supply a symbol for name (as with def).")
  (let [[docstr specs] (if (string? docstr)
                      [docstr specs]
                      ["" (cons docstr specs)])
        specs (if (->> (split-with (complement symbol?) specs)
                       (some #(= % 'displayName)))
                (cons (list 'displayName name) specs)
                specs)]
    `(def ~name ~docstr (createClass ~@specs))))
