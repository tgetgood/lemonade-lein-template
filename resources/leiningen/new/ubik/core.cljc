(ns {{name}}.core
    (:require [ubik.core :as l]
              [ubik.hosts :as hosts]))

#?(:cljs (enable-console-print!))

(def app-state {:text "Almost Useless"
                :count 3})

(defn shape [state]
  (let [{:keys [text count]} state]
    [(-> l/text
         (assoc :text text)
         (l/scale 4)
         (l/translate [250 550]))
     (map (fn [i] (l/translate
                   (assoc l/circle :radius 100)
                   [(* (inc i) 200) 400]))
          (range count))]))

(def host (hosts/default-host {}))

(defn ^:export init []
  (l/draw! (shape app-state) host))

(defn on-reload []
  (init))
