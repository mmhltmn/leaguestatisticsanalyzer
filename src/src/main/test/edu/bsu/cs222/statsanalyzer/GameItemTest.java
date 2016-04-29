package edu.bsu.cs222.statsanalyzer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameItemTest {
    private GameItem gi;
    private static final int barrysWins = 5;
    private static final int barrysLosses = 2;
    private static final int barrysGamesPlayed = barrysWins + barrysLosses;

    @Before
    public void testSetup(){
        int[] winLoss = new int[2];
        winLoss[0] = barrysWins;
        winLoss[1] = barrysLosses;
        gi = new GameItem("Barry", winLoss);
    }

    @Test
    public void testGetName(){
        Assert.assertEquals(gi.getName(), "Barry");
    }

    @Test
    public void testGetGamesPlayed(){
        Assert.assertEquals(gi.getGamesPlayed(), barrysGamesPlayed);
    }

    @Test
    public void testGetWinRate(){
        Assert.assertTrue(gi.getWinRate() == (double)barrysWins/(double)barrysGamesPlayed);
    }
}