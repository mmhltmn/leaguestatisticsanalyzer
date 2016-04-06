package edu.bsu.cs222.statsanalyzer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

public class MostPlayedRetrieverTest {

    private Map statMap;

    public void readFromFile() throws IOException, ClassNotFoundException {
        FileInputStream input = new FileInputStream("src/main/test-resources/mapObject.ser");
        ObjectInputStream ois = new ObjectInputStream(input);
        statMap = (Map) ois.readObject();
        ois.close();
    }

    @Before
    public void testSetup() throws IOException, ClassNotFoundException {
        readFromFile();
    }

    @Test
    public void testGetMostPlayedStats() throws FileNotFoundException {
        Assert.assertTrue((statMap.get("Xin Zhao")) != null);
    }

}