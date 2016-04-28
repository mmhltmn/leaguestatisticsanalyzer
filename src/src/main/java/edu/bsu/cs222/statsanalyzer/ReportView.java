package edu.bsu.cs222.statsanalyzer;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.game.Game;
import com.robrua.orianna.type.core.summoner.Summoner;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class ReportView {
    private String generalStatsReport;
    private String mostPlayedReport;
    private String winLossReport;

    public ReportView(String name) throws FileNotFoundException {
        retrieveGeneralAndMostPlayedStats(name);
        retrieveItemWinLossStats(name);
    }

    public static void chooseRegionAndKey() {  //Tested
        RiotAPI.setRegion(Region.NA);
        RiotAPI.setAPIKey("70f18885-23eb-4106-ad64-04554ffc5b4d");
    }

    private void retrieveGeneralAndMostPlayedStats(String name) throws FileNotFoundException {
        Map statMap = retrievePlayerStatMap(name);
        GeneralStatsReport generalStats = new GeneralStatsReport(statMap);
        MostPlayedReport mostPlayed = new MostPlayedReport(statMap);
        generalStatsReport = generalStats.getReport();
        mostPlayedReport = mostPlayed.getReport();
    }

    private Map retrievePlayerStatMap(String name){
        Summoner player = RiotAPI.getSummonerByName(name);
        return RiotAPI.getRankedStats(player);
    }

    private void retrieveItemWinLossStats(String name){
        List<Game> recentGames = RiotAPI.getRecentGames(name);
        ItemWinLossReport itemWinLoss = new ItemWinLossReport(recentGames);
        winLossReport = itemWinLoss.getReport();
    }

    public String getGeneralStatsReport(){
        return generalStatsReport;
    }

    public String getMostPlayedReport(){
        return mostPlayedReport;
    }

    public String getWinLossReport(){
        return winLossReport;
    }
}
