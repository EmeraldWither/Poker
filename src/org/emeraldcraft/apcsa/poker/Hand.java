package org.emeraldcraft.apcsa.poker;

public class Hand {
    private final Card[] cards = new Card[5];

    public Hand(DeckOfCards deck) {
        for (int i = 0; i < cards.length; i++) {
            cards[i] = deck.dealCard();
        }
    }

    public Card[] getCards() {
        return cards;
    }

    public double getValue() {
        double value = 0.0;
        double multiplier = 0.01;
        // Hunt for pairs
        // put in {} so we can minimize in IDE
        {
            // find highest pair
            int highestPairIndex = -1;
            int secondHighestPairValue = 0;
            for (int i = 0; i < cards.length - 1; i++) {
                if (cards[i].getValue() == cards[i + 1].getValue()) {
                    secondHighestPairValue = cards[i].getValue();
                    if (highestPairIndex == -1)
                        highestPairIndex = i;
                    else if (cards[highestPairIndex].getValue() < cards[i].getValue())
                        highestPairIndex = i;
                
                }
            }

            // Include pair type
            if (highestPairIndex != -1) {
                // Found a pair
                value = 2;
                value += cards[highestPairIndex].getValue() * multiplier;
                multiplier *= 0.01;
                // now loop over array and check for the ones that we need and add it to it
                for (int j = 4; j >= 0; j--) {
                    if (j == highestPairIndex || j == highestPairIndex + 1) {
                        continue;
                    }
                    value += (cards[j].getValue() + secondHighestPairValue) * multiplier;
                    multiplier *= 0.01;
                }
                return value;
            }
        }
        // Hunt for the highest card
        value = 1;
        for (int i = 4; i >= 0; i--) {
            value += cards[i].getValue() * multiplier;
            multiplier *= 0.01;
        }
        return value;
    }
}
