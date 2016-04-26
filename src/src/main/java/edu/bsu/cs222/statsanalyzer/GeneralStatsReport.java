package edu.bsu.cs222.statsanalyzer;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.summoner.Summoner;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;

public class GeneralStatsReport {
    private String statReportText;

    public GeneralStatsReport(Map statMap){
        GeneralStatsRetriever statsRetriever = new GeneralStatsRetriever(statMap);
        statisticReportCreator(statsRetriever);
    }

    private void statisticReportCreator(GeneralStatsRetriever statsRetriever) {
        DecimalFormat df = createDecimalFormat();
        statReportText += "Ranked Games Played: ";
        statReportText += statsRetriever.getRankedGamesPlayed();
        statReportText += "\nTotal Wins: ";
        statReportText += statsRetriever.getTotalWins();
        statReportText += "\nTotal Losses: ";
        statReportText += statsRetriever.getTotalLosses();
        statReportText += "\nWin/Loss Ratio: ";
        statReportText += df.format(statsRetriever.getWinLossRatio());
        statReportText += "\nAverage Champions Killed: ";
        statReportText += df.format(statsRetriever.getAvgChampionsKilled());
        statReportText += "\nAverage Assists: ";
        statReportText += df.format(statsRetriever.getAvgAssists());
        statReportText += "\nAverage Number of Deaths: ";
        statReportText += df.format(statsRetriever.getAvgDeaths());
        statReportText += "\nAverage Kill/Death Ratio: ";
        statReportText += df.format(statsRetriever.getAvgKillDeathRatio());
    }

    private DecimalFormat createDecimalFormat(){
        DecimalFormat df = new DecimalFormat(("#.##"));
        df.setRoundingMode(RoundingMode.DOWN);
        return df;
    }

    public String getGeneralStatsReport(){
        return statReportText;
    }
}
