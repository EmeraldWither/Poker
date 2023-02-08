package org.emeraldcraft.apcsa.poker.selector;

import org.emeraldcraft.apcsa.poker.Hand;

public class Selector
 {
    public interface OnSelectEvent 
    {
        boolean run(Selector selector, int selectedValue, char action);
    }

    private final SelectorThread selectorThread;
    public Selector(OnSelectEvent onSelectCard, Hand hand)
    {
        selectorThread = new SelectorThread(this, onSelectCard, hand);
    }
    public void showDeckAndSelector()
    {
        selectorThread.showDeckAndSelector();
    }
    public int getSelected() 
    {
        return selectorThread.getSelected() - 1;
    }
    public void start()
    {
        selectorThread.start();
    }
    public void pause()
    {
        selectorThread.end();
    }
}
