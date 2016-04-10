package edu.bsu.cs222.statsanalyzer;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.stats.AggregatedStats;
import com.robrua.orianna.type.core.stats.ChampionStats;

import java.io.FileNotFoundException;

import java.io.InputStream;
import java.util.*;


public class MostPlayedRetriever {
    protected HashMap<String, Integer> champMap;

    public void createMostPlayedMap(Map statMap) throws FileNotFoundException {
        ArrayList<String> mostPlayedChamps = createListOfChampions();
        champMap = new HashMap<String, Integer>();
        for(String champion: mostPlayedChamps){
            Champion currentChampion = RiotAPI.getChampionByName(champion);
            ChampionStats currentChamp = (ChampionStats) statMap.get(currentChampion);
            if(currentChamp != null) {
                AggregatedStats currentChampStats = currentChamp.getStats();
                champMap.put(champion,currentChampStats.getTotalGamesPlayed());
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

    @SuppressWarnings("unchecked")
    //This method will always work even though the type cast is unchecked.
    public String mostPlayedChampSorter(){
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


