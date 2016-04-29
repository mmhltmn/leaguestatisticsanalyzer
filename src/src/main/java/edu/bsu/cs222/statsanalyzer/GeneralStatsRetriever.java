package edu.bsu.cs222.statsanalyzer;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.stats.AggregatedStats;
import com.robrua.orianna.type.core.stats.ChampionStats;

import java.util.Map;

public class GeneralStatsRetriever {
    private int rankedGamesPlayed;
    private int totalWins;
    private int totalLosses;
    private double winLossRatio;
    private double avgChampionsKilled;
    private double avgAssists;
    private double avgDeaths;
    private double avgKillDeathRatio;

    public GeneralStatsRetriever(Map statMap) {
        AggregatedStats playersStatsObject = pullStatsObjectFromMap(statMap);
        setFieldsFromStatsObject(playersStatsObject);
    }

    private AggregatedStats pullStatsObjectFromMap(Map statMap) {
        Champion avgChampion = RiotAPI.getChampionByID(0);
        ChampionStats avgChampStats = (ChampionStats) statMap.get(avgChampion);
        return avgChampStats.getStats();
    }

    private void setFieldsFromStatsObject(AggregatedStats playerStatsObject) {
        rankedGamesPlayed = playerStatsObject.getTotalGamesPlayed();
        totalWins = playerStatsObject.getTotalWins();
        totalLosses = playerStatsObject.getTotalLosses();
        winLossRatio = calculateWinLossRatio();
        setAvgFields(playerStatsObject);
    }

    private void setAvgFields(AggregatedStats playerStatsObject){
        double rankedGamesPlayedDivision = calculateGamesPlayedForDivision();
        avgChampionsKilled = (playerStatsObject.getTotalKills() / rankedGamesPlayedDivision);
        avgAssists = (playerStatsObject.getTotalAssists() / rankedGamesPlayedDivision);
        avgDeaths = (playerStatsObject.getTotalDeaths() / rankedGamesPlayedDivision);
        avgKillDeathRatio = calculateAvgKillDeathRatio();
    }

    private double calculateWinLossRatio(){
        if (totalLosses != 0) {
            return (double)totalWins / (double)totalLosses;
        } else {
            return totalWins;
        }
    }

    private double calculateGamesPlayedForDivision(){
        if (rankedGamesPlayed != 0) {
            return rankedGamesPlayed + 1;
        } else {
            return rankedGamesPlayed;
        }
    }

    private double calculateAvgKillDeathRatio(){
        if (avgDeaths != 0) {
            return avgChampionsKilled + avgAssists / avgDeaths;
        } else {
            return avgChampionsKilled + avgAssists;
        }
    }

    public int getRankedGamesPlayed(){
        return rankedGamesPlayed;
    }

    public int getTotalWins(){
        return totalWins;
    }

    public int getTotalLosses(){
        return totalLosses;
    }

    public double getWinLossRatio(){
        return winLossRatio;
    }

    public double getAvgChampionsKilled(){
        return avgChampionsKilled;
    }

    public double getAvgAssists(){
        return avgAssists;
    }

    public double getAvgDeaths(){
        return avgDeaths;
    }

    public double getAvgKillDeathRatio(){
        return avgKillDeathRatio;
    }
}









