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
         (l/translate [250 550])
         )
     (map (fn [i] (l/translate
                   (assoc l/circle :radius 100)
                   [(* (inc i) 200) 400]))
          (range count))]))

(defn on-reload []
  (system/fullscreen host)
  (system/initialise!
   {:host      host
    :handler   image-fn
    :behaviour #(-> %
                    window/wrap-windowing
                    hlei/wrap)
    :app-db    app-db}))

(defn ^:export init []
  (on-reload))
