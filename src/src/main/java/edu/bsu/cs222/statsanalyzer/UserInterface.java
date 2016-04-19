package edu.bsu.cs222.statsanalyzer;
import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.Region;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.FileNotFoundException;

public class UserInterface extends Application {
    private Scene scene;
    private AnchorPane generalPane = new AnchorPane();
    private AnchorPane itemPane = new AnchorPane();
    private TabPane tab = new TabPane();
    GeneralStatsPane gt = new GeneralStatsPane();
    ItemsStatsPane it = new ItemsStatsPane();

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

    private void displayWindow(Stage primaryStage) throws FileNotFoundException {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        makeTabs();
        scene = new Scene(tab,(primaryScreenBounds.getWidth()/1.5), (primaryScreenBounds.getHeight()/1.5));
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

    private void chooseRegionAndKey() {  //Tested
        RiotAPI.setRegion(Region.NA);
        RiotAPI.setAPIKey("70f18885-23eb-4106-ad64-04554ffc5b4d");
    }
}