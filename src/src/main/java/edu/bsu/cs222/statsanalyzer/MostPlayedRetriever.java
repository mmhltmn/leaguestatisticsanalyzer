package edu.bsu.cs222.statsanalyzer;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.stats.AggregatedStats;
import com.robrua.orianna.type.core.stats.ChampionStats;

import java.io.FileNotFoundException;

import java.io.InputStream;
import java.util.*;


public class MostPlayedRetriever {
    protected HashMap<Champion, Integer> champMap;

    public void createMostPlayedMap(Map statMap) throws FileNotFoundException {
        ArrayList<String> mostPlayedChamps = createListOfChampions();
        champMap = new HashMap<Champion, Integer>();
        for(String champion: mostPlayedChamps){
            Champion currentChampion = RiotAPI.getChampionByName(champion);
            ChampionStats currentChamp = (ChampionStats) statMap.get(currentChampion);
            if(currentChamp != null) {
                AggregatedStats currentChampStats = currentChamp.getStats();
                champMap.put(currentChampion,currentChampStats.getTotalGamesPlayed());
            }
        }
    }

    private ArrayList<String> createListOfChampions() throws FileNotFoundException {
        InputStream is = MostPlayedRetriever.class.getResourceAsStream("/ListOfChampions");
        Scanner scanner = new Scanner(is);
        String championList = scanner.useDelimiter("\\A").next();
        scanner.close();
            return new ArrayList<String>(Arrays.asList(championList.split("  ")));
    }
}


