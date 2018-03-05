(ns leiningen.new.ubik
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "ubik"))

(defn ubik
  "FIXME: write documentation"
  [name]
  (let [data {:name      name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh Ubik example project.")
    (->files data
             ["project.clj" (render "project.clj" data)]
             [".gitignore" (render ".gitignore" data)]
             ["README.md" (render "README.md" data)]
             ["dev/user.clj" (render "dev/user.clj" data)]
             ["resources/public/index.html" (render "index.html" data)]
             ["src/{{sanitized}}/core.cljc" (render "core.cljc" data)])))
