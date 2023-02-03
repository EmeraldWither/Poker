package org.emeraldcraft.apcsa.poker.selector;

import org.emeraldcraft.apcsa.poker.Hand;

public class Selector {
    public interface OnSelectEvent {
        boolean run(int selectedValue);
    }

    private final SelectorThread selectorThread;
    public Selector(OnSelectEvent onSelectCard, Hand hand) {
        selectorThread = new SelectorThread(onSelectCard, hand);
    }
    public int getSelected() {
        return selectorThread.getSelected();
    }
    public void start(){
        selectorThread.start();
    }
    public void pause(){
        selectorThread.end();
    }
}
