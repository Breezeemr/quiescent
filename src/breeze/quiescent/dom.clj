(ns breeze.quiescent.dom
  (:refer-clojure :exclude [map meta time])
  (:require cljs.tagged-literals)
  (:import (cljs.tagged_literals JSValue)))

(defn- tag-fn*
  "Return a form to defining a wrapper function for a base ReactDOM Element."
  [tag]
  (let [f (symbol "js" (str "React.DOM." (name tag)))]
    `(defn ~tag
       ~(str "Return a `" (name tag) "` ReactElement.")
       ([]
         (~f nil))
       ([~'attrs]
         (~f (quiescent.dom/js-props ~'attrs)))
       ([~'attrs ~'c]
         (~f (quiescent.dom/js-props ~'attrs) ~'c))
       ([~'attrs ~'c1 ~'c2]
         (~f (quiescent.dom/js-props ~'attrs) ~'c1 ~'c2))
       ([~'attrs ~'c1 ~'c2 ~'c3]
         (~f (quiescent.dom/js-props ~'attrs) ~'c1 ~'c2 ~'c3))
       ([~'attrs ~'c1 ~'c2 ~'c3 ~'c4]
         (~f (quiescent.dom/js-props ~'attrs) ~'c1 ~'c2 ~'c3 ~'c4))
       ([~'attrs ~'c1 ~'c2 ~'c3 ~'c4 ~'c5 & ~'children]
         (let [a# (cljs.core/make-array 6)]
           (aset a# 0 (quiescent.dom/js-props ~'attrs))
           (aset a# 1 ~'c1)
           (aset a# 2 ~'c2)
           (aset a# 3 ~'c3)
           (aset a# 4 ~'c4)
           (aset a# 5 ~'c5)
           (reduce #(do (.push %1 %2) %1) a# ~'children)
           (.apply ~f nil a#))))))

(defn- tag-macro-fn+attrs* [fsym attrs]
  (list fsym
    (cond
      (nil? attrs) nil
      (instance? JSValue attrs) attrs
      (map? attrs) (cljs.tagged-literals/read-js attrs)
      :else (list 'quiescent.dom/js-props attrs))))

(defmacro ^:private tag-macro*
  "Define a macro which calls a React.DOM factory."
  [tag]
  (let [f (symbol "js" (str "React.DOM." (name tag)))]
    `(defmacro ~tag
       ~(str "Return a `" (name tag) "` ReactElement.")
       ([]
         (list '~f nil))
       ([~'attrs]
         (tag-macro-fn+attrs* '~f ~'attrs))
       ([~'attrs & ~'children]
         (concat (tag-macro-fn+attrs* '~f ~'attrs) ~'children)))))

(def ReactDOM-elements
  '[a abbr address area article aside audio b base bdi bdo big blockquote body br
    button canvas caption cite code col colgroup data datalist dd del details dfn
    dialog div dl dt em embed fieldset figcaption figure footer form h1 h2 h3 h4 h5
    h6 head header hr html i iframe img input ins kbd keygen label legend li link
    main map mark menu menuitem meta meter nav noscript object ol optgroup option
    output p param picture pre progress q rp rt ruby s samp script section select
    small source span strong style sub summary sup table tbody td textarea tfoot th
    thead time title tr track u ul var video wbr

    circle clipPath defs ellipse g line linearGradient mask path pattern polygon
    polyline radialGradient rect stop svg text tspan])

(defmacro ^:private define-tag-macros []
  `(do ~@(clojure.core/map #(list 'tag-macro* %) ReactDOM-elements)))

(define-tag-macros)

(defmacro define-tag-functions
  "Macro which expands to a do block which contains a defn for
  each supported HTML and SVG tag. The resulting functions take
  an (optional) properties argument, and any number of child
  arguments. The properties argument may be a Clojure map or a JS
  object."
  []
  `(do ~@(clojure.core/map tag-fn* ReactDOM-elements)))

(defmacro render
  "`ReactDOM.render` wrapper"
  ([element mountpoint] `(js/ReactDOM.render ~element ~mountpoint))
  ([element mountpoint callback] `(js/ReactDOM.render ~element ~mountpoint ~callback)))

(defmacro unmountComponentAtNode
  "`ReactDOM.unmountComponentAtNode` wrapper"
  [mountpoint] `(js/ReactDOM.unmountComponentAtNode ~mountpoint))

(defmacro findDOMNode
  "`ReactDOM.findDOMNode` wrapper"
  [component] `(js/ReactDOM.findDOMNode ~component))
