package org.emeraldcraft.apcsa.poker;

public class Poker {
    public static void main(String[] args) {
        DeckOfCards cards = new DeckOfCards();
        Card[] player1 = new Card[5];
        Card[] player2 = new Card[5];
        cards.shuffle();
        System.out.println("=======================================\n      WELCOME TO EMERQLD CASINO\n        Lets play some poker\n========================================");
        //Hand out all of the cards
        System.out.println("Player 1 Cards:");
        for (int i = 0; i < player1.length; i++) {
            player1[i] = cards.dealCard();
            System.out.println("Card " + (i + 1) + ":  " + player1[i]);
        }
        System.out.println("\n\nPlayer 2 Cards: ");
        for (int i = 0; i < player2.length; i++) {
            player2[i] = cards.dealCard();
            System.out.println("Card " + (i + 1) + ":  " + player2[i]);
        }
    }
}
