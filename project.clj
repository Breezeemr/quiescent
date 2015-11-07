(defproject breeze-quiescent "0.1.6-SNAPSHOT"
  :description "A minimal, functional ClojureScript wrapper for ReactJS"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
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
                   [[org.clojure/clojure "1.7.0"]
                    [org.clojure/clojurescript "1.7.145"]]
                   :source-paths ["src" "examples/src"]
                   :resource-paths ["examples/resources"]
                   :plugins [[lein-cljsbuild "1.1.0"]]
                   :cljsbuild
                   {:builds
                    [{:source-paths ["src" "examples/src"]
                      :compiler
                      {:output-to "examples/resources/public/main.js"
                       :output-dir "examples/resources/public/build"
                       :optimizations :whitespace
                       :pretty-print true
                       :source-map
                       "examples/resources/public/main.js.map"
                       :closure-warnings {:non-standard-jsdoc :off}}}]}}})
