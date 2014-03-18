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

(defmacro set-prop! [o propname fn]
  `(let [newo# ~o] (set! (. newo# ~propname) ~fn) newo#))
