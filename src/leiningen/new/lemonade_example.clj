(ns leiningen.new.lemonade-example
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "lemonade-example"))

(defn lemonade-example
  "FIXME: write documentation"
  [name]
  (let [data {:name      name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh Lemonade example project.")
    (->files data
             ["project.clj" (render "project.clj" data)]
             [".gitignore" (render ".gitignore" data)]
             ["README.md" (render "README.md" data)]
             ["dev/user.clj" (render "dev/user.clj" data)]
             ["resources/public/index.html" (render "index.html" data)]
             ["src/{{sanitized}}/core.cljc" (render "core.cljc" data)])))
