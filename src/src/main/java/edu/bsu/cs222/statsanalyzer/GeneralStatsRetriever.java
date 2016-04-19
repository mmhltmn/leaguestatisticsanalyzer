package edu.bsu.cs222.statsanalyzer;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.stats.AggregatedStats;
import com.robrua.orianna.type.core.stats.ChampionStats;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;


public class GeneralStatsRetriever {
    private AggregatedStats playerStatsObject;
    public GeneralStatsRetriever(Map statMap){
        pullStatsFromMap(statMap);
    }

    private void pullStatsFromMap(Map statMap) {
        Champion avgChampion = RiotAPI.getChampionByID(0);
        ChampionStats avgChampStats = (ChampionStats) statMap.get(avgChampion);
        playerStatsObject = avgChampStats.getStats();
    }

    public AggregatedStats getPlayerStatsObject(){
        return playerStatsObject;
    }

}



