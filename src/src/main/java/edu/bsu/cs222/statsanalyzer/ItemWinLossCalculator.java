package edu.bsu.cs222.statsanalyzer;

import com.robrua.orianna.type.core.game.Game;
import com.robrua.orianna.type.core.game.RawStats;
import com.robrua.orianna.type.core.staticdata.Item;
import com.robrua.orianna.type.exception.MissingDataException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//MAKE SEPARATE MAP FOR WINS AND LOSSES.
//CREATE REPORT IN THIS CLASS AND RETURN TO UI.
//STATS RETRIEVER GETS GAMES AND GIVES THEM TO THIS CLASS.
public class ItemWinLossCalculator {
    private HashMap<Item,int[]> itemWinAndLossMap= new HashMap<Item, int[]>();
    private List<Item> itemsList;
    private int MAX_GAMES_WON = 10;
    private int MAX_GAMEMAP_SIZE = 20;

    public ItemWinLossCalculator(List<Game> recentGames){
        HashMap<Integer, List<Item>> itemsUsedInGamesMap = gameToItemMap(recentGames);
        createWinAndLossMap(itemsUsedInGamesMap);
    }

    private HashMap<Integer, List<Item>> gameToItemMap(List<Game> recentGames){
        HashMap<Integer, List<Item>> itemsUsedInGamesMap = new HashMap<Integer, List<Item>>();
        for(int i = 0; i < recentGames.size(); i++) {
            RawStats currentGameStats = recentGames.get(i).getStats();
             int keyPosition = winOrLoss(currentGameStats);
             itemsUsedInGamesMap.put(i+keyPosition,makeItemList(currentGameStats));
    }
        return itemsUsedInGamesMap;
    }

    private int winOrLoss(RawStats currentGameStats) {
        if (currentGameStats.getWin()){
            return 0;
        }else{
            return 10;
        }
    }

    private List<Item> makeItemList(RawStats currentGameStats) throws MissingDataException {
        ArrayList<Item> items = new ArrayList<Item>();
        for (int i=0;i<7;i++){
            try{
                Method m= currentGameStats.getClass().getMethod("getItem"+String.valueOf(i), new Class[]{});
                items.add((Item)(m.invoke(currentGameStats)));
            }catch (MissingDataException e){
                items.add(null);
            } catch (NoSuchMethodException e) {
                items.add(null);
            } catch (IllegalAccessException e) {
                items.add(null);
            } catch (InvocationTargetException e) {
                items.add(null);
            }
        }
        return items;
    }

    private void createWinAndLossMap(HashMap<Integer, List<Item>> itemsUsedInGamesMap){
        for(int gameNumber = 0; gameNumber <= MAX_GAMEMAP_SIZE; gameNumber++){
            if (itemsUsedInGamesMap.get(gameNumber) != null){
                itemsList = itemsUsedInGamesMap.get(gameNumber);
                putInMapWithWinCount(gameNumber);
                }
            }
    }

    private void putInMapWithWinCount(int gameNumber) {
        for (int itemNumber = 0; itemNumber < itemsList.size(); itemNumber++) {
            if (!(itemWinAndLossMap.containsKey(itemsList.get(itemNumber)))) {
                itemWinAndLossMap.put(itemsList.get(itemNumber), new int[2]);
            } else if (itemWinAndLossMap.containsKey(itemsList.get(itemNumber)) && gameNumber < MAX_GAMES_WON) {
                addToWinCountInMap(itemNumber);
            } else if (itemWinAndLossMap.containsKey(itemsList.get(itemNumber)) && gameNumber >= MAX_GAMES_WON) {
                addToLossCountInMap(itemNumber);
            }
        }
    }

        private void addToWinCountInMap(int itemNumber){
        int[] newWinCount = itemWinAndLossMap.get(itemsList.get(itemNumber));
        newWinCount[0] = newWinCount[0]++;
        itemWinAndLossMap.put(itemsList.get(itemNumber), newWinCount);
    }

    private void addToLossCountInMap(int itemNumber){
        int[] newLossCount = itemWinAndLossMap.get(itemsList.get(itemNumber));
        newLossCount[1] = newLossCount[1]++;
        itemWinAndLossMap.put(itemsList.get(itemNumber), newLossCount);
    }
}
