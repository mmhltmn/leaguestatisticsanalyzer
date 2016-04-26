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
       // Summoner player = prepareAPIAndSummoner(name);
       // statMap = RiotAPI.getRankedStats(player);
       // recentGames = RiotAPI.getRecentGames(name);
    }

  //  private Summoner prepareAPIAndSummoner(String name) throws APIException {
    //        chooseRegionAndKey();
     //       return RiotAPI.getSummonerByName(name);
   // }
}




