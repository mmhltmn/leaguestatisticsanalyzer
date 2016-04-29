package edu.bsu.cs222.statsanalyzer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MostPlayedChampionTest {
    private MostPlayedChampion mpc;
    private static final int TIMES_PLAYED = 8;
    private static final int GREATER_TIMES_PLAYED = 12;
    private static final int LESS_TIMES_PLAYED = 6;


    @Before
    public void testSetup(){
        mpc = new MostPlayedChampion("Jason", TIMES_PLAYED);
    }

    @Test
    public void testGetChampionName(){
        Assert.assertEquals(mpc.getChampionName(), "Jason");
    }

    @Test
    public void testGetTimesPlayed(){
        Assert.assertEquals(mpc.getTimesPlayed(), TIMES_PLAYED);
    }

    @Test
    public void testComparableGreaterThan(){
        MostPlayedChampion greater = new MostPlayedChampion("Jason", GREATER_TIMES_PLAYED);
        Assert.assertTrue(mpc.compareTo(greater) == 1);
    }

    @Test
    public void testComparableLessThan(){
        MostPlayedChampion less = new MostPlayedChampion("Jason", LESS_TIMES_PLAYED);
        Assert.assertTrue(mpc.compareTo(less) == -1);
    }

    @Test
    public void testEqual(){
        MostPlayedChampion equal = new MostPlayedChampion("Jason", TIMES_PLAYED);
        Assert.assertTrue(mpc.compareTo(equal) == 0);
    }
}