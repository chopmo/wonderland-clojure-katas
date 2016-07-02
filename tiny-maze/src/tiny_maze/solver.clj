(ns tiny-maze.solver)

(defn neighbours [[row col]]
  [[(dec row) col]
   [(inc row) col]
   [row (dec col)]
   [row (inc col)]])

(defn filter-unvisited [path positions]
  (let [unvisited? (fn [position]
                     (not ((set path) position)))]
    (filter unvisited? positions)))

(defn find-path [maze path position]
  (let [new-path (conj path position)]
    (case (get-in maze position)
      :E new-path
      (:S 0) (->> (neighbours position)
                  (filter-unvisited path)
                  (map #(find-path maze new-path %))
                  (filter identity)
                  (first))
      nil)))


(defn render-path [maze path]
  (reduce #(assoc-in %1 %2 :x) maze path))

(defn solve-maze [maze]
  (let [path (find-path maze [] [0 0])]
    (render-path maze path)))
