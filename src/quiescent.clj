(ns quiescent)

(defmacro defcomponent
  "Creates a ReactJS component with the given name, an (optional)
  docstring and an argument vector (for a single argument) and a body
  which will be passed as the rendering function to
  quiescent/component.

  Shorthand for:

  (def name (quiescent/component (fn [value] body)))"
  [name & forms]
  (let [has-docstr? (string? (first forms))
        docstr (if has-docstr? (first forms) "")
        argvec (if has-docstr? (second forms) (first forms))
        body (if has-docstr? (drop 2 forms) (drop 1 forms))]
    `(def ~name ~docstr (quiescent/component (fn ~argvec ~@body)))))


(defmacro set-prop! [o propname fn]
  `(let [newo# ~o] (set! (. newo# ~propname) ~fn) newo#))
