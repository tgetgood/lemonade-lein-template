(ns {{name}}.core
  (:require
   [lemonade.core :as l]
   [lemonade.hosts :as hosts]
   [lemonade.system :as system]
   [lemonade.window :as window]
   [lemonade.events.hlei :as hlei]))

#?(:cljs (enable-console-print!))

(def host hosts/default-host)

(defonce app-db (atom {:text "Almost Useless"
                       :count 3}))

(defn image-fn [state]
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
   {:host      host
    :size      :fullscreen
    :handler   image-fn
    :behaviour (comp hlei/wrap window/wrap-windowing)
    :app-db    app-db}))

(defn on-reload []
  (on-reload))
