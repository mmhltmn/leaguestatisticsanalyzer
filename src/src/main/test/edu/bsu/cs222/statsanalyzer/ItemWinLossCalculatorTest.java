package edu.bsu.cs222.statsanalyzer;

import com.robrua.orianna.type.core.game.Game;
import com.robrua.orianna.type.core.staticdata.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ItemWinLossCalculator.class)
public class ItemWinLossCalculatorTest {
    private List<Game> recentGames;
    private List<GameItem> testList;
    private List<Item> mockItemList;
    private static final double RECENT_GAMES_WIN_RATE = 0.7;

    @Before
    public void testSetup() throws Exception {
        SerializedObjectsReader sor = new SerializedObjectsReader();
        recentGames = sor.readRecentGamesFromFile();
        mockListSetup();
        mockCalcSetup();
    }

    public void mockListSetup() throws Exception {
        mockItemList = new ArrayList<Item>();
        Item mockSword = mock(Item.class);
        when(mockSword.getName()).thenReturn("Sword");
        Item mockShield = mock(Item.class);
        when(mockShield.getName()).thenReturn("Shield");
        mockItemList.add(mockSword);
        mockItemList.add(mockShield);
    }

    public void mockCalcSetup() throws Exception {
        ItemWinLossCalculator testCalc = spy(new ItemWinLossCalculator());
        doReturn(mockItemList).when(testCalc, "makeItemList", anyObject());
        testCalc.calculateItemWinsAndLosses(recentGames);
        testList = testCalc.makeListForReport();
    }

    @Test
    public void testItemName(){
        Assert.assertTrue(testList.get(0).getName().equals("Shield"));
    }

    @Test
    public void testOtherItemName(){
        Assert.assertTrue(testList.get(0).getName().equals("Sword"));
    }

    @Test
    public void testItemWinLoss() {
        Assert.assertTrue(testList.get(0).getWinRate() == RECENT_GAMES_WIN_RATE);
    }
}
