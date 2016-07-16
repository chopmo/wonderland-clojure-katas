(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]))


(deftest test-play-round
  (testing "the highest rank wins the cards in the round"
    (is (= :player1
           (play-round [:diamond 1]
                       [:spade 1])))
    (is (= :player2
           (play-round [:club 10]
                       [:heart 10]))))

  (testing "queens are higher rank than jacks"
    (is (= :player1
           (play-round [:spade :queen]
                       [:club :jack]))))

  (testing "kings are higher rank than queens"
    (is (= :player2
           (play-round [:spade :queen]
                       [:spade :king]))))

  (testing "aces are higher rank than kings"
    (is (= :player1
           (play-round [:spade :ace]
                       [:spade :king]))))

  (testing "if the ranks are equal, clubs beat spades")
  (testing "if the ranks are equal, diamonds beat clubs")
  (testing "if the ranks are equal, hearts beat diamonds"))

(deftest test-play-game
  (testing "the player loses when they run out of cards"
    (is (= :player1
           (play-game
            [[:spade 2]]
            [])))
    (is (= :player2
           (play-game
            []
            [[:spade 2]]))))

  (testing "multiple rounds are played"
    (is (= :player1
           (play-game
            [[:spade 3]]
            [[:spade 2]]))))

  (testing "the best deck wins"
    (is (= :player1
           (play-game
            [[:spade 10]
             [:spade 3]
             [:spade 8]]
            [[:spade 2]
             [:spade 4]
             [:spade 5]])))))
