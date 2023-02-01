package org.emeraldcraft.apcsa.poker;

public class Poker {
    public static void main(String[] args) throws InterruptedException {
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
            System.out.println("Card " + (i + 1) + ":  " + player1[i]);
        }
        System.out.println("\n\nPlayer 2 Cards: ");
        for (int i = 0; i < player2.length; i++) {
            player2[i] = cards.dealCard();
            System.out.println("Card " + (i + 1) + ":  " + player2[i]);
        }
    }
    public static void loadingAnimation(String during, String end, double duration) throws InterruptedException{
        int amount = (int) (duration/0.5);
        for(int i = 0; i < amount; i++){
            String text = during;
            //Add a "." depending on the state
            for(int j = 0; j < i % 4; j++){
                text += ".";
            }
            //Input whitespace to be able to override previous
            text += "   ";
            Thread.sleep(500);
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
