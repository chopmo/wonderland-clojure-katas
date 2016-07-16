(ns card-game-war.game)

(def suits [:spade :club :diamond :heart])
(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])
(def cards
  (for [suit suits
        rank ranks]
    [suit rank]))

(defn suit-value [card]
  (.indexOf suits (first card)))

(defn rank-value [card]
  (.indexOf ranks (last card)))

(defn play-round [player1-card player2-card]
  (if (= (rank-value player1-card) (rank-value player2-card))
   (if (< (suit-value player1-card) (suit-value player2-card))
     :player2
     :player1)
   (if (< (rank-value player1-card) (rank-value player2-card))
     :player2
     :player1)))

(defn play-game [player1-cards player2-cards]
  (if (empty? player1-cards)
    :player2
    (if (empty? player2-cards)
      :player1
      (let [p1-card (first player1-cards)
            p1-hand (vec (rest player1-cards))
            p2-card (first player2-cards)
            p2-hand (vec (rest player2-cards))]
        (if (= :player1 (play-round p1-card p2-card))
          (recur (conj p1-hand p1-card p2-card) p2-hand)
          (recur p1-hand (conj p2-hand p1-card p2-card)))))))
