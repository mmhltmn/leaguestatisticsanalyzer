package edu.bsu.cs222.statsanalyzer;
import com.robrua.orianna.type.exception.APIException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ItemsPane{
    private Text itemTitle = new Text("Items: ");
    private TextArea itemsText = new TextArea();
    private AnchorPane itemLayout;
    private TextField enterText;
    private Button summonerButton = new Button();

    public ItemsPane(){
        itemLayout = new AnchorPane();
        getItemStats();
        itemsTextArea();
        anchorItemText();
        playerSearchLayoutSetup();
    }

    public AnchorPane makeItemAnchorPane(){
        return itemLayout;
    }

    private void getItemStats() throws APIException {
        summonerButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (event.getSource() == summonerButton) {
                    try {
                        StatReportRetriever reportRetriever = new StatReportRetriever(enterText.getText());
                        itemsText.setText(reportRetriever.grabItemReport());
                    } catch (APIException e) {
                        enterText.setText("No match.");
                    }
                }
            }
        });
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

    private void playerSearchLayoutSetup() {
        Text itemSearchInstructions = new Text("Search for a ranked player:");
        itemSearchInstructions.setLayoutX(10);
        itemSearchInstructions.setLayoutY(35);
        enterText = new TextField();
        summonerButton.setText("Search");
        itemLayout.getChildren().add(summonerButton);
        itemLayout.getChildren().addAll(itemSearchInstructions, enterText);
        anchorItemSummonerSearch();
    }

    private void anchorItemSummonerSearch(){
        AnchorPane.setTopAnchor(summonerButton, 20d);
        AnchorPane.setLeftAnchor(summonerButton, 390d);
        AnchorPane.setTopAnchor(enterText, 20d);
        AnchorPane.setLeftAnchor(enterText, 195d);
    }
}