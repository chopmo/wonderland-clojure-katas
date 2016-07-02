(ns magic-square.puzzle
  (:require [clojure.math.combinatorics :as combo]))

(def values [1.0 1.5 2.0 2.5 3.0 3.5 4.0 4.5 5.0])

(defn valid-square? [s]
  (let [sum-rows (fn [m]
                   (map #(reduce + %) m))
        sum-cols (fn [m]
                   [(reduce + (map first m))
                    (reduce + (map second m))
                    (reduce + (map last m))])
        sum-diagonals (fn [m]
                        [(+ (get-in m [0 0]) (get-in m [1 1]) (get-in m [2 2]))
                         (+ (get-in m [2 0]) (get-in m [1 1]) (get-in m [0 2]))])]
    (and (= (set (sum-rows s))
            (set (sum-cols s))
            (set (sum-diagonals s)))
         (= 1
            (count (set (sum-rows s)))
            (count (set (sum-cols s)))
            (count (set (sum-diagonals s)))))))

(defn build-square [values]
  (let [square-as-lists (partition 3 values)]
    (vec (map vec square-as-lists))))

(defn all-magic-squares [values]
  (->>
   (combo/permutations values)
   (map build-square)
   ))

(defn valid-magic-squares [values]
  (filter valid-square? (all-magic-squares values)))

(def magic-square
  (memoize (fn [values]
             (first (valid-magic-squares values)))))

;; [[1.0 1.5 2.0]
;;     [2.5 3.0 3.5]
;;     [4.0 4.5 5.0]]
