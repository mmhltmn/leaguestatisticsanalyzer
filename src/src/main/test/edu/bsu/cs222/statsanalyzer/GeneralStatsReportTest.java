package edu.bsu.cs222.statsanalyzer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.Map;

public class GeneralStatsReportTest {
    GeneralStatsReport gsr;

    @Before
    public void testSetup() throws IOException, ClassNotFoundException {
        SerializedObjectsReader read = new SerializedObjectsReader();
        Map statMap = read.readStatMapFromFile();
        gsr = new GeneralStatsReport(statMap);
    }

    @Test
    public void testGetReportAccuracy(){
        Assert.assertTrue(gsr.getReport().contains("Total Wins: 91"));
    }

    @Test
    public void testGetReportNewLineFormat(){
        Assert.assertTrue(gsr.getReport().contains("\nWin/Loss Ratio: 0.98"));
    }
}