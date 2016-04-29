package edu.bsu.cs222.statsanalyzer;

import com.robrua.orianna.type.core.game.Game;

import java.text.NumberFormat;
import java.util.List;

public class ItemWinLossReport {
    private String winLossReportText;

    public ItemWinLossReport(List<Game> recentGames){
        ItemWinLossCalculator itemWinLossCalculator = new ItemWinLossCalculator();
        itemWinLossCalculator.calculateItemWinsAndLosses(recentGames);
        List<GameItem> gamesList = itemWinLossCalculator.makeListForReport();
        createWinLossReport(gamesList);
    }

    @SuppressWarnings("unchecked")
    //This method will always work even though the type cast is unchecked.
    private void createWinLossReport(List<GameItem> gamesList) {
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMaximumFractionDigits(1);
        winLossReportText = "";
        for(GameItem game : gamesList){
            winLossReportText += game.getName();
            winLossReportText += ": Bought ";
            winLossReportText += game.getGamesPlayed();
            winLossReportText += " times, Win Rate: ";
            winLossReportText += percentFormat.format(game.getWinRate());
            winLossReportText += "\n";
        }
    }

    public String getReport(){
        return winLossReportText;
    }
}
