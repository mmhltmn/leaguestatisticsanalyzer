package edu.bsu.cs222.statsanalyzer;
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
    GeneralPane gt;
    ItemsPane it = new ItemsPane();

    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("League of Legends API Reader");
        displayWindow(primaryStage);
    }

    private void displayWindow(Stage primaryStage) throws FileNotFoundException {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        gt = new GeneralPane();
        generalPane = gt.makeGeneralAnchorPane();
        itemPane = it.makeItemAnchorPane();
        makeTabs();
        scene = new Scene(tab,(primaryScreenBounds.getWidth()/1.5),  (primaryScreenBounds.getHeight()/1.5));
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
}