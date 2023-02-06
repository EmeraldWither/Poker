package org.emeraldcraft.apcsa.poker;

public class Hand {
    private final Card[] cards = new Card[5];

    public Hand(DeckOfCards deck) {
        for (int i = 0; i < cards.length; i++) {
            cards[i] = deck.dealCard();
        }
    }

    public static void printDeck(Hand hand) {
        Card[] cards = hand.getCards();
        StringBuilder card = new StringBuilder();
        //Add the top row
        for (int i = 0; i < 5; i++) card.append(" -----------      ");
        card.append("\n");
        //Print out the face row
        card.append(getFaceRow(cards));
        //print out suit
        card.append(getSuitRow(cards));
        //Put 2 walls
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                card.append("|           |     ");
            }
            card.append("\n");
        }
        //do above but in reverse
        card.append(getSuitRow(cards));
        card.append(getFaceRow(cards));
        for (int i = 0; i < 5; i++) card.append(" -----------      ");
        System.out.println(card);
    }

    public static String getFaceRow(Card[] hand) {
        StringBuilder card = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            if (hand[i].toShortString().length() == 2) {
                //exists so if we get "10", it does not mess with the spacing of the cards
                card.append("| ").append(hand[i].toShortString()).append("     ").append(hand[i].toShortString()).append(" |     ");
                continue;
            }
            card.append("| ").append(hand[i].toShortString()).append("       ").append(hand[i].toShortString()).append(" |     ");
        }
        card.append("\n");
        return card.toString();
    }

    public static String getSuitRow(Card[] hand) {
        StringBuilder card = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            card.append("| ").append(hand[i].getSuitUnicode()).append("       ").append(hand[i].getSuitUnicode()).append(" |     ");
        }
        card.append("\n");
        return card.toString();
    }

    public Card[] getCards() {
        return cards;
    }

    public boolean isWinning(Hand other) {

        if((getFlushValue() > other.getFlushValue()) || (getFlushValue() < other.getFlushValue())){
            return getFlushValue() > other.getFlushValue();
        }
        if((hasStraight() && !other.hasStraight()) || ((!hasStraight() && other.hasStraight()))){
            return hasStraight() && !other.hasStraight();
        }
        if((hasThreePair() && !other.hasThreePair()) || (!hasThreePair() && other.hasThreePair())){
            return hasThreePair() && !other.hasThreePair();
        }
        if((getPairValue() > other.getPairValue()) || (getPairValue() < other.getPairValue())){
            return getPairValue() > other.getPairValue();
        }
        if((getHighestCardValue() > other.getHighestCardValue()) || (getHighestCardValue() < other.getHighestCardValue())){
            return getHighestCardValue() > other.getHighestCardValue();
        }
        return false;
    }
    public double getFlushValue(){
        Card highestCard = null;
        double value = 0.0;
        for(int i = 1; i < cards.length; i++){
            if(!cards[i - 1].getSuit().equalsIgnoreCase(cards[i].getSuit())) return 0.0;
            if(highestCard == null){
                highestCard = cards[i];
            }
            else if(highestCard.getFaceValue() < cards[i].getFaceValue()){
                highestCard = cards[i];
            }
        }
        value +=5; 
        value += 0.01 * highestCard.getFaceValue();
        return value;
    }

    public boolean hasStraight() {
        Card previousValue = cards[0];
        boolean isStraight = true;
        //lowest straight
        for (int i = 1; i < cards.length - 1; i++) {
            if (previousValue.getFaceValue() != cards[i].getFaceValue() + 1) {
                isStraight = false;
                break;
            }
        }
        if (isStraight && (cards[4].getFace().equalsIgnoreCase("Ace") && cards[3].getFace().equalsIgnoreCase("King") || (cards[4].getFaceValue() == 14 && cards[0].getFaceValue() == 2)  || (previousValue.getFaceValue() == cards[4].getFaceValue() + 1)))
            isStraight = true;
        if (!isStraight) {
            boolean isHighestStraight = true;
            //Check highest straight
            previousValue = cards[0];
            for (int i = 1; i < cards.length; i++) {
                Card card = cards[i];
                if (previousValue.getFaceValue() + 1 != card.getFaceValue()) {
                    isHighestStraight = false;
                    break;
                }
                previousValue = cards[i];
            }
            isStraight = isHighestStraight;
        }
        return isStraight;
    }

    public boolean hasThreePair() {
        int[] suits = {0, 0, 0, 0};
        int[] faces = new int[14];
        //0 = hearts
        //1 - diamonds
        //2 - clubs
        //3 - spades
        for (Card card : cards) {
            if (card.getSuit().equalsIgnoreCase("Hearts")) suits[0]++;
            if (card.getSuit().equalsIgnoreCase("Diamonds")) suits[1]++;
            if (card.getSuit().equalsIgnoreCase("Clubs")) suits[2]++;
            if (card.getSuit().equalsIgnoreCase("Spades")) suits[3]++;

            faces[card.getFaceValue() - 1]++;
        }
        //Now loop over faces
        for (int i : suits) if (i >= 3) return true;
        //Now check for values

        for (int i : faces) if (i >= 3) return true;

        return false;
    }

    public double getPairValue() {
        double value = 0;
        double multiplier = 0.1;
        // find highest pair
        int highestPairIndex = -1;
        int secondHighestPairValue = 0;
        for (int i = 0; i < cards.length - 1; i++) {
            if (cards[i].getFaceValue() == cards[i + 1].getFaceValue()) {
                secondHighestPairValue = cards[i].getFaceValue();
                if (highestPairIndex == -1)
                    highestPairIndex = i;
                else if (cards[highestPairIndex].getFaceValue() < cards[i].getFaceValue())
                    highestPairIndex = i;
            }
        }

        // Include pair type
        if (highestPairIndex != -1) {
            // Found a pair
            value += 1;
            value += cards[highestPairIndex].getFaceValue() * multiplier;
            multiplier *= 0.01;
            // now loop over array and check for the ones that we need and add it to it
            for (int j = 4; j >= 0; j--) {
                if (j == highestPairIndex || j == highestPairIndex + 1) {
                    continue;
                }
                value += (cards[j].getFaceValue() + secondHighestPairValue) * multiplier;
                multiplier *= 0.01;
            }
        }
        return value;
    }
    public double getHighestCardValue(){
        double value = 0.0;
        double multiplier = 0.01;
        for (int i = 4; i >= 0; i--) {
            value += cards[i].getFaceValue() * multiplier;
            multiplier *= 0.01;
        }
        return value;
    }
}
