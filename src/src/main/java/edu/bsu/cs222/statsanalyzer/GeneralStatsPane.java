package edu.bsu.cs222.statsanalyzer;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.io.FileNotFoundException;

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

    public void addReports(ReportView reportView) throws FileNotFoundException {
        statBox.setText(reportView.getGeneralStatsReport());
        championText.setText(reportView.getMostPlayedReport());
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