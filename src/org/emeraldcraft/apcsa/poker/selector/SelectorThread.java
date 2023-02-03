package org.emeraldcraft.apcsa.poker.selector;

import org.emeraldcraft.apcsa.poker.Card;
import org.emeraldcraft.apcsa.poker.Hand;

import java.util.Scanner;

public class SelectorThread extends Thread{
    private final Selector.OnSelectEvent onSelectEvent;
    private final Hand hand;
    private final Scanner scanner = new Scanner(System.in);
    private int selected = 1;
    private boolean stopped = false;
    public SelectorThread(Selector.OnSelectEvent onSelectEvent, Hand hand) {
        this.onSelectEvent = onSelectEvent;
        this.hand = hand;
    }

    @Override
    public void run(){
        showDeckAndSelector();
        while (!stopped){
            System.out.print("Enter an action: ");
            int action = scanner.nextInt();
            if(action < 1 || action > 5){
                System.out.println("invalid action");
                continue;
            }
            selected = action;
            if(onSelectEvent.run(action)){
                //fill screen with new lines
                for (int i = 0; i < 100; ++i) System.out.println();
                showDeckAndSelector();
            }
        }

    }
    public void showDeckAndSelector(){
        Card.CardPrinter.printDeck(hand);
                for(int i = 0; i < selected; i++){
                    if(i + 1 == selected){
                        System.out.println("      ^");
                    }
                    else System.out.print("                  ");
                }
    }

    public int getSelected() {
        return this.selected;
    }

    public void end() {
        stopped = true;
    }
}
