(ns card-game-war.game)

;; feel free to use these cards or use your own data structure
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

(defn play-game [player1-cards player2-cards])
