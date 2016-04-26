package edu.bsu.cs222.statsanalyzer;
import com.robrua.orianna.type.exception.APIException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GeneralStatsPane {
    private Button summonerButton  = new Button();
    private TextArea championText = new TextArea();
    private TextArea statBox = new TextArea();
    private AnchorPane generalLayout;
    private TextField enterText;
    private Text statTitle = new Text("Stat Report:");
    private Text championTitle = new Text("Most Played Champions:");

    public GeneralStatsPane(){
        generalLayout = new AnchorPane();
        getSummonerStats();
        playerSearchLayoutSetup();
        mostPlayedChampionsTextArea();
        statReportDisplay();
        anchorObjects();
    }

    public AnchorPane makeGeneralAnchorPane(){
        return generalLayout;
    }

    private void getSummonerStats(){
        summonerButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event)  {
                if (event.getSource() == summonerButton) {
                    try {
                        StatReportRetriever reportRetriever = new StatReportRetriever(enterText.getText());
                        //ArrayList<String> statReports = reportRetriever.grabStatReports();
                        //championText.setText(statReports.get(1));
                       // statBox.setText(statReports.get(0));

                    } catch (APIException e) {
                        enterText.setText("No match.");
                   // } catch (FileNotFoundException e) {
                     //   enterText.setText("Missing files.");
                    }
                }
            }
        });
    }

    private void playerSearchLayoutSetup() {
        Text searchInstructions = new Text("Search for a ranked player:");
        searchInstructions.setLayoutX(10);
        searchInstructions.setLayoutY(35);
        enterText = new TextField();
        generalLayout.getChildren().addAll(searchInstructions, enterText);
        makeSummonerSearchButton();
    }

    private void makeSummonerSearchButton() {
        summonerButton.setText("Search");
        generalLayout.getChildren().add(summonerButton);
    }

    private void mostPlayedChampionsTextArea(){
        championText.setPrefSize(350, 320);
        generalLayout.getChildren().addAll(championText, championTitle);
    }

    private void statReportDisplay(){
        statBox.setPrefSize(350, 320);
        generalLayout.getChildren().addAll(statTitle, statBox);
    }
    private void anchorObjects(){
        anchorSummonerSearch();
        anchorStatText();
        anchorChampionText();
    }

    private void anchorSummonerSearch(){
        AnchorPane.setTopAnchor(summonerButton, 20d);
        AnchorPane.setLeftAnchor(summonerButton, 390d);
        AnchorPane.setTopAnchor(enterText, 20d);
        AnchorPane.setLeftAnchor(enterText, 195d);
    }

    private void anchorChampionText(){
        AnchorPane.setLeftAnchor(championText, 410d);
        AnchorPane.setTopAnchor(championText, 100d);
        AnchorPane.setBottomAnchor(championText, 100d);
        AnchorPane.setBottomAnchor(championTitle, 200d);
        AnchorPane.setTopAnchor(championTitle, 75d);
        AnchorPane.setLeftAnchor(championTitle, 410d);
    }

    private void anchorStatText(){
        AnchorPane.setLeftAnchor(statBox, 10d);
        AnchorPane.setBottomAnchor(statBox, 100d);
        AnchorPane.setTopAnchor(statBox, 100d);
        AnchorPane.setLeftAnchor(statTitle, 10d);
        AnchorPane.setBottomAnchor(statTitle, 200d);
        AnchorPane.setTopAnchor(statTitle, 75d);
    }
}