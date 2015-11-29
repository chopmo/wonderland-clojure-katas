(ns alphabet-cipher.coder
  (:require [clojure.set :as s]))

(def alphabet "abcdefghijklmnopqrstuvwxyz")

(defn alphabet-offset [c]
  (- (int c) (int \a)))

(defn shift-alphabet [n]
  (->> alphabet
       cycle
       (drop n)
       (take 25)))

(defn alphabet-mapping [keychar]
  (let [offset (alphabet-offset keychar)
        shifted-alphabet (shift-alphabet offset)]
    (zipmap alphabet shifted-alphabet)))

(defn encode-char [keyword-char char]
  (let [mapping (alphabet-mapping keyword-char)]
    (mapping char)))

(defn decode-char [keyword-char char]
  (let [mapping (mapping keyword-char)
        inverse-mapping (s/map-invert mapping)]
    (inverse-mapping char)))

(defn process [keyword message operation]
  (apply str (map operation (cycle keyword) message)))

(defn encode [keyword message]
  (process keyword message encode-char))

(defn decode [keyword message]
  (process keyword message decode-char))

(defn decipher [cipher message]
  "decypherme")
