package edu.bsu.cs222.statsanalyzer;

public class GameItem {
    private String name;
    private int wins;
    private int losses;
    private int gamesPlayed;
    private double winRate;

    public GameItem(String itemName, int[] winLoss){
        name = itemName;
        wins = winLoss[0];
        losses = winLoss[1];
        gamesPlayed = wins + losses;
        winRate = (double)(wins/gamesPlayed);
    }

    public String getName(){
        return name;
    }

    public int getGamesPlayed(){
        return gamesPlayed;
    }

    public double getWinRate(){
        return winRate;
    }
}
