(ns alphabet-cipher.coder
  (:require [clojure.set :as s]))

(def alphabet "abcdefghijklmnopqrstuvwxyz")

(defn mapping [keyword-char]
  (let [alphabet-offset (- (int keyword-char) (int \a))
        shifted-alpha (take 25 (drop alphabet-offset (cycle alphabet)))]
    (zipmap alphabet shifted-alpha)))

(defn encode-char [keyword-char char]
  ((mapping keyword-char) char))

(defn decode-char [keyword-char char]
  ((s/map-invert (mapping keyword-char)) char))

(defn encode [keyword message]
  (apply str (map encode-char (cycle keyword) message)))

(defn decode [keyword message]
  (apply str (map decode-char (cycle keyword) message)))

(defn decipher [cipher message]
  "decypherme")
