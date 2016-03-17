package edu.bsu.cs222.statsanalyzer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class MostPlayedRetrieverTest {

    private APIRetriever apiRetriever;
    private MostPlayedRetriever playedRetriever;

    @Before
    public void testSetup(){
        apiRetriever = new APIRetriever("Hiloka");
        playedRetriever = new MostPlayedRetriever();
    }

    @Test
    public void testGetMostPlayedStats() throws FileNotFoundException {
        HashMap<String, Integer> testMap = playedRetriever.createMostPlayedMap(apiRetriever.statMap);
        Assert.assertTrue((testMap.get("Xin Zhao")) != null);

    }

}