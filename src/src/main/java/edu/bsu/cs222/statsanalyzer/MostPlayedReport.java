package edu.bsu.cs222.statsanalyzer;


import com.robrua.orianna.type.core.staticdata.Champion;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MostPlayedReport {
    private String mostPlayedReportText;

    public MostPlayedReport(HashMap<Champion,Integer> champMap){
        mostPlayedChampSorter(champMap);
    }

    @SuppressWarnings("unchecked")
    //This method will always work even though the type cast is unchecked.
    private String mostPlayedChampSorter(HashMap<Champion,Integer> champMap){
        Object[] a = champMap.entrySet().toArray();
        Arrays.sort(a, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((HashMap.Entry<Champion, Integer>) o2).getValue().compareTo(((HashMap.Entry<Champion, Integer>) o1).getValue());
            }
        });
        String sortedMap = "";
        for (Object e : a) {
            sortedMap = sortedMap + ((((Map.Entry<Champion, Integer>) e).getKey()).getName() +
                    ": Played " + ((Map.Entry<Champion, Integer>) e).getValue()) + " times" + "\n";
        }
        return sortedMap;
    }

    public String getMostPlayedReport(){
        return mostPlayedReportText;
    }
}
