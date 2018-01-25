(ns {{name}}.core
    (:require [lemonade.core :as l]
              [lemonade.system :as system]))

#?(:cljs (enable-console-print!))

(defonce app-db (atom {:text "Almost Useless"
                       :count 3}))

(defn render [state]
  (let [{:keys [text count]} state]
    [(-> l/text
         (assoc :text text)
         (l/scale 4)
         (l/translate [250 550]))
     (map (fn [i] (l/translate
                   (assoc l/circle :radius 100)
                   [(* (inc i) 200) 400]))
          (range count))]))

(defn ^:export init []
  (system/initialise!
   {:size   :fullscreen
    :render render
    :app-db app-db}))

(defn on-reload []
  (on-reload))
