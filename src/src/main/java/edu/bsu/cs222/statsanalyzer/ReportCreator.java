package edu.bsu.cs222.statsanalyzer;

import com.robrua.orianna.type.core.stats.AggregatedStats;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


public class ReportCreator {
    private DecimalFormat df = new DecimalFormat(("#.##"));

    protected String statisticReportCreator(AggregatedStats playerStatsObject){
        df.setRoundingMode(RoundingMode.DOWN);
        int totalGamesPlayed = playerStatsObject.getTotalGamesPlayed();
        int totalLosses = playerStatsObject.getTotalLosses();
        String statReport = "";
        statReport += "Ranked Games Played: ";
        statReport += totalGamesPlayed;
        if(totalGamesPlayed == 0){
            totalGamesPlayed++;
        }
        double totalGamesPlayedD = totalGamesPlayed;
        statReport += "\nTotal Wins: ";
        statReport += playerStatsObject.getTotalWins();
        statReport += "\nTotal Losses: ";
        statReport += totalLosses;
        if (totalLosses == 0){
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
        if(playerStatsObject.getTotalDeaths() != 0){
            statReport += df.format(((playerStatsObject.getTotalKills() /  totalGamesPlayedD)
                    + (playerStatsObject.getTotalAssists() / totalGamesPlayedD))
                    / ((double)playerStatsObject.getTotalDeaths() / totalGamesPlayedD));
        }else{
            statReport += df.format(playerStatsObject.getAverageKills() + playerStatsObject.getAverageAssists());

        }
        statReport += "\n";
        return statReport;
    }

    @SuppressWarnings("unchecked")
    //This method will always work even though it is unchecked.
    protected String mostPlayedChampSorter(HashMap<String, Integer> champMap){
        Object[] a = champMap.entrySet().toArray();
        Arrays.sort(a, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((HashMap.Entry<String, Integer>) o2).getValue().compareTo(((HashMap.Entry<String, Integer>) o1).getValue());
            }
        });
        String sortedMap = "";
        for (Object e : a) {
            sortedMap = sortedMap + (((Map.Entry<String, Integer>) e).getKey() +
                    ": Played " + ((Map.Entry<String, Integer>) e).getValue()) + " times" + "\n";
        }
        return sortedMap;
    }
}

