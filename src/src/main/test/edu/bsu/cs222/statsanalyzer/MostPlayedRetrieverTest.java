/*package edu.bsu.cs222.statsanalyzer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class MostPlayedRetrieverTest {

    private HashMap<String,Integer> statMap;
    private MostPlayedRetriever mostPlayedRetriever = new MostPlayedRetriever();

    @SuppressWarnings("unchecked")
    //This method will always work even though the type cast is unchecked.
    public void readFromFile() throws IOException, ClassNotFoundException {
       FileInputStream input = new FileInputStream("src/main/test-resources/hashmapObject.ser");
        ObjectInputStream ois = new ObjectInputStream(input);
        statMap = (HashMap<String,Integer>) ois.readObject();
        ois.close();
    }

    @Before
    public void testSetup() throws IOException, ClassNotFoundException {
        readFromFile();
        mostPlayedRetriever.champMap = statMap;
    }

    @Test
    public void testGetMostPlayedStatsHasChamps() throws FileNotFoundException {
        Assert.assertTrue((statMap.get("Xin Zhao")) != null);

    }


    @Test
    public void testGetMostPlayedStringFormatting() throws FileNotFoundException {
        String testReport = mostPlayedRetriever.mostPlayedChampSorter();
        Assert.assertTrue(testReport.contains("Fizz: Played 1 times"));
    }

    @Test
    public void testGetMostPlayedStringDoesntAddNull() throws FileNotFoundException {
        String testReport = mostPlayedRetriever.mostPlayedChampSorter();
        Assert.assertTrue(!testReport.contains("null"));
    }
}
*/