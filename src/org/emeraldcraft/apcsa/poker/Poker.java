package org.emeraldcraft.apcsa.poker;

import java.util.Scanner;

import org.emeraldcraft.apcsa.poker.GameResult.Result;
import org.emeraldcraft.apcsa.poker.selector.Selector;

public class Poker 
{
    private static int balance = 0;
    //README:
    // When using program, use a monospaced font for the best results (suggested. Fira Code)
    public static void main(String[] args) 
    {
        System.out.println("=========================================================================================================================\n" +
                "     ███████╗███╗   ███╗███████╗██████╗  ██████╗ ██╗     ██████╗    █████╗  █████╗  ██████╗██╗███╗  ██╗ █████╗ \n" +
                "     ██╔════╝████╗ ████║██╔════╝██╔══██╗██╔═══██╗██║     ██╔══██╗  ██╔══██╗██╔══██╗██╔════╝██║████╗ ██║██╔══██╗   \n" +
                "     █████╗  ██╔████╔██║█████╗  ██████╔╝██║██╗██║██║     ██║  ██║  ██║  ╚═╝███████║╚█████╗ ██║██╔██╗██║██║  ██║   \n" +
                "     █╔══╝   ██║╚██╔╝██║██╔══╝  ██╔══██╗╚██████╔╝██║     ██║  ██║  ██║  ██╗██╔══██║ ╚═══██╗██║██║╚████║██║  ██║\n" +
                "     ███████╗██║ ╚═╝ ██║███████╗██║  ██║ ╚═██╔═╝ ███████╗██████╔╝  ╚█████╔╝██║  ██║██████╔╝██║██║ ╚███║╚█████╔╝\n" +
                "     ╚══════╝╚═╝     ╚═╝╚══════╝╚═╝  ╚═╝   ╚═╝   ╚══════╝╚═════╝    ╚════╝ ╚═╝  ╚═╝╚═════╝ ╚═╝╚═╝  ╚══╝ ╚════╝ \n" +
                "=========================================================================================================================");
        runGame();
    }
    public static void runGame()
    {
        DeckOfCards deck = new DeckOfCards();
        System.out.print("\nPlace your bet: ");

        int bet = new Scanner(System.in).nextInt();
        System.out.printf("You have bet $%s. Lets start the game\n\n\n", bet);


        deck.shuffle();
        loadingAnimation("We are shuffling your cards", "", 4);
        Hand player1 = new Hand(deck);
        Hand player2 = new Hand(deck);

        //Hand out all of the cards
        //Organize our Decks
        organizeDeck(player1.getCards());
        organizeDeck(player2.getCards());
        // System.out.println("player 2 deck");
        // Hand.printDeck(player2);
        System.out.println("Player 1 Cards:");
        //Start our card selector
        int[] changed = {0};
        Selector selector = new Selector(
                //callback for when a value is selected
                (cardSelector, selectedValue, action) -> {
                    //Action 'q' = Submit
                    if (action == 'p') 
                    {
                        //remove any mystery cards
                        for (int i = 0; i < 5; i++) 
                        {
                            if (player1.getCards()[i].getFaceValue() == -1) player1.getCards()[i] = deck.dealCard();
                        }
                        organizeDeck(player1.getCards());
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                                "Computer Deck");
                        Hand.printDeck(player2);

                        System.out.println("Your Deck:");
                        Hand.printDeck(player1);
                        GameResult result = player1.isWinning(player2);
                        if (result.getResult() == Result.WON)
                        {
                            System.out.println("You Won!");
                            balance += bet;
                        }
                        if (result.getResult() == Result.LOST)
                        {
                            System.out.println("You lost :(");
                            balance -= bet;
                        }
                        if (result.getResult() == Result.TIED)
                        {
                            System.out.println("You tied.");
                            balance += bet/2;
                        }
                        if(balance > 0)
                        {
                            System.out.println("Current Balance: " + balance);
                            cardSelector.pause();
                            runGame();
                            return false;
                        }
                        System.out.println("Your gambling addiction did not pay off. You are now broke, and have to leave the casino empty handed. You actually owe me $" + Math.abs(balance) +". \n*You have 3 days*");
                        cardSelector.pause();
                        return false;
                    }
                    if (action == 's' && changed[0] < 3) 
                    {
                        if (player1.getCards()[cardSelector.getSelected()].getFaceValue() == -1) return false;
                        player1.getCards()[cardSelector.getSelected()] = new Card("?", "?");
                        changed[0]++;
                    }
                    //reprint the cards
                    return true;
                }, player1);
        selector.start();
    }

    public static void organizeDeck(Card[] hand) 
    {
        Card temp;
        for (int i = 0; i < hand.length; i++) 
        {
            int minIndex = findMinimum(hand, i);
            if (minIndex != i)
             {
                //swap the cards
                temp = hand[i];
                hand[i] = hand[minIndex];
                hand[minIndex] = temp;
            }
        }
    }
    private static int findMinimum(Card[] hand, int first) 
    {
        int minIndex = first;
        for (int i = first + 1; i < hand.length; i++) 
        {
            if (hand[i].getFaceValue() < hand[minIndex].getFaceValue()) minIndex = i;
        }
        return minIndex;
    }

    public static void loadingAnimation(String during, String end, double duration) 
    {
        //get our duration for the animations loop
        int amount = (int) (duration / 0.5);
        for (int i = 0; i < amount; i++) 
        {
            StringBuilder text = new StringBuilder(during);
            //Add a "." depending on the state
            for (int j = 0; j < i % 4; j++) 
            {
                text.append(".");
            }
            //Input whitespace to be able to override previous
            text.append("   ");
            //try/catch sleep
            try 
            {
                Thread.sleep(500);
            } catch (InterruptedException e) 
            {
                System.out.println("Error: Sleeping thread has been interupted. Canceling animation.\n");
                return;
            }
            System.out.print(text + "\r");
        }
        StringBuilder text = new StringBuilder(end);
        //Whitespace based on the during string
        for (int i = 0; i < during.length() + 4; i++) 
        {
            text.append(" ");
        }
        System.out.println("\r" + text);
    }
}
