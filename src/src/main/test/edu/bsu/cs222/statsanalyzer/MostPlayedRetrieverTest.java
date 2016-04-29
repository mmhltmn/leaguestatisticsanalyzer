package edu.bsu.cs222.statsanalyzer;
import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.stats.AggregatedStats;
import com.robrua.orianna.type.core.stats.ChampionStats;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(RiotAPI.class)
public class MostPlayedRetrieverTest {
    private Map statMap;

    @Before
    public void testSetup() throws Exception {
        Champion lesserChampion = mock(Champion.class);
        Champion greaterChampion = mock(Champion.class);
        statMap = mockStatMap(greaterChampion, lesserChampion);
        mockRiotAPI(greaterChampion, lesserChampion);
    }


    public Map mockStatMap(Champion greaterChampion, Champion lesserChampion){
        Map<Champion, ChampionStats> statMap = new HashMap<Champion, ChampionStats>();
        ChampionStats lesserChampionStats = makeLesserChampionStats();
        ChampionStats greaterChampionStats = makeGreaterChampionStats();
        statMap.put(lesserChampion,lesserChampionStats);
        statMap.put(greaterChampion,greaterChampionStats);
        return statMap;
    }

    public ChampionStats makeLesserChampionStats(){
        ChampionStats lesserChampionStats = mock(ChampionStats.class);
        AggregatedStats lesserChampionAggStats = mock(AggregatedStats.class);
        when(lesserChampionStats.getStats()).thenReturn(lesserChampionAggStats);
        when(lesserChampionAggStats.getTotalGamesPlayed()).thenReturn(4);
        return lesserChampionStats;
    }

    public ChampionStats makeGreaterChampionStats(){
        ChampionStats greaterChampionStats = mock(ChampionStats.class);
        AggregatedStats greaterChampionAggStats = mock(AggregatedStats.class);
        when(greaterChampionStats.getStats()).thenReturn(greaterChampionAggStats);
        when(greaterChampionAggStats.getTotalGamesPlayed()).thenReturn(7);
        return greaterChampionStats;
    }

    public void mockRiotAPI(Champion greaterChampion, Champion lesserChampion) throws Exception {
        PowerMockito.mockStatic(RiotAPI.class);
        when(RiotAPI.getChampionByName(Mockito.matches("Amumu"))).thenReturn(lesserChampion);
        when(RiotAPI.getChampionByName(Mockito.matches("Ahri"))).thenReturn(greaterChampion);
    }


    @Test
    public void testAllChampsAdded() throws FileNotFoundException {
        MostPlayedRetriever mpr = new MostPlayedRetriever(statMap);
        Assert.assertTrue(mpr.getChampList().size() == 2);
    }

    @Test
    public void testChampListNamesWork() throws FileNotFoundException{
        MostPlayedRetriever mpr = new MostPlayedRetriever(statMap);
        List<MostPlayedChampion> champList = mpr.getChampList();
        Assert.assertTrue(champList.get(1).getChampionName().equals("Amumu"));
    }

    @Test
    public void testChampListTimesPlayedWork() throws FileNotFoundException{
        MostPlayedRetriever mpr = new MostPlayedRetriever(statMap);
        List<MostPlayedChampion> champList = mpr.getChampList();
        Assert.assertTrue(champList.get(1).getTimesPlayed() == 4);
    }



}

