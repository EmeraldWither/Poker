package org.emeraldcraft.apcsa.poker;

import org.emeraldcraft.apcsa.poker.selector.Selector;

public class Poker {
    //README:
    // When using program, use a monospaced font for the best results (suggested. Fira Code)
    public static void main(String[] args) {
        DeckOfCards cards = new DeckOfCards();

        System.out.println("=======================================\n      WELCOME TO EMERQLD CASINO\n        Lets play some poker\n========================================");
        cards.shuffle();
        loadingAnimation("Hold on! We are shuffling your cards", "Your cards are ready", 4);
        Hand player1 = new Hand(cards);
        Hand player2 = new Hand(cards);

        //Hand out all of the cards
        System.out.println("Player 1 Cards:");
        //Organize our Decks
        organizeDeck(player1.getCards());
        organizeDeck(player2.getCards());
        //Print out the deck to the human
        Card.CardPrinter.printDeck(player1);
        //Start our card selector
        Selector selector = new Selector(
                //when a value is selected
                selectedValue -> {
                    System.out.println("Selected Value: " + selectedValue);
                    //reprint the cards
                    return true;

                }, player1);
        selector.start();
    }

    public static void organizeDeck(Card[] hand) {
        Card temp;
        for (int i = 0; i < hand.length; i++) {
            int minIndex = findMinimum(hand, i);
            if (minIndex != i) {
                //swap the cards
                temp = hand[i];
                hand[i] = hand[minIndex];
                hand[minIndex] = temp;
            }
        }
    }

    private static int findMinimum(Card[] hand, int first) {
        int minIndex = first;
        for (int i = first + 1; i < hand.length; i++) {
            if (hand[i].getValue() < hand[minIndex].getValue()) minIndex = i;
        }
        return minIndex;
    }

    public static void loadingAnimation(String during, String end, double duration) {
        int amount = (int) (duration / 0.5);
        for (int i = 0; i < amount; i++) {
            StringBuilder text = new StringBuilder(during);
            //Add a "." depending on the state
            for (int j = 0; j < i % 4; j++) {
                text.append(".");
            }
            //Input whitespace to be able to override previous
            text.append("   ");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Error: Sleeping thread has been interupted. Canceling animation.\n");
                return;
            }
            System.out.print(text + "\r");
        }
        StringBuilder text = new StringBuilder(end);
        //Whitespace based on the during string
        for (int i = 0; i < end.length(); i++) {
            text.append(" ");
        }
        System.out.println("\r" + text);
    }
}
