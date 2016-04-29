package edu.bsu.cs222.statsanalyzer;

import com.robrua.orianna.type.core.game.Game;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ItemWinLossReport.class)
public class ItemWinLossReportTest {
    private List<GameItem> gameItems = new ArrayList<GameItem>();
    private List<Game> recentGames;

    @Before
    public void testSetup() throws Exception {
        SerializedObjectsReader read = new SerializedObjectsReader();
        recentGames = read.readRecentGamesFromFile();
        makeTestGames();
        ItemWinLossCalculator wlc = mock(ItemWinLossCalculator.class);
        when(wlc.makeListForReport()).thenReturn(gameItems);
        whenNew(ItemWinLossCalculator.class).withAnyArguments().thenReturn(wlc);
    }

    public void makeTestGames(){
        gameItems.add(makeKeyboardItem());
        gameItems.add(makeMouseItem());
    }

    public GameItem makeKeyboardItem(){
        int[] kbWinLoss = new int[2];
        kbWinLoss[0] = 3;
        kbWinLoss[1] = 4;
        return new GameItem("Keyboard", kbWinLoss);
    }

    public GameItem makeMouseItem(){
        int[] mouseWinLoss = new int[2];
        mouseWinLoss[0] = 5;
        mouseWinLoss[1] = 1;
        return new GameItem("Mouse", mouseWinLoss);
    }

    @Test
    public void testGetReport(){
        ItemWinLossReport wlr = new ItemWinLossReport(recentGames);
        Assert.assertTrue(wlr.getReport().contains("Keyboard: Bought 7 times, Win Rate: 42.9%"));
    }

    @Test
    public void testReportNewLine(){
        ItemWinLossReport wlr = new ItemWinLossReport(recentGames);
        Assert.assertTrue(wlr.getReport().contains("\nMouse"));
    }
}
