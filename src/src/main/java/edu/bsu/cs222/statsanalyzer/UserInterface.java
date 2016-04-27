package edu.bsu.cs222.statsanalyzer;
import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.exception.APIException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserInterface extends Application {
    private Button summonerButton  = new Button();
    private VBox pane = new VBox(8);
    private GeneralStatsPane gt = new GeneralStatsPane();
    private ItemsStatsPane it = new ItemsStatsPane();
    private TextField enterText;
    private ExecutorService service = Executors.newCachedThreadPool();

    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception{
        chooseRegionAndKey();
        fillReportBoxes();
        primaryStage.setTitle("League of Legends API Reader");
        displayWindow(primaryStage);
    }

    private void fillReportBoxes(){
        summonerButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event)  {
                if (event.getSource() == summonerButton) {
                    service.execute(new Runnable() {
                        public void run() {
                                    try {
                                        it.addItemReport(enterText.getText());
                                        gt.addReports(enterText.getText());
                                } catch (APIException e) {
                                    enterText.setText("No match.");
                                } catch (FileNotFoundException e) {
                                    enterText.setText("Missing files.");
                            }
                        }
                    });
                }
            }
        });
    }

    private void displayWindow(Stage primaryStage) throws FileNotFoundException {
        Scene scene;
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        AnchorPane search = makeSearchPane();
        TabPane tab = makeTabs();
        pane.getChildren().addAll(search, tab);
        scene = new Scene(pane,(primaryScreenBounds.getWidth()/1.5), (primaryScreenBounds.getHeight()/1.5));
        primaryStage.setScene(scene);
        importStyleSheet(scene);
        primaryStage.show();
    }

    private void importStyleSheet(Scene scene){
        String cssFile = UserInterface.class.getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(cssFile);
    }

    private TabPane makeTabs(){
        TabPane tab = new TabPane();
        tab.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        Tab general = new Tab("General");
        Tab items = new Tab("Items");
        AnchorPane generalPane = gt.makeGeneralAnchorPane();
        AnchorPane itemPane = it.makeItemAnchorPane();
        general.setContent(generalPane);
        items.setContent(itemPane);
        tab.getTabs().addAll(general, items);
        return tab;
    }

    private AnchorPane makeSearchPane(){
        AnchorPane search = new AnchorPane();
        Text searchInstructions = new Text("Search for a ranked player:");
        searchInstructions.setLayoutX(10);
        searchInstructions.setLayoutY(35);
        enterText = new TextField();
        search.getChildren().addAll(searchInstructions, enterText);
        makeSummonerSearchButton(search);
        anchorSummonerSearch();
        return search;
    }

    private void makeSummonerSearchButton(AnchorPane search) {
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