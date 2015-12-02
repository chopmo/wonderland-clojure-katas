(ns wonderland-number.finder)

(defn six-digits? [n]
  (= 6 (count (str n))))

(defn same-digits? [n1 n2]
  (let [s1 (set (str n1))
        s2 (set (str n2))]
    (= s1 s2)))


(defn wonderland-number? [n]
  (and
   (six-digits? n)
   (every? #(same-digits? n (* % n)) '(2 3 4 5 6))
   n))

(defn wonderland-number []
  (let [candidates (range 1 999999)]
    (some wonderland-number? candidates)))


;; Continue on with the Wonderland Number kata. If you have finished the
;; exercise early, you might think of finding other special numbers. For
;; example, what about numbers under 1,000 that are equal to the sum of the
;; cubes of its digits?
(defn digits [n] ; There must be an easier way
  (letfn [(to-digit [c] (- (int c) (int \0)))]
    (map to-digit (str n))))

(defn cube [n]
  (* n n n))

(defn sum-cubed-digits-number? [n]
  (and
   (< n 1000)
   (= n (apply + (map cube (digits n))))
   n))

(defn sum-cubed-digits-number []
  (filter sum-cubed-digits-number? (range 1000)))
