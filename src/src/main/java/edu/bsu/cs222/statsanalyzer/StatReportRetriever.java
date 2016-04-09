package edu.bsu.cs222.statsanalyzer;
import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.game.Game;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.exception.APIException;

import java.io.FileNotFoundException;
import java.util.*;

public class StatReportRetriever{
    private List<Game> recentGames;
    private Map statMap;

    public StatReportRetriever(String name) throws APIException{
        Summoner player = prepareAPIAndSummoner(name);
        statMap = RiotAPI.getRankedStats(player);
        recentGames = RiotAPI.getRecentGames(name);
    }

    public ArrayList<String> grabStatReports() throws FileNotFoundException {
        StatsRetriever statsRetriever = new StatsRetriever(statMap);
        MostPlayedRetriever mostPlayedRetriever = new MostPlayedRetriever(statMap);
        ArrayList<String> statStrings = new ArrayList<String>();
        statStrings.add(statsRetriever.statisticReportCreator());
        statStrings.add(mostPlayedRetriever.mostPlayedChampSorter());
        return statStrings;
    }

    public String grabItemReport(){
        ItemWinLossCalculator itemWinLossCalculator = new ItemWinLossCalculator(recentGames);
        return itemWinLossCalculator.createReport();
    }

    private Summoner prepareAPIAndSummoner(String name) throws APIException {
            chooseRegionAndKey();
            return RiotAPI.getSummonerByName(name);
    }

    private void chooseRegionAndKey() {  //Tested
        RiotAPI.setRegion(Region.NA);
        RiotAPI.setAPIKey("70f18885-23eb-4106-ad64-04554ffc5b4d");
    }
}




