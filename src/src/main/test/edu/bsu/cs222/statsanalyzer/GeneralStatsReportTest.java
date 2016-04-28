package edu.bsu.cs222.statsanalyzer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

public class GeneralStatsReportTest {
    private Map statMap;
    GeneralStatsReport gsr;
    @Before
    @SuppressWarnings("unchecked")
    //This method will always work even though the type cast is unchecked.
    public void readFromFile() throws IOException, ClassNotFoundException {
        FileInputStream input = new FileInputStream("src/main/test-resources/mapObject.ser");
        ObjectInputStream ois = new ObjectInputStream(input);
        statMap = (Map) ois.readObject();
        ois.close();
    }

    @Before
    public void testSetup(){
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