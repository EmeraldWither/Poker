package org.emeraldcraft.apcsa.poker.selector;

import java.util.Scanner;

import org.emeraldcraft.apcsa.poker.Hand;

public class SelectorThread extends Thread{
    private final Selector.OnSelectEvent onSelectEvent;
    private final Hand hand;
    private final Scanner scanner = new Scanner(System.in);
    private int selected = 1;
    private boolean stopped = false;
    private final Selector selector;
    public SelectorThread(Selector selector, Selector.OnSelectEvent onSelectEvent, Hand hand) {
        this.onSelectEvent = onSelectEvent;
        this.hand = hand;
        this.selector = selector;
    }

    @Override
    public void run(){
        showDeckAndSelector();
        while (!stopped){
            System.out.print("Enter an action: ");
            String action = scanner.next();
            if(action.equalsIgnoreCase("h")){
                //Show help page
                System.out.println(
                        "Please enter a number to select that card [1-5], or run a command\n" +
                        "Commands: \n" +
                        "[S]umbit Cards\n" +
                        "[C]hange Cards\n" +
                        "[H]elp");
                continue;
            }
            try{
                int actionNum = Integer.parseInt(action);
                if(actionNum < 1 || actionNum > 5){
                    System.out.println("Please enter a valid action (type H for help)");
                    continue;
                }
                selected = actionNum;
                action = " ";
            } catch(NumberFormatException ignored){}
            if(onSelectEvent.run(selector, selected, action.charAt(0))){
                //fill screen with new lines
                for (int i = 0; i < 100; ++i) System.out.println();
                showDeckAndSelector();
            }
        }

    }
    public void showDeckAndSelector(){
        Hand.printDeck(hand);
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
