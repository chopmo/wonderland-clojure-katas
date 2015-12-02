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
