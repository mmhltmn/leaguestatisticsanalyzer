package edu.bsu.cs222.statsanalyzer;
import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.stats.AggregatedStats;
import com.robrua.orianna.type.core.stats.ChampionStats;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.exception.APIException;

import java.io.FileNotFoundException;
import java.util.*;

public class APIRetriever {
    protected Summoner player;
    protected Map statMap;

    public APIRetriever(String name) throws APIException{
        player = prepareAPIAndSummoner(name);
        statMap = RiotAPI.getRankedStats(player);
    }

    public ArrayList<String> grabStatReports() throws FileNotFoundException {
        AggregatedStats playerStatsObject = pullStatsFromMap();
        HashMap<String, Integer> champMap = retrieveMostPlayedMap();
        ReportCreator reportCreator = new ReportCreator();
        ArrayList<String> statStrings = new ArrayList<String>();
        statStrings.add(reportCreator.statisticReportCreator(playerStatsObject));
        statStrings.add(reportCreator.mostPlayedChampSorter(champMap));
        return statStrings;
    }

    private HashMap<String, Integer> retrieveMostPlayedMap() throws FileNotFoundException {
        MostPlayedRetriever mostPlayedRetriever = new MostPlayedRetriever();
        return mostPlayedRetriever.createMostPlayedMap(statMap);
    }

    private Summoner prepareAPIAndSummoner(String name) throws APIException {
            chooseRegionAndKey();
            return RiotAPI.getSummonerByName(name);

    }

    private void chooseRegionAndKey() {  //Tested
        RiotAPI.setRegion(Region.NA);
        RiotAPI.setAPIKey("70f18885-23eb-4106-ad64-04554ffc5b4d");
    }


    private AggregatedStats pullStatsFromMap() {
        Champion AvgChampion = RiotAPI.getChampionByID(0);
        ChampionStats avgChampStats = (ChampionStats) statMap.get(AvgChampion);
        return avgChampStats.getStats();
    }

}




