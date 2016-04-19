package edu.bsu.cs222.statsanalyzer;

import com.robrua.orianna.type.core.stats.AggregatedStats;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class GeneralStatsReport {
    private String statReportText;

    public GeneralStatsReport(AggregatedStats playerStatsObject) {
        statisticReportCreator(playerStatsObject);
    }

    private void statisticReportCreator(AggregatedStats playerStatsObject) {
        DecimalFormat df = new DecimalFormat(("#.##"));
        df.setRoundingMode(RoundingMode.DOWN);
        int totalGamesPlayed = playerStatsObject.getTotalGamesPlayed();
        int totalLosses = playerStatsObject.getTotalLosses();
        statReportText += "Ranked Games Played: ";
        statReportText += totalGamesPlayed;
        if (totalGamesPlayed == 0) {
            totalGamesPlayed++;
        }
        double totalGamesPlayedD = totalGamesPlayed;
        statReportText += "\nTotal Wins: ";
        statReportText += playerStatsObject.getTotalWins();
        statReportText += "\nTotal Losses: ";
        statReportText += totalLosses;
        if (totalLosses == 0) {
            totalLosses++;
        }
        double totalLossesD = totalLosses;
        statReportText += "\nWin/Loss Ratio: ";
        statReportText += df.format(playerStatsObject.getTotalWins() / totalLossesD);
        statReportText += "\nAverage Champions Killed: ";
        statReportText += df.format(playerStatsObject.getTotalKills() / totalGamesPlayedD);
        statReportText += "\nAverage Assists: ";
        statReportText += df.format(playerStatsObject.getTotalAssists() / totalGamesPlayedD);
        statReportText += "\nAverage Number of Deaths: ";
        statReportText += df.format(playerStatsObject.getTotalDeaths() / totalGamesPlayedD);
        statReportText += "\nAverage Kill/Death Ratio: ";
        if (playerStatsObject.getTotalDeaths() != 0) {
            statReportText += df.format(((playerStatsObject.getTotalKills() / totalGamesPlayedD)
                    + (playerStatsObject.getTotalAssists() / totalGamesPlayedD))
                    / ((double) playerStatsObject.getTotalDeaths() / totalGamesPlayedD));
        } else {
            statReportText += df.format(playerStatsObject.getAverageKills() + playerStatsObject.getAverageAssists());

        }
        statReportText += "\n";
    }

    public String getGeneralStatsReport(){
        return statReportText;
    }
}
