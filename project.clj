(defproject kirasystems/leinjacker "0.4.3"
  :description "A library for Leiningen plug-in authors."
  :url "https://github.com/kirasystems/leinjacker"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/core.contracts "0.0.1"]]
  :profiles {:dev {:dependencies [[midje "1.3.1"]
                                  [org.clojure/clojure "1.4.0"]]
                   :eval-in :leiningen}
             :1.2 {:dependencies [[org.clojure/clojure "1.2.1"]]}}
  :lein-release {:deploy-via :clojars}

  :repositories [["releases" {:url "https://clojars.org/repo"
                              :sign-releases false
                              :username :env
                              :password :env}]]
  ;;; Don't sign the tag, and don't deploy
  :release-tasks [["vcs" "assert-committed"]
                  ["change" "version" "leiningen.release/bump-version" "release"]
                  ["vcs" "commit"]
                  ["vcs" "tag" "--no-sign"]
                  ["change" "version" "leiningen.release/bump-version"]
                  ["vcs" "commit"]
                  ["vcs" "push"]])
