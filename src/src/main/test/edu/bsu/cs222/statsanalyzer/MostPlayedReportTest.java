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
    private List<MostPlayedChampion> champList = new ArrayList<MostPlayedChampion>();
    private Map statMap;

    @Before
    public void testSetup() throws Exception {
        makeTestChampions();
        MostPlayedRetriever mpr = mock(MostPlayedRetriever.class);
        when(mpr.getChampList()).thenReturn(champList);
        whenNew(MostPlayedRetriever.class).withAnyArguments().thenReturn(mpr);
    }

    public void makeTestChampions(){
        MostPlayedChampion jeffMPC = new MostPlayedChampion("Jeff", 2);
        MostPlayedChampion paulMPC = new MostPlayedChampion("Paul", 7);
        MostPlayedChampion jasonMPC = new MostPlayedChampion("Jason", 15);
        champList.add(jeffMPC);
        champList.add(paulMPC);
        champList.add(jasonMPC);
    }

    @Test
    public void testGetReport() throws FileNotFoundException {
        MostPlayedReport mpc = new MostPlayedReport(statMap);
        Assert.assertTrue(mpc.getReport().contains("Jeff: Played 2 times"));
    }

    @Test
    public void testGetReportSorted() throws FileNotFoundException {
        MostPlayedReport mpc = new MostPlayedReport(statMap);
        Assert.assertTrue(mpc.getReport().contains("Jason: Played 15 times\nPaul: Played 7 times"));
    }
}