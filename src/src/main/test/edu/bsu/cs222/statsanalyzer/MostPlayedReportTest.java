package edu.bsu.cs222.statsanalyzer;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MostPlayedReport.class)
public class MostPlayedReportTest {
    List<MostPlayedChampion> champList = new ArrayList<MostPlayedChampion>();
    Map statMap;
    private static final int HIGHEST_PLAY = 15;
    private static final int MIDDLE_PLAY = 7;
    private static final int LOWEST_PLAY = 0;

    @Before
    public void testSetup() throws Exception {
        makeTestChampions();
        MostPlayedRetriever mpr = mock(MostPlayedRetriever.class);
        when(mpr.getChampList()).thenReturn(champList);
        whenNew(MostPlayedRetriever.class).withAnyArguments().thenReturn(mpr);
    }

    public void makeTestChampions(){
        champList.add(new MostPlayedChampion("Jeff", MIDDLE_PLAY));
        champList.add(new MostPlayedChampion("Paul", LOWEST_PLAY));
        champList.add(new MostPlayedChampion("Jason", HIGHEST_PLAY));
    }

    @Test
    public void testGetReport() throws FileNotFoundException {
        MostPlayedReport mpc = new MostPlayedReport(statMap);
        Assert.assertTrue(mpc.getReport().contains("Jeff: Played 7 times"));
    }

    @Test
    public void testGetReportSorted() throws FileNotFoundException {
        MostPlayedReport mpc = new MostPlayedReport(statMap);
        Assert.assertTrue(mpc.getReport().contains("Jason: Played 15 times\nJeff: Played 7 times"));
    }
}