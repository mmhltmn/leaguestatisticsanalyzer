package edu.bsu.cs222.statsanalyzer;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.stats.AggregatedStats;
import com.robrua.orianna.type.core.stats.ChampionStats;

import java.io.FileNotFoundException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;


public class StatsRetriever {
    private DecimalFormat df = new DecimalFormat(("#.##"));
    private AggregatedStats playerStatsObject;
    private String statReport;

    public StatsRetriever(Map statMap) throws FileNotFoundException {
        playerStatsObject = pullStatsFromMap(statMap);
    }

    private AggregatedStats pullStatsFromMap(Map statMap) {
        Champion AvgChampion = RiotAPI.getChampionByID(0);
        ChampionStats avgChampStats = (ChampionStats) statMap.get(AvgChampion);
        return avgChampStats.getStats();
    }

    public String statisticReportCreator() {
        df.setRoundingMode(RoundingMode.DOWN);
        int totalGamesPlayed = playerStatsObject.getTotalGamesPlayed();
        int totalLosses = playerStatsObject.getTotalLosses();
        statReport += "Ranked Games Played: ";
        statReport += totalGamesPlayed;
        if (totalGamesPlayed == 0) {
            totalGamesPlayed++;
        }
        double totalGamesPlayedD = totalGamesPlayed;
        statReport += "\nTotal Wins: ";
        statReport += playerStatsObject.getTotalWins();
        statReport += "\nTotal Losses: ";
        statReport += totalLosses;
        if (totalLosses == 0) {
            totalLosses++;
        }
        double totalLossesD = totalLosses;
        statReport += "\nWin/Loss Ratio: ";
        statReport += df.format(playerStatsObject.getTotalWins() / totalLossesD);
        statReport += "\nAverage Champions Killed: ";
        statReport += df.format(playerStatsObject.getTotalKills() / totalGamesPlayedD);
        statReport += "\nAverage Assists: ";
        statReport += df.format(playerStatsObject.getTotalAssists() / totalGamesPlayedD);
        statReport += "\nAverage Number of Deaths: ";
        statReport += df.format(playerStatsObject.getTotalDeaths() / totalGamesPlayedD);
        statReport += "\nAverage Kill/Death Ratio: ";
        if (playerStatsObject.getTotalDeaths() != 0) {
            statReport += df.format(((playerStatsObject.getTotalKills() / totalGamesPlayedD)
                    + (playerStatsObject.getTotalAssists() / totalGamesPlayedD))
                    / ((double) playerStatsObject.getTotalDeaths() / totalGamesPlayedD));
        } else {
            statReport += df.format(playerStatsObject.getAverageKills() + playerStatsObject.getAverageAssists());

        }
        statReport += "\n";
        return statReport;
    }
}



