package org.emeraldcraft.apcsa.poker;

public class GameResult 
{
    public enum Result
    {
        WON, 
        LOST,
        TIED
    }
    private final boolean isWinning;
    private final boolean isTied;
    public GameResult(boolean isWinning, boolean isTied)
    {
        this.isWinning = isWinning;
        this.isTied = isTied;
    }
    public GameResult.Result getResult()
    {
        if(isWinning) return Result.WON;
        if(isTied) return Result.TIED;
        return Result.LOST;
    }
    
}
