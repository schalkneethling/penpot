;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at http://mozilla.org/MPL/2.0/.
;;
;; Copyright (c) 2017-2019 Andrey Antukh <niwi@niwi.nz>

(ns uxbox.main.refs
  "A collection of derived refs."
  (:require [lentes.core :as l]
            [beicon.core :as rx]
            [uxbox.main.constants :as c]
            [uxbox.main.store :as st]))

(def workspace
  (letfn [(selector [state]
            (let [id (get-in state [:workspace :current])]
              (get-in state [:workspace id])))]
    (-> (l/lens selector)
        (l/derive st/state))))

(def selected-shapes
  (-> (l/key :selected)
      (l/derive workspace)))

(def selected-canvas
  (-> (l/key :selected-canvas)
      (l/derive workspace)))

(def toolboxes
  (-> (l/key :toolboxes)
      (l/derive workspace)))

(def flags
  (-> (l/key :flags)
      (l/derive workspace)))

(def selected-zoom
  (-> (l/key :zoom)
      (l/derive workspace)))

(def selected-tooltip
  (-> (l/key :tooltip)
      (l/derive workspace)))

(def selected-drawing-shape
  (-> (l/key :drawing)
      (l/derive workspace)))

(def selected-drawing-tool
  (-> (l/key :drawing-tool)
      (l/derive workspace)))

(def selected-edition
  (-> (l/key :edition)
      (l/derive workspace)))

(def history
  (-> (l/key :history)
      (l/derive workspace)))

(defn selected-modifiers
  [id]
  {:pre [(uuid? id)]}
  (-> (l/in [:modifiers id])
      (l/derive workspace)))

(defn alignment-activated?
  [flags]
  (and (contains? flags :grid-indexed)
       (contains? flags :grid-snap)))

(def selected-alignment
  (-> (comp (l/key :flags)
            (l/lens alignment-activated?))
      (l/derive workspace)))

(def shapes-by-id
  (-> (l/key :shapes)
      (l/derive st/state)))




