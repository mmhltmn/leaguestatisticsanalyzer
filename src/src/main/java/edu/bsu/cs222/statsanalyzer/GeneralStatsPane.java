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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GeneralStatsPane {
    private TextArea championText = new TextArea();
    private TextArea statBox = new TextArea();
    private AnchorPane generalLayout;
    private Text statTitle = new Text("Stat Report:");
    private Text championTitle = new Text("Most Played Champions:");

    public GeneralStatsPane(){
        generalLayout = new AnchorPane();
        mostPlayedChampionsTextArea();
        statReportDisplay();
        anchorObjects();
    }

    public AnchorPane makeGeneralAnchorPane(){
        return generalLayout;
    }

    public void addReports(String name) throws FileNotFoundException {
        Summoner player = RiotAPI.getSummonerByName(name);
        Map statMap = RiotAPI.getRankedStats(player);
        GeneralStatsReport generalStatsReport = new GeneralStatsReport(statMap);
        MostPlayedReport mostPlayedReport = new MostPlayedReport(statMap);
        statBox.setText(generalStatsReport.getReport());
        championText.setText(mostPlayedReport.getReport());
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
        anchorStatText();
        anchorChampionText();
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