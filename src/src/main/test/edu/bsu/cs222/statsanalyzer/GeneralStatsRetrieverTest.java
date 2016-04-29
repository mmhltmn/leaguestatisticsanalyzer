package edu.bsu.cs222.statsanalyzer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;


public class GeneralStatsRetrieverTest {

    GeneralStatsRetriever gsr;
    private static final int EXPECTED_GAMES_PLAYED = 183;
    private static final int EXPECTED_TOTAL_WINS = 91;
    private static final int EXPECTED_TOTAL_LOSSES = 92;
    private static final double EXPECTED_WL_RATIO = 0.9891304347826086;
    private static final double EXPECTED_CHAMPIONS_KILLED = 4.657608695652174;
    private static final double EXPECTED_ASSISTS = 9.076086956521738;
    private static final double EXPECTED_DEATHS = 5.353260869565218;
    private static final double EXPECTED_KD_RATIO = 6.353040167733392;

    @Before
    public void testSetup() throws IOException, ClassNotFoundException {
        SerializedObjectsReader sor = new SerializedObjectsReader();
        gsr = new GeneralStatsRetriever(sor.readStatMapFromFile());
    }

    @Test
    public void testGetRankedGamesPlayed(){
        Assert.assertEquals(gsr.getRankedGamesPlayed(), EXPECTED_GAMES_PLAYED);
    }

    @Test
    public void testGetTotalWins(){
        Assert.assertEquals(gsr.getTotalWins(), EXPECTED_TOTAL_WINS);
    }

    @Test
    public void testGetTotalLosses(){
        Assert.assertEquals(gsr.getTotalLosses(), EXPECTED_TOTAL_LOSSES);
    }

    @Test
    public void testGetWinLossRatio(){
        Assert.assertTrue(gsr.getWinLossRatio() == EXPECTED_WL_RATIO);
    }

    @Test
    public void testGetAvgChampionsKilled(){
        Assert.assertTrue(gsr.getAvgChampionsKilled() == EXPECTED_CHAMPIONS_KILLED);
    }

    @Test
    public void testGetAvgAssists(){
        Assert.assertTrue(gsr.getAvgAssists() == EXPECTED_ASSISTS);
    }

    @Test
    public void testGetAvgDeaths(){
        Assert.assertTrue(gsr.getAvgDeaths() == EXPECTED_DEATHS);
    }

    @Test
    public void testGetAvgKillDeathRatio(){
        Assert.assertTrue(gsr.getAvgKillDeathRatio() == EXPECTED_KD_RATIO);
    }
}