package edu.bsu.cs222.statsanalyzer;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.game.Game;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class ItemWinLossCalculatorTest {
    private List<Game> recentGames;
    private String testReport;

    @Before
    @SuppressWarnings("unchecked")
    //This method will always work even though the type cast is unchecked.
    public void readFromFile() throws IOException, ClassNotFoundException {
        RiotAPI.setRegion(Region.NA); //This test class doesn't work without doing this
        //even though WinLossCalculator doesn't even touch the API to our knowledge.  This needs fixing in a future iteration.
        RiotAPI.setAPIKey("70f18885-23eb-4106-ad64-04554ffc5b4d");
        FileInputStream input = new FileInputStream("src/main/test-resources/recentGames.ser");
        ObjectInputStream ois = new ObjectInputStream(input);
        recentGames = (List<Game>) ois.readObject();
        ois.close();
        getReport();
    }

    public void getReport(){
        ItemWinLossCalculator itemWinLossCalculator = new ItemWinLossCalculator(recentGames);
        testReport = itemWinLossCalculator.createReport();
    }

    @Test
    public void reportContainsItemCount(){
        Assert.assertTrue(testReport.startsWith("Blasting Wand: Bought 2 times"));
    }

    @Test
    public void reportContainsWinRate(){
        ItemWinLossCalculator itemWinLossCalculator = new ItemWinLossCalculator(recentGames);
        String testReport = itemWinLossCalculator.createReport();
        Assert.assertTrue(testReport.contains(", Win Rate: 0%"));
    }

}
