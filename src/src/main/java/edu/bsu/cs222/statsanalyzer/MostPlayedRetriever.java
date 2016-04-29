package edu.bsu.cs222.statsanalyzer;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.stats.AggregatedStats;
import com.robrua.orianna.type.core.stats.ChampionStats;

import java.io.FileNotFoundException;

import java.io.InputStream;
import java.util.*;


public class MostPlayedRetriever{
    private List<MostPlayedChampion> champList = new ArrayList<MostPlayedChampion>();

    public MostPlayedRetriever(Map statMap) throws FileNotFoundException {
        createMostPlayedChampList(statMap);
    }

    private void createMostPlayedChampList(Map statMap) throws FileNotFoundException {
        ArrayList<String> mostPlayedChamps = createListOfChampions();
        for(String champion: mostPlayedChamps){
            Champion currentChampion = RiotAPI.getChampionByName(champion);
            ChampionStats currentChamp = (ChampionStats) statMap.get(currentChampion);
            if(currentChamp != null) {
                AggregatedStats currentChampStats = currentChamp.getStats();
                champList.add(new MostPlayedChampion(champion,currentChampStats.getTotalGamesPlayed()));
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

    public List<MostPlayedChampion> getChampList(){
        return champList;
    }
}


