package edu.bsu.cs222.statsanalyzer;

import java.io.FileNotFoundException;
import java.util.*;

public class MostPlayedReport {
    private String mostPlayedReportText;

    public MostPlayedReport(Map statMap) throws FileNotFoundException {
        MostPlayedRetriever retriever = new MostPlayedRetriever(statMap);
        List<MostPlayedChampion> champList = retriever.getChampList();
        mostPlayedReportText = createSortedReport(champList);
    }

    protected String createSortedReport(List<MostPlayedChampion> champList){
        Collections.sort(champList);
        String mostPlayedReport = "";
        for (MostPlayedChampion champion : champList) {
            mostPlayedReport = mostPlayedReport + champion.getChampionName() +
                    ": Played " + champion.getTimesPlayed() + " times" + "\n";
        }
        return mostPlayedReport;
    }

    public String getReport(){
        return mostPlayedReportText;
    }
}
