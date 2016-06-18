(ns doublets.solver
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]
            [clojure.set :as set]))

(def words (-> "words.edn"
               (io/resource)
               (slurp)
               (read-string)
               (set)))

(defn doublets [word1 word2]
  (vec (first (solutions word1 word2))))

(defn solutions [w1 w2]
  (filter #(= w2 (last %)) (chains w1)))

(defn chains [w]
  (tree-seq branch? children [w]))

;; Helper functions for building the tree
(defn children [ws]
  (let [w (last ws)
        unused-words (set/difference words (set ws))
        neighbours (filter #(neighbours? w %) unused-words)]
    (map #(conj ws %) neighbours)))

(defn branch? [ws]
  (seq (children ws)))

(defn neighbours? [w1 w2]
  (let [pairs (map vector w1 w2)
        diffs (filter #(not (apply = %)) pairs)]
    (and
     (= 1 (count diffs))
     (= (count w1) (count w2)))))
