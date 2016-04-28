package edu.bsu.cs222.statsanalyzer;


public class MostPlayedChampion implements Comparable<MostPlayedChampion>{
    private String name;
    private int timesPlayed;

    public MostPlayedChampion(String champName, int champTimesPlayed){
        name = champName;
        timesPlayed = champTimesPlayed;
    }

    public int compareTo(MostPlayedChampion other) {
        return ((Integer)other.timesPlayed).compareTo((timesPlayed));
    }

    public String getChampionName(){
        return name;
    }

    public int getTimesPlayed(){
        return timesPlayed;
    }
}
