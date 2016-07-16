(ns card-game-war.game)

(def suits [:spade :club :diamond :heart])
(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])
(def cards
  (for [suit suits
        rank ranks]
    [suit rank]))

(def suit first)
(def rank last)

(defn suit-value [card]
  (.indexOf suits (suit card)))

(defn rank-value [card]
  (.indexOf ranks (rank card)))

(defn winner [val1 val2]
  (let [c (compare val1 val2)]
    (cond
      (< c 0) :player2
      (= 0 c) :none
      (> c 0) :player1)))

(defn rank-winner [card1 card2]
  (winner (rank-value card1) (rank-value card2)))

(defn suit-winner [card1 card2]
  (winner (suit-value card1) (suit-value card2)))

(defn play-round [player1-card player2-card]
  (let [rank-winner (rank-winner player1-card player2-card)]
    (if (not (= :none rank-winner))
      rank-winner
      (suit-winner player1-card player2-card))))

(defn play-game [player1-cards player2-cards]
  (cond
    (empty? player1-cards) :player2
    (empty? player2-cards) :player1
    true (let [card1 (first player1-cards)
               hand1 (vec (rest player1-cards))
               card2 (first player2-cards)
               hand2 (vec (rest player2-cards))]
           (if (= :player1 (play-round card1 card2))
             (recur (conj hand1 card1 card2) hand2)
             (recur hand1 (conj hand2 card1 card2))))))
