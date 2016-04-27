/*package edu.bsu.cs222.statsanalyzer;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.Region;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

public class StatsRetrieverTest {
    private Map statMap;
    @Before
    @SuppressWarnings("unchecked")
    //This method will always work even though the type cast is unchecked.
    public void readFromFile() throws IOException, ClassNotFoundException {
        RiotAPI.setRegion(Region.NA); //This needs fixing in a future iteration.
        RiotAPI.setAPIKey("70f18885-23eb-4106-ad64-04554ffc5b4d");
        FileInputStream input = new FileInputStream("src/main/test-resources/mapObject.ser");
        ObjectInputStream ois = new ObjectInputStream(input);
        statMap = (Map) ois.readObject();
        ois.close();
    }


    @Test
    public void reportContainsItemCount(){
        StatsRetriever statsRetriever = new StatsRetriever(statMap);
        String report = statsRetriever.statisticReportCreator();
        Assert.assertTrue(report.startsWith("Ranked Games Played: 183"));
    }

}


*/