package edu.bsu.cs222.statsanalyzer;

import java.io.FileNotFoundException;
import java.util.*;

public class MostPlayedReport {
    private String mostPlayedReportText;

    public MostPlayedReport(Map statMap) throws FileNotFoundException {
        MostPlayedRetriever retriever = new MostPlayedRetriever(statMap);
        List<MostPlayedChampion> champList = retriever.getChampList();
        mostPlayedReportText = mostPlayedChampSorter(champList);
    }

    private String mostPlayedChampSorter(List<MostPlayedChampion> champList){
        Collections.sort(champList);
        String mostPlayedReport = "";
        for (int i = 0; i < champList.size(); i++) {
            MostPlayedChampion currentChamp = champList.get(i);
            mostPlayedReport = mostPlayedReport + currentChamp.getChampionName() +
                    ": Played " + currentChamp.gettimesPlayed() + " times" + "\n";
        }
        return mostPlayedReport;
    }

    public String getReport(){
        return mostPlayedReportText;
    }
}
