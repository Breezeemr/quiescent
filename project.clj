(defproject breeze-quiescent "0.1.4-SNAPSHOT"
  :description "A minimal, functional ClojureScript wrapper for ReactJS"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories [["snapshots" {:url "s3p://breezepackages/snapshots" :creds :gpg}]
                 ["releases" {:url "s3p://breezepackages/releases" :creds :gpg}]]
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2342" :scope "provided"]
                 [cljsjs/react "0.13.3-1"]]
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
  :profiles {:dev {:source-paths ["src" "examples/src"]
                   :resource-paths ["examples/resources"]
                   :plugins [[lein-cljsbuild "1.0.1"]]
                   :cljsbuild
                   {:builds
                    [{:source-paths ["src" "examples/src"]
                      :compiler
                      {:output-to "examples/resources/public/main.js"
                       :output-dir "examples/resources/public/build"
                       :optimizations :whitespace
                       :preamble ["react/react.min.js"]
                       :externs ["react/externs/react.js"]
                       :pretty-print true
                       :source-map
                       "examples/resources/public/main.js.map"
                       :closure-warnings {:non-standard-jsdoc :off}}}]}}})
