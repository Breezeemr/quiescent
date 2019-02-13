(ns breeze.quiescent.dom
  (:refer-clojure :exclude [map time])
  (:require cljs.tagged-literals)
  (:import (cljs.tagged_literals JSValue)))

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


(defn- js-props* [props]
  (cond
    (nil? props) nil
    (instance? JSValue props) props
    (map? props) (cljs.tagged-literals/read-js props)
    :else (list 'breeze.quiescent.dom/js-props props)))

(defmacro RE
  "Return a react element like React.createElement.
  Component may be a string or a keyword literal for a dom component.
  :key and :ref are expected to be keys in the attribute.

  Props will be converted to plain js objects shallowly (and at compile time if
  possible)."
  ([component]
   (with-meta `(RE ~component nil) (meta &form)))
  ([component props & children]
   (let [p (js-props* props)]
     (with-meta `(~'js/React.createElement ~component ~p ~@children) (clojure.core/meta &form)))))


(defmacro a
  ([] (with-meta `(breeze.quiescent.dom/RE "a" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "a" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "a" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro abbr
  ([] (with-meta `(breeze.quiescent.dom/RE "abbr" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "abbr" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "abbr" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro address
  ([] (with-meta `(breeze.quiescent.dom/RE "address" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "address" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "address" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro area
  ([] (with-meta `(breeze.quiescent.dom/RE "area" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "area" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "area" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro article
  ([] (with-meta `(breeze.quiescent.dom/RE "article" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "article" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "article" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro aside
  ([] (with-meta `(breeze.quiescent.dom/RE "aside" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "aside" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "aside" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro audio
  ([] (with-meta `(breeze.quiescent.dom/RE "audio" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "audio" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "audio" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro b
  ([] (with-meta `(breeze.quiescent.dom/RE "b" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "b" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "b" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro base
  ([] (with-meta `(breeze.quiescent.dom/RE "base" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "base" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "base" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro bdi
  ([] (with-meta `(breeze.quiescent.dom/RE "bdi" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "bdi" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "bdi" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro bdo
  ([] (with-meta `(breeze.quiescent.dom/RE "bdo" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "bdo" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "bdo" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro big
  ([] (with-meta `(breeze.quiescent.dom/RE "big" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "big" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "big" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro blockquote
  ([] (with-meta `(breeze.quiescent.dom/RE "blockquote" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "blockquote" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "blockquote" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro body
  ([] (with-meta `(breeze.quiescent.dom/RE "body" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "body" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "body" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro br
  ([] (with-meta `(breeze.quiescent.dom/RE "br" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "br" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "br" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro button
  ([] (with-meta `(breeze.quiescent.dom/RE "button" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "button" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "button" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro canvas
  ([] (with-meta `(breeze.quiescent.dom/RE "canvas" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "canvas" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "canvas" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro caption
  ([] (with-meta `(breeze.quiescent.dom/RE "caption" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "caption" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "caption" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro circle
  ([] (with-meta `(breeze.quiescent.dom/RE "circle" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "circle" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "circle" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro cite
  ([] (with-meta `(breeze.quiescent.dom/RE "cite" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "cite" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "cite" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro clipPath
  ([] (with-meta `(breeze.quiescent.dom/RE "clipPath" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "clipPath" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "clipPath" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro code
  ([] (with-meta `(breeze.quiescent.dom/RE "code" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "code" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "code" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro col
  ([] (with-meta `(breeze.quiescent.dom/RE "col" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "col" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "col" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro colgroup
  ([] (with-meta `(breeze.quiescent.dom/RE "colgroup" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "colgroup" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "colgroup" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro data
  ([] (with-meta `(breeze.quiescent.dom/RE "data" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "data" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "data" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro datalist
  ([] (with-meta `(breeze.quiescent.dom/RE "datalist" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "datalist" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "datalist" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro dd
  ([] (with-meta `(breeze.quiescent.dom/RE "dd" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "dd" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "dd" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro defs
  ([] (with-meta `(breeze.quiescent.dom/RE "defs" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "defs" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "defs" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro del
  ([] (with-meta `(breeze.quiescent.dom/RE "del" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "del" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "del" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro details
  ([] (with-meta `(breeze.quiescent.dom/RE "details" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "details" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "details" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro dfn
  ([] (with-meta `(breeze.quiescent.dom/RE "dfn" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "dfn" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "dfn" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro dialog
  ([] (with-meta `(breeze.quiescent.dom/RE "dialog" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "dialog" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "dialog" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro div
  ([] (with-meta `(breeze.quiescent.dom/RE "div" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "div" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "div" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro dl
  ([] (with-meta `(breeze.quiescent.dom/RE "dl" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "dl" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "dl" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro dt
  ([] (with-meta `(breeze.quiescent.dom/RE "dt" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "dt" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "dt" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro ellipse
  ([] (with-meta `(breeze.quiescent.dom/RE "ellipse" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "ellipse" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "ellipse" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro em
  ([] (with-meta `(breeze.quiescent.dom/RE "em" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "em" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "em" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro embed
  ([] (with-meta `(breeze.quiescent.dom/RE "embed" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "embed" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "embed" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro fieldset
  ([] (with-meta `(breeze.quiescent.dom/RE "fieldset" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "fieldset" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "fieldset" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro figcaption
  ([] (with-meta `(breeze.quiescent.dom/RE "figcaption" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "figcaption" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "figcaption" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro figure
  ([] (with-meta `(breeze.quiescent.dom/RE "figure" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "figure" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "figure" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro footer
  ([] (with-meta `(breeze.quiescent.dom/RE "footer" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "footer" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "footer" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro form
  ([] (with-meta `(breeze.quiescent.dom/RE "form" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "form" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "form" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro g
  ([] (with-meta `(breeze.quiescent.dom/RE "g" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "g" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "g" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro h1
  ([] (with-meta `(breeze.quiescent.dom/RE "h1" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "h1" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "h1" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro h2
  ([] (with-meta `(breeze.quiescent.dom/RE "h2" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "h2" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "h2" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro h3
  ([] (with-meta `(breeze.quiescent.dom/RE "h3" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "h3" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "h3" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro h4
  ([] (with-meta `(breeze.quiescent.dom/RE "h4" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "h4" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "h4" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro h5
  ([] (with-meta `(breeze.quiescent.dom/RE "h5" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "h5" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "h5" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro h6
  ([] (with-meta `(breeze.quiescent.dom/RE "h6" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "h6" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "h6" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro head
  ([] (with-meta `(breeze.quiescent.dom/RE "head" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "head" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "head" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro header
  ([] (with-meta `(breeze.quiescent.dom/RE "header" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "header" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "header" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro hr
  ([] (with-meta `(breeze.quiescent.dom/RE "hr" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "hr" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "hr" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro html
  ([] (with-meta `(breeze.quiescent.dom/RE "html" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "html" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "html" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro i
  ([] (with-meta `(breeze.quiescent.dom/RE "i" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "i" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "i" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro iframe
  ([] (with-meta `(breeze.quiescent.dom/RE "iframe" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "iframe" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "iframe" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro img
  ([] (with-meta `(breeze.quiescent.dom/RE "img" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "img" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "img" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro input
  ([] (with-meta `(breeze.quiescent.dom/RE "input" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "input" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "input" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro ins
  ([] (with-meta `(breeze.quiescent.dom/RE "ins" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "ins" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "ins" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro kbd
  ([] (with-meta `(breeze.quiescent.dom/RE "kbd" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "kbd" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "kbd" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro keygen
  ([] (with-meta `(breeze.quiescent.dom/RE "keygen" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "keygen" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "keygen" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro label
  ([] (with-meta `(breeze.quiescent.dom/RE "label" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "label" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "label" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro legend
  ([] (with-meta `(breeze.quiescent.dom/RE "legend" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "legend" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "legend" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro li
  ([] (with-meta `(breeze.quiescent.dom/RE "li" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "li" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "li" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro line
  ([] (with-meta `(breeze.quiescent.dom/RE "line" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "line" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "line" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro linearGradient
  ([] (with-meta `(breeze.quiescent.dom/RE "linearGradient" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "linearGradient" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "linearGradient" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro link
  ([] (with-meta `(breeze.quiescent.dom/RE "link" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "link" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "link" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro main
  ([] (with-meta `(breeze.quiescent.dom/RE "main" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "main" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "main" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro map
  ([] (with-meta `(breeze.quiescent.dom/RE "map" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "map" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "map" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro mark
  ([] (with-meta `(breeze.quiescent.dom/RE "mark" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "mark" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "mark" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro mask
  ([] (with-meta `(breeze.quiescent.dom/RE "mask" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "mask" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "mask" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro menu
  ([] (with-meta `(breeze.quiescent.dom/RE "menu" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "menu" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "menu" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro menuitem
  ([] (with-meta `(breeze.quiescent.dom/RE "menuitem" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "menuitem" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "menuitem" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro meta
  ([] (with-meta `(breeze.quiescent.dom/RE "meta" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "meta" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "meta" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro meter
  ([] (with-meta `(breeze.quiescent.dom/RE "meter" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "meter" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "meter" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro nav
  ([] (with-meta `(breeze.quiescent.dom/RE "nav" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "nav" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "nav" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro noscript
  ([] (with-meta `(breeze.quiescent.dom/RE "noscript" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "noscript" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "noscript" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro object
  ([] (with-meta `(breeze.quiescent.dom/RE "object" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "object" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "object" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro ol
  ([] (with-meta `(breeze.quiescent.dom/RE "ol" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "ol" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "ol" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro optgroup
  ([] (with-meta `(breeze.quiescent.dom/RE "optgroup" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "optgroup" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "optgroup" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro option
  ([] (with-meta `(breeze.quiescent.dom/RE "option" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "option" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "option" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro output
  ([] (with-meta `(breeze.quiescent.dom/RE "output" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "output" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "output" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro p
  ([] (with-meta `(breeze.quiescent.dom/RE "p" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "p" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "p" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro param
  ([] (with-meta `(breeze.quiescent.dom/RE "param" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "param" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "param" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro path
  ([] (with-meta `(breeze.quiescent.dom/RE "path" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "path" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "path" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro pattern
  ([] (with-meta `(breeze.quiescent.dom/RE "pattern" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "pattern" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "pattern" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro picture
  ([] (with-meta `(breeze.quiescent.dom/RE "picture" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "picture" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "picture" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro polygon
  ([] (with-meta `(breeze.quiescent.dom/RE "polygon" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "polygon" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "polygon" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro polyline
  ([] (with-meta `(breeze.quiescent.dom/RE "polyline" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "polyline" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "polyline" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro pre
  ([] (with-meta `(breeze.quiescent.dom/RE "pre" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "pre" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "pre" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro progress
  ([] (with-meta `(breeze.quiescent.dom/RE "progress" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "progress" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "progress" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro q
  ([] (with-meta `(breeze.quiescent.dom/RE "q" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "q" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "q" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro radialGradient
  ([] (with-meta `(breeze.quiescent.dom/RE "radialGradient" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "radialGradient" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "radialGradient" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro rect
  ([] (with-meta `(breeze.quiescent.dom/RE "rect" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "rect" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "rect" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro rp
  ([] (with-meta `(breeze.quiescent.dom/RE "rp" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "rp" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "rp" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro rt
  ([] (with-meta `(breeze.quiescent.dom/RE "rt" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "rt" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "rt" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro ruby
  ([] (with-meta `(breeze.quiescent.dom/RE "ruby" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "ruby" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "ruby" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro s
  ([] (with-meta `(breeze.quiescent.dom/RE "s" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "s" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "s" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro samp
  ([] (with-meta `(breeze.quiescent.dom/RE "samp" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "samp" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "samp" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro script
  ([] (with-meta `(breeze.quiescent.dom/RE "script" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "script" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "script" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro section
  ([] (with-meta `(breeze.quiescent.dom/RE "section" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "section" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "section" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro select
  ([] (with-meta `(breeze.quiescent.dom/RE "select" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "select" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "select" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro small
  ([] (with-meta `(breeze.quiescent.dom/RE "small" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "small" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "small" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro source
  ([] (with-meta `(breeze.quiescent.dom/RE "source" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "source" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "source" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro span
  ([] (with-meta `(breeze.quiescent.dom/RE "span" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "span" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "span" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro stop
  ([] (with-meta `(breeze.quiescent.dom/RE "stop" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "stop" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "stop" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro strong
  ([] (with-meta `(breeze.quiescent.dom/RE "strong" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "strong" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "strong" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro style
  ([] (with-meta `(breeze.quiescent.dom/RE "style" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "style" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "style" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro sub
  ([] (with-meta `(breeze.quiescent.dom/RE "sub" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "sub" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "sub" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro summary
  ([] (with-meta `(breeze.quiescent.dom/RE "summary" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "summary" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "summary" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro sup
  ([] (with-meta `(breeze.quiescent.dom/RE "sup" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "sup" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "sup" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro svg
  ([] (with-meta `(breeze.quiescent.dom/RE "svg" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "svg" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "svg" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro table
  ([] (with-meta `(breeze.quiescent.dom/RE "table" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "table" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "table" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro tbody
  ([] (with-meta `(breeze.quiescent.dom/RE "tbody" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "tbody" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "tbody" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro td
  ([] (with-meta `(breeze.quiescent.dom/RE "td" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "td" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "td" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro text
  ([] (with-meta `(breeze.quiescent.dom/RE "text" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "text" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "text" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro textarea
  ([] (with-meta `(breeze.quiescent.dom/RE "textarea" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "textarea" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "textarea" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro tfoot
  ([] (with-meta `(breeze.quiescent.dom/RE "tfoot" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "tfoot" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "tfoot" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro th
  ([] (with-meta `(breeze.quiescent.dom/RE "th" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "th" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "th" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro thead
  ([] (with-meta `(breeze.quiescent.dom/RE "thead" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "thead" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "thead" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro time
  ([] (with-meta `(breeze.quiescent.dom/RE "time" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "time" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "time" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro title
  ([] (with-meta `(breeze.quiescent.dom/RE "title" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "title" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "title" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro tr
  ([] (with-meta `(breeze.quiescent.dom/RE "tr" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "tr" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "tr" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro track
  ([] (with-meta `(breeze.quiescent.dom/RE "track" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "track" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "track" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro tspan
  ([] (with-meta `(breeze.quiescent.dom/RE "tspan" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "tspan" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "tspan" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro u
  ([] (with-meta `(breeze.quiescent.dom/RE "u" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "u" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "u" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro ul
  ([] (with-meta `(breeze.quiescent.dom/RE "ul" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "ul" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "ul" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro var
  ([] (with-meta `(breeze.quiescent.dom/RE "var" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "var" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "var" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro video
  ([] (with-meta `(breeze.quiescent.dom/RE "video" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "video" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "video" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro wbr
  ([] (with-meta `(breeze.quiescent.dom/RE "wbr" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(breeze.quiescent.dom/RE "wbr" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(breeze.quiescent.dom/RE "wbr" ~props ~@children)
     (clojure.core/meta &form))))
