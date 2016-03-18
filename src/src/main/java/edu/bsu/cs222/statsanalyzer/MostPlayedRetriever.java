package edu.bsu.cs222.statsanalyzer;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.stats.AggregatedStats;
import com.robrua.orianna.type.core.stats.ChampionStats;
import com.sun.istack.internal.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;


public class MostPlayedRetriever {

    protected HashMap<String, Integer> createMostPlayedMap(Map statMap) throws FileNotFoundException {
        ArrayList<String> mostPlayedChamps = createListOfChampions();
        HashMap<String, Integer> champMap = new HashMap<String, Integer>();
        for(String champion: mostPlayedChamps){
            Champion currentChampion = RiotAPI.getChampionByName(champion);
            ChampionStats currentChamp = (ChampionStats) statMap.get(currentChampion);
            if(currentChamp != null) {
                AggregatedStats currentChampStats = currentChamp.getStats();
                champMap.put(champion,currentChampStats.getTotalGamesPlayed());
            }
        }
        return champMap;
    }

    @SuppressWarnings("ConstantConditions")
    //Suppressed becase if file is null, returns an ArrayList that tells the user their file is empty.
    private ArrayList<String> createListOfChampions() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        if (((classLoader.getResource("ListOfChampions").getFile()) != null)) {
            Scanner scanner = new Scanner(new File((classLoader.getResource("ListOfChampions").getFile())));
            String championList = scanner.useDelimiter("\\A").next();
            scanner.close();
            return new ArrayList<String>(Arrays.asList(championList.split("  ")));
        }else{
            ArrayList<String> ifChampionFileMissing = new ArrayList<String>();
            ifChampionFileMissing.add("Champion file is null.");
            return ifChampionFileMissing;
        }

    }

}

