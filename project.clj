(defproject com.breezeehr/quiescent "0.2.0-SNAPSHOT"
  :description "A minimal, functional ClojureScript wrapper for ReactJS"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories [["snapshots" {:url "s3p://breezepackages/snapshots" :creds :gpg}]
                 ["releases" {:url "s3p://breezepackages/releases" :creds :gpg}]]
  :dependencies [[org.clojure/clojure "1.7.0" :scope "provided"]
                 [org.clojure/clojurescript "1.7.145" :scope "provided"]
                 [cljsjs/react-dom "0.14.0-1"]]
  :source-paths ["src"]
  :release-tasks [["vcs" "assert-committed"]
                  ["change" "version"
                   "leiningen.release/bump-version" "release"]
                  ["vcs" "commit"]
                  ["vcs" "tag"]
                  ["deploy" "releases"]
                  ["change" "version" "leiningen.release/bump-version"]
                  ["vcs" "commit"]
                  ["vcs" "push"]]

  ;; development concerns
  :profiles {:dev {:dependencies
                   [[org.clojure/clojure "1.10.0"]
                    [org.clojure/clojurescript "1.10.516"]
                    [figwheel-sidecar "0.5.18"
                     :exclusions [org.clojure/clojure cljsjs/react org.clojure/clojurescript]]
                    [nrepl/nrepl "0.6.0"]]
                   ;; :source-paths     ["src"]
                   #_#_:resource-paths ["examples/resources"]
                   :plugins            [[lein-cljsbuild "1.1.0"]
                                        [lein-figwheel "0.5.18"]]
                   :cljsbuild
                   {:builds
                    [{:source-paths ["src" "examples/src"]
                      :id           "dev-cljs"
                      :figwheel     true
                      :compiler
                      {:output-to        "resources/public/js/main.js"
                       :output-dir       "resources/public/js/out"
                       :main             "breeze.quiescent"
                       :asset-path       "js/out"
                       :closure-warnings {:non-standard-jsdoc :off}}}]}}})
