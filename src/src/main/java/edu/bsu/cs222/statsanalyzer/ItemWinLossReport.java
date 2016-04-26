package edu.bsu.cs222.statsanalyzer;

import com.robrua.orianna.type.core.game.Game;
import com.robrua.orianna.type.core.staticdata.Item;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemWinLossReport {
    private String winLossReportText;

    public ItemWinLossReport(List<Game> recentGames){
        ItemWinLossCalculator itemWinLossCalculator = new ItemWinLossCalculator(recentGames);
        List<GameItem> gamesList = itemWinLossCalculator.makeListForReport();
        createWinLossReport(gamesList);
    }

    @SuppressWarnings("unchecked")
    //This method will always work even though the type cast is unchecked.
    private void createWinLossReport(List<GameItem> gamesList) {
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMaximumFractionDigits(1);
        winLossReportText = "";
        for(int i=0;i<gamesList.size();i++){
            GameItem currentGame = gamesList.get(i);
            winLossReportText += currentGame.getName();
            winLossReportText += ": Bought ";
            winLossReportText += currentGame.getGamesPlayed();
            winLossReportText += " times, Win Rate: ";
            winLossReportText += percentFormat.format(currentGame.getWinRate());
            winLossReportText += "\n";
        }
    }

    public String getWinLossReport(){
        return winLossReportText;
    }
}
