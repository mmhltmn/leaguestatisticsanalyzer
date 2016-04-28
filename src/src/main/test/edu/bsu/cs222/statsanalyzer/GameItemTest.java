package edu.bsu.cs222.statsanalyzer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameItemTest {
    private GameItem gi;

    @Before
    public void testSetup(){
        int[] winLoss = new int[2];
        winLoss[0] = 5;
        winLoss[1] = 2;
        gi = new GameItem("BarryFaust", winLoss);
    }

    @Test
    public void testGetName(){
        Assert.assertEquals(gi.getName(), "BarryFaust");
    }

    @Test
    public void testGetGamesPlayed(){
        Assert.assertEquals(gi.getGamesPlayed(), 7);
    }

    @Test
    public void testGetWinRate(){
        Assert.assertTrue(gi.getWinRate() == (double)5/(double)7);
    }
}