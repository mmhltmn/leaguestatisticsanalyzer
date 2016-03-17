package edu.bsu.cs222.statsanalyzer;
import com.robrua.orianna.type.exception.APIException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class userInterface extends Application {

    private Button summonerButton  = new Button();
    private TextArea championText = new TextArea();
    private TextArea statBox = new TextArea();
    private Text statTitle = new Text("Stat Report: ");
    private Text championTitle = new Text("Most Played Champions: ");
    private AnchorPane layout;
    private TextField enterText;

    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("League of Legends API Reader");
        getSummonerStats(primaryStage);
    }


    private void displayWindow(Stage primaryStage) throws FileNotFoundException {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Pane mainWindow = new Pane();
        Scene scene = new Scene(mainWindow,(primaryScreenBounds.getWidth()/1.5),  (primaryScreenBounds.getHeight()/1.5));
        layout = new AnchorPane();
        primaryStage.setScene(scene);
        playerSearchLayoutSetup();
        mostPlayedChampionsTextArea();
        statReportDisplay();
        mainWindow.getChildren().add(layout);
        positionObjects();
        primaryStage.show();
    }

    private void playerSearchLayoutSetup() {
        Text searchInstructions = new Text("Search for a ranked player: ");
        searchInstructions.setLayoutX(20);
        searchInstructions.setLayoutY(25);
        enterText = new TextField();
        layout.getChildren().addAll(searchInstructions, enterText);
        makeSummonerSearchButton();
    }

    private void makeSummonerSearchButton() {
        summonerButton.setText("Search");
        layout.getChildren().add(summonerButton);
    }

    private void getSummonerStats(final Stage primaryStage) throws APIException, FileNotFoundException {
        displayWindow(primaryStage);
        summonerButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (event.getSource() == summonerButton) {
                    try {
                        APIRetriever apiRetriever = new APIRetriever(enterText.getText());
                        ArrayList<String> statReports = apiRetriever.grabStatReports();
                        championText.setText(statReports.get(1));
                        statBox.setText(statReports.get(0));

                    } catch (APIException e) {
                        enterText.setText("No match.");
                    } catch (FileNotFoundException e) {
                        enterText.setText("Missing files.");
                    }
                }
            }
        });
    }

    private void mostPlayedChampionsTextArea(){
        championText.setPrefSize(350, 320);
        layout.getChildren().addAll(championText, championTitle);
    }


    private void statReportDisplay(){
        statBox.setPrefSize(350, 320);
        layout.getChildren().addAll(statTitle, statBox);
    }

    private void positionObjects(){
        anchorSummonerSearch();
        anchorStatText();
        anchorChampionText();
    }

    private void anchorSummonerSearch(){
        AnchorPane.setTopAnchor(summonerButton, 10d);
        AnchorPane.setLeftAnchor(summonerButton, 420d);
        AnchorPane.setTopAnchor(enterText, 10d);
        AnchorPane.setLeftAnchor(enterText, 230d);
    }

    private void anchorChampionText(){
        AnchorPane.setLeftAnchor(championText, 400d);
        AnchorPane.setTopAnchor(championText, 200d);
        AnchorPane.setBottomAnchor(championText, 100d);
        AnchorPane.setBottomAnchor(championTitle, 200d);
        AnchorPane.setTopAnchor(championTitle, 150d);
        AnchorPane.setLeftAnchor(championTitle, 400d);
    }

    private void anchorStatText(){
        AnchorPane.setLeftAnchor(statBox, 10d);
        AnchorPane.setBottomAnchor(statBox, 100d);
        AnchorPane.setTopAnchor(statBox, 200d);
        AnchorPane.setLeftAnchor(statTitle, 10d);
        AnchorPane.setBottomAnchor(statTitle, 200d);
        AnchorPane.setTopAnchor(statTitle, 150d);
    }
}