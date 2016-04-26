package edu.bsu.cs222.statsanalyzer;
import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.game.Game;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.exception.APIException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class ItemsStatsPane{
    private Text itemTitle = new Text("Items: ");
    private TextArea itemsText = new TextArea();
    private AnchorPane itemLayout;

    public ItemsStatsPane(){
        itemLayout = new AnchorPane();
        //getItemStats();
        itemsTextArea();
        anchorItemText();
        //playerSearchLayoutSetup();
    }

    public AnchorPane makeItemAnchorPane(){
        return itemLayout;
    }

    /* private void getItemStats() throws APIException {
         summonerButton.setOnAction(new EventHandler<ActionEvent>() {
             public void handle(ActionEvent event) {
                 if (event.getSource() == summonerButton) {
                     try {
                         StatReportRetriever reportRetriever = new StatReportRetriever(enterText.getText());
                        // itemsText.setText(reportRetriever.grabItemReport());
                        // List<Game> recentGames = RiotAPI.getRecentGames(name);

                     } catch (APIException e) {
                         enterText.setText("No match.");
                     }
                 }
             }
         });
     }
 */

    public void addItemReport(String name) throws FileNotFoundException {
        List<Game> recentGames = RiotAPI.getRecentGames(name);
        ItemWinLossReport itemWinLossReport = new ItemWinLossReport(recentGames);
        itemsText.setText(itemWinLossReport.getReport());
    }

    private void itemsTextArea(){
        itemsText.setPrefSize(700, 900);
        itemLayout.getChildren().addAll(itemsText, itemTitle);
    }

    private void anchorItemText(){
        AnchorPane.setLeftAnchor(itemsText, 10d);
        AnchorPane.setTopAnchor(itemsText, 125d);
        AnchorPane.setBottomAnchor(itemsText, 100d);
        AnchorPane.setLeftAnchor(itemTitle, 10d);
        AnchorPane.setBottomAnchor(itemTitle, 200d);
        AnchorPane.setTopAnchor(itemTitle, 75d);
    }

}