# leinjacker

leinjacker is a library of utilities for Leiningen plug-in developers.  Current features include:

* `leinjacker.eval` gives you an easy way for your project to call `eval-in-project` or `sh`
  and have it work independent of the version of Leiningen the user is running. Additionally,
  there is a function `hook-eval-in-project`. This function can be used provide custom behavior
  (like middleware) for `eval-in-project`, such as for code instrumentation or alternative JVM
  launching scripts.

  Finally, there is the `in-project` macro. This allows you to eval code in your project without
  having to construct the form by hand. You just provide the bindings used to pass state to your
  project along with the code that uses those bindings.

* `leinjacker.deps` adds some handy functions for querying and manipulating the dependencies
  of a project.

* `leinjacker.utils` provides useful utilities for supporting multiple Leiningen generations,
  such as:

  * `try-resolve` and `try-resolve-any` - useful for resolving namespaced symbols at
    runtime.
  * `lein-generation` - returns the current Leiningen generation (1 or 2) as an integer.
  * `lein-home` - returns the path to current Leiningen home (typically `~/.lein/`). This
    abstracts away the location of Leiningen's `leiningen-home`, since it moved between
    generations.
  * `read-lein-project` - reads a `project.clj` and returns the project map. This
    abstracts away the location and name of Leiningen's project read function,
    since it was renamed between generations.
  * `abort` - 1.x- and 2.x-compatible way to signal task failure.
  * `apply-task` - 1.x- and 2.x-compatible way to apply arguments to a task.
  * `get-classpath` - 1.x- and 2.x-compatible way to get the classpath from a project map.

* `leinjacker.lein-runner` has functions for finding and running the `lein` command on the
  path for each Leiningen generation. Useful for testing plugins under various Leiningen
  versions. It provides:

  * `find-lein-cmd` - looks for `lein` on the $PATH by trying to call it. It takes a
    `generation` argument, which should be `1` or `2`. It will then look for a command
    called `lein` or `lein<generation>` that reports the correct generation when called
    with `lein version`. It will memoize the found cmd to `./.lein-commands` to speed
    up subsequent calls. This can be disabled via the `memoize?` arg.
  * `run-lein` - Will execute the given Leiningen `generation` in `dir` with the given
    `args`, returning the result map from the 'clojure.java.shell/sh' call.

  See leinjacker's tests for examples of usage.

## Usage

Just drop the following into your plug-in’s `project.clj`:

````clojure
(defproject lein-tau "6.283"
  :name "The π is a lie!"
  ;; other stuff
  :dependencies [[kirasystems/leinjacker "0.4.3"]
                 ;; more deps
		 ])
````

Although this library is built using Leiningen 2, the library itself does not require it.  It should also work with Clojure 1.2 and above.

## Contributing

If you ever find yourself writing the same generic code in more than one of your plug-ins, feel free to send it here.

### Contributors

* Daniel Solano Gómez
* Tobias Crawley
* Phil Hagelberg
* David Greenberg
* Justin Balthrop
* Anthony Grimes

## License

Copyright © 2012 Sattvik Software & Technology Resources, Ltd. Co.
All rights reserved.

Distributed under the Eclipse Public License, the same as Clojure.
