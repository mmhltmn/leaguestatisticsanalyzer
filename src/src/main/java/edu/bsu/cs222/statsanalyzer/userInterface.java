package edu.bsu.cs222.statsanalyzer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class userInterface extends Application {

    private Button summonerButton  = new Button();
    Pane mainWindow;
    private TextField enterText;

    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("League of Legends API Reader");
        displayWindow(primaryStage);
    }

    private void displayWindow(Stage primaryStage){
        mainWindow = new Pane();
        Scene scene = new Scene(mainWindow, 800, 800);
        primaryStage.setScene(scene);
        playerSearchLayoutSetup();
        //grabSummoner();
        //will pass a string that contains champion list through 'mostPlayedChampionsTextArea()'
        mostPlayedChampionsTextArea();
        //Same type of string pass as mentioned above for 'statReportDisplay()'
        statReportDisplay();
        primaryStage.show();
    }

    private void playerSearchLayoutSetup() {
        Text playerText = new Text("Search for a ranked player: ");
        playerText.setLayoutX(20);
        playerText.setLayoutY(25);
        enterText = new TextField();
        enterText.setLayoutX(20);
        enterText.setLayoutY(35);
        mainWindow.getChildren().addAll(playerText, enterText);
        makeSummonerSearchButton();

    }

    private void makeSummonerSearchButton() {
        summonerButton.setText("Search");
        summonerButton.setLayoutX(20);
        summonerButton.setLayoutY(75);
        mainWindow.getChildren().add(summonerButton);
    }

    private void getSummonerName() {
        final String summoner = enterText.getText();
        summonerButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (event.getSource() == summonerButton) {
                    if(summoner == null){
                        //Print no summoner found.
                    }else{
                        //search for name through API
                        //DisplayFoundSummonerName()
                    }
                }
            }
        });
    }

    private void mostPlayedChampionsTextArea(){
        TextArea championText = new TextArea();
        Text championTitle = new Text("Most Played Champions: ");
        championTitle.setLayoutX(600);
        championTitle.setLayoutY(90);
        championText.setPrefSize(180, 400);
        championText.setLayoutX(600);
        championText.setLayoutY(100);
        mainWindow.getChildren().addAll(championText, championTitle);
       //for(int i = 0; i < champions.length() - 1; i++){
       //     championText.appendText(String.valueOf(i));
      // }

    }

    private void statReportDisplay(){
        Text statTitle = new Text("Stat Report: ");
        statTitle.setLayoutX(20);
        statTitle.setLayoutY(490);
        TextArea statBox = new TextArea();
        statBox.setPrefSize(350, 320);
        statBox.setLayoutX(20);
        statBox.setLayoutY(500);
        mainWindow.getChildren().addAll(statTitle, statBox);
    }

}
