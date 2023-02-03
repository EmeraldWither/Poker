package org.emeraldcraft.apcsa.poker;

public class Hand {
    private final Card[] cards = new Card[5];

    public Hand(DeckOfCards deck){
        for (int i = 0; i < cards.length; i++) {
            cards[i] = deck.dealCard();
        }
    }
    public Card[] getCards() {
        return cards;
    }
}
