package edu.bsu.cs222.statsanalyzer;
import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.Region;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.FileNotFoundException;

public class UserInterface extends Application {
    private Scene scene;
    private AnchorPane generalPane = new AnchorPane();
    private AnchorPane itemPane = new AnchorPane();
    private AnchorPane search = new AnchorPane();
    private Button summonerButton  = new Button();
    private TabPane tab = new TabPane();
    private VBox pane = new VBox(8);
    GeneralStatsPane gt = new GeneralStatsPane();
    ItemsStatsPane it = new ItemsStatsPane();
    private TextField enterText;

    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception{
        chooseRegionAndKey();
        primaryStage.setTitle("League of Legends API Reader");
        generalPane = gt.makeGeneralAnchorPane();
        itemPane = it.makeItemAnchorPane();
        displayWindow(primaryStage);
    }

    /*
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
*/

    private void displayWindow(Stage primaryStage) throws FileNotFoundException {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        makeSearchPane();
        makeTabs();
        pane.getChildren().addAll(search, tab);
        scene = new Scene(pane,(primaryScreenBounds.getWidth()/1.5), (primaryScreenBounds.getHeight()/1.5));
        primaryStage.setScene(scene);
        importStyleSheet();
        primaryStage.show();
    }

    private void importStyleSheet(){
        String cssFile = UserInterface.class.getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(cssFile);
    }

    private void makeTabs(){
        tab.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        Tab general = new Tab("General");
        Tab items = new Tab("Items");
        general.setContent(generalPane);
        items.setContent(itemPane);
        tab.getTabs().addAll(general, items);
    }

    private void makeSearchPane(){
        Text searchInstructions = new Text("Search for a ranked player:");
        searchInstructions.setLayoutX(10);
        searchInstructions.setLayoutY(35);
        enterText = new TextField();
        search.getChildren().addAll(searchInstructions, enterText);
        makeSummonerSearchButton();
        anchorSummonerSearch();
    }

    private void makeSummonerSearchButton() {
        summonerButton.setText("Search");
        search.getChildren().add(summonerButton);
    }

    private void anchorSummonerSearch(){
        AnchorPane.setTopAnchor(summonerButton, 20d);
        AnchorPane.setLeftAnchor(summonerButton, 390d);
        AnchorPane.setTopAnchor(enterText, 20d);
        AnchorPane.setLeftAnchor(enterText, 195d);
    }

    private void chooseRegionAndKey() {  //Tested
        RiotAPI.setRegion(Region.NA);
        RiotAPI.setAPIKey("70f18885-23eb-4106-ad64-04554ffc5b4d");
    }
}