package edu.bsu.cs222.statsanalyzer;

public class GameItem {
    private String name;
    private int gamesPlayed;
    private double winRate;

    public GameItem(String itemName, int[] winLoss){
        name = itemName;
        int wins = winLoss[0];
        int losses = winLoss[1];
        gamesPlayed = wins + losses;
        winRate = ((double)wins/(double)gamesPlayed);
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
