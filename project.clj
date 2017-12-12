(defproject breeze-quiescent "0.1.8-SNAPSHOT"
  :description "A minimal, functional ClojureScript wrapper for ReactJS"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories [["snapshots" {:url "s3p://breezepackages/snapshots" :creds :gpg}]
                 ["releases" {:url "s3p://breezepackages/releases" :creds :gpg}]]
  :dependencies [[org.clojure/clojure "1.9.0" :scope "provided"]
                 [org.clojure/clojurescript "1.9.946" :scope "provided"]
                 [cljsjs/react-dom "16.2.0-0"]]
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
  :profiles {:dev {:source-paths   ["src" "examples/src"]
                   :resource-paths ["examples/resources"]
                   :dependencies   [[org.clojure/clojure "1.9.0"]
                                    [org.clojure/clojurescript "1.9.946"]]
                   :plugins        [[lein-figwheel "0.5.14" :exclusions [org.clojure/clojure]]]
                   :cljsbuild
                                   {:builds
                                    {:main
                                     {:source-paths ["src" "examples/src"]
                                      :figwheel     true
                                      :compiler
                                                    {:output-to        "examples/resources/public/main.js"
                                                     :output-dir       "examples/resources/public/build"
                                                     :asset-path       "build"
                                                     :main             quiescent.examples.hello
                                                     :optimizations    :none
                                                     :pretty-print     true
                                                     :install-deps     true
                                                     :npm-deps         {:react                "16.2.0"
                                                                        :react-dom            "16.2.0"
                                                                        "create-react-class" "15.6.2"}
                                                     :source-map       true
                                                     ;:source-map
                                                     ;                  "examples/resources/public/main.js.map"
                                                     :closure-warnings {:non-standard-jsdoc :off}}}}}}})
