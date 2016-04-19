package edu.bsu.cs222.statsanalyzer;

import com.robrua.orianna.type.core.staticdata.Item;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class ItemWinLossReport {
    private String winLossReportText;

    public ItemWinLossReport(HashMap<Item, int[]> itemWinAndLossMap){
        createWinLossReport(itemWinAndLossMap);
    }

    @SuppressWarnings("unchecked")
    //This method will always work even though the type cast is unchecked.
    public String createWinLossReport(HashMap<Item, int[]> itemWinAndLossMap){
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMaximumFractionDigits(1);
        Object[] a = itemWinAndLossMap.entrySet().toArray();
        String sortedMap = "";
        for (Object e : a) {
            int[] currentValue = ((Map.Entry<Integer, int[]>) e).getValue();
            int gamesPlayed = currentValue[0] + currentValue[1];
            sortedMap = sortedMap + (((Map.Entry<Integer, int[]>) e).getKey() +
                    ": Bought " + gamesPlayed + " times, Win Rate: " +
                    percentFormat.format((double)(currentValue[0])/gamesPlayed) + "\n");
        }
        return sortedMap;
    }

    public String getWinLossReport(){
        return winLossReportText;
    }

}
