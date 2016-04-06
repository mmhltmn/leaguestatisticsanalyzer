package edu.bsu.cs222.statsanalyzer;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.staticdata.Champion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class APIRetrieverTest {
    //Covers ReportCreator code as well.
    private StatsRetriever apiRetriever;

    @Before
    public void testSetup(){
        apiRetriever = new StatsRetriever("Hiloka");
    }

    @Test
    public void regionAndKeySetSuccessful(){
        Champion XinZhao = RiotAPI.getChampionByName("Xin Zhao");
        Assert.assertTrue(XinZhao != null);
    }

    @Test
    public void isPlayerObjectReturningStats() throws FileNotFoundException {
        ArrayList<String> statReports = apiRetriever.grabStatReports();
        Assert.assertTrue(statReports.get(0).contains("Total Wins"));
    }

    @Test
    public void isPlayerObjectReturningMostPlayed() throws FileNotFoundException {
        ArrayList<String> statReports = apiRetriever.grabStatReports();
        Assert.assertTrue(statReports.get(1).contains("Played"));
    }

}
