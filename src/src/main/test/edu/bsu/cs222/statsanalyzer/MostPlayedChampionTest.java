package edu.bsu.cs222.statsanalyzer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MostPlayedChampionTest {
    private MostPlayedChampion mpc;

    @Before
    public void testSetup(){
        mpc = new MostPlayedChampion("JasonSchultz", 8);
    }

    @Test
    public void testGetChampionName(){
        Assert.assertEquals(mpc.getChampionName(), "JasonSchultz");
    }

    @Test
    public void testGetTimesPlayed(){
        Assert.assertEquals(mpc.getTimesPlayed(), 8);
    }

    @Test
    public void testComparableGreaterThan(){
        MostPlayedChampion greater = new MostPlayedChampion("JasonSchultz", 12);
        Assert.assertTrue(mpc.compareTo(greater) == -1);
    }

    @Test
    public void testComparableLessThan(){
        MostPlayedChampion less = new MostPlayedChampion("JasonSchultz", 6);
        Assert.assertTrue(mpc.compareTo(less) == 1);
    }

    @Test
    public void testEqual(){
        MostPlayedChampion equal = new MostPlayedChampion("JasonSchultz", 8);
        Assert.assertTrue(mpc.compareTo(equal) == 0);
    }
}