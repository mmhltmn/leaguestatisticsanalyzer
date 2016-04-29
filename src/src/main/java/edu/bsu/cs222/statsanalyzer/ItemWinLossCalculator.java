package edu.bsu.cs222.statsanalyzer;

import com.robrua.orianna.type.core.game.Game;
import com.robrua.orianna.type.core.game.RawStats;
import com.robrua.orianna.type.core.staticdata.Item;
import com.robrua.orianna.type.exception.MissingDataException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class ItemWinLossCalculator {
    private HashMap<Item,int[]> itemWinAndLossMap= new HashMap<Item, int[]>();
    private HashMap<Integer, List<Item>> itemsUsedInWinningGames = new HashMap<Integer, List<Item>>();
    private HashMap<Integer, List<Item>> itemsUsedInLosingGames = new HashMap<Integer, List<Item>>();
    private List<Item> itemsList;
    private static final int NUMBER_OF_ITEMS = 6;

    public void calculateItemWinsAndLosses(List<Game> recentGames){
        winsAndLossesToItemMap(recentGames);
        createWinAndLossMap(itemsUsedInWinningGames);
        createWinAndLossMap(itemsUsedInLosingGames);
    }

    private void winsAndLossesToItemMap(List<Game> recentGames) {
        int wins = 0;
        int losses = 0;
        for (Game currentGame: recentGames) {
            RawStats currentGameStats = currentGame.getStats();
            if (currentGameStats.getWin()) {
                itemsUsedInWinningGames.put(wins, makeItemList(currentGameStats));
                wins++;
            }else{
                itemsUsedInLosingGames.put(losses, makeItemList(currentGameStats));
                losses++;
            }
        }
    }

    private List<Item> makeItemList(RawStats currentGameStats) throws MissingDataException {
        List<Item> items = new ArrayList<Item>();
        for (int i=0;i<=NUMBER_OF_ITEMS;i++){
            try{
                Method m= currentGameStats.getClass().getMethod("getItem"+String.valueOf(i));
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

    private void createWinAndLossMap(HashMap<Integer, List<Item>> currentMap){
        for(int gameNumber = 0; gameNumber < currentMap.size(); gameNumber++){
            if (currentMap == itemsUsedInWinningGames){
                itemsList = currentMap.get(gameNumber);
                putWinsAndLossesInMap(true);
            }else if(currentMap == itemsUsedInLosingGames){
                itemsList = currentMap.get(gameNumber);
                putWinsAndLossesInMap(false);

            }
        }
    }

    private void putWinsAndLossesInMap(boolean win) {
        for (int itemNumber = 0; itemNumber < itemsList.size(); itemNumber++) {
            Item currentItem = itemsList.get(itemNumber);
            if (!(itemWinAndLossMap.containsKey(currentItem)) && currentItem != null) {
                itemWinAndLossMap.put(itemsList.get(itemNumber), new int[2]);
            }if (itemWinAndLossMap.containsKey(currentItem) && win){
                addToWinCountInMap(itemNumber);
            }if (itemWinAndLossMap.containsKey(currentItem) && !win) {
                addToLossCountInMap(itemNumber);
            }
        }
    }

    private void addToWinCountInMap(int itemNumber){
        int[] newWinCount = itemWinAndLossMap.get(itemsList.get(itemNumber));
        newWinCount[0]++;
        itemWinAndLossMap.put(itemsList.get(itemNumber), newWinCount);
    }

    private void addToLossCountInMap(int itemNumber){
        int[] newLossCount = itemWinAndLossMap.get(itemsList.get(itemNumber));
        newLossCount[1]++;
        itemWinAndLossMap.put(itemsList.get(itemNumber), newLossCount);
    }

    @SuppressWarnings("unchecked")
    //This method will always work even though the type cast is unchecked.
    public List<GameItem> makeListForReport(){
        List<GameItem> gameItems = new ArrayList<GameItem>();
        Object[] a = itemWinAndLossMap.entrySet().toArray();
        for (Object e : a) {
            String name = ((Map.Entry<Item, int[]>) e).getKey().getName();
            int[] winLoss = ((Map.Entry<Item, int[]>) e).getValue();
            GameItem currentItem = new GameItem(name, winLoss);
            gameItems.add(currentItem);
        }
        return gameItems;
    }
}
