package org.emeraldcraft.apcsa.poker;

import java.util.Arrays;

public class Poker {
    public static void main(String[] args) {
        DeckOfCards cards = new DeckOfCards();
        Card[] player1 = new Card[5];
        Card[] player2 = new Card[5];
        System.out.println("=======================================\n      WELCOME TO EMERQLD CASINO\n        Lets play some poker\n========================================");
        cards.shuffle();
        loadingAnimation("Hold on! We are shuffling your cards", "Your cards are ready", 4);

        //Hand out all of the cards
        System.out.println("Player 1 Cards:");
        for (int i = 0; i < player1.length; i++) {
            player1[i] = cards.dealCard();
        }
        for (int i = 0; i < player2.length; i++) {
            player2[i] = cards.dealCard();
        }
        //Organize our Decks
        organizeDeck(player1);
        organizeDeck(player2);
        System.out.println(Arrays.toString(player1));
        System.out.println(Arrays.toString(player2));
    }
    public static void organizeDeck(Card[] hand){
        Card temp;
        for(int i = 0; i < hand.length; i++){
            int minIndex = findMinimum(hand, i);
            if(minIndex != i){
                //swap the cards
                temp = hand[i];
                hand[i] = hand[minIndex];
                hand[minIndex] = temp;
            }
        }
    }
    private static int findMinimum(Card[] hand, int first) {
        int minIndex = first;
        for(int i = first + 1; i < hand.length; i++){
            if(hand[i].getValue() < hand[minIndex].getValue()) minIndex = i;
        }
        return minIndex;
    }
    public static void loadingAnimation(String during, String end, double duration) {
        int amount = (int) (duration/0.5);
        for(int i = 0; i < amount; i++){
            String text = during;
            //Add a "." depending on the state
            for(int j = 0; j < i % 4; j++){
                text += ".";
            }
            //Input whitespace to be able to override previous
            text += "   ";
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Error: Sleeping thread has been interupted. Canceling animation.\n");
                return;
            }
            System.out.print(text + "\r");
        }
        String text = end;
        //Whitespace based on the during string
        for (int i = 0; i < end.length(); i++) {
            text += " ";
        }
        System.out.println("\r" + text);
    }
}
