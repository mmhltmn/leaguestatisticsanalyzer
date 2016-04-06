package edu.bsu.cs222.statsanalyzer;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ItemsPane {

    private Text itemTitle = new Text("Items: ");
    private TextArea itemsText = new TextArea();
    private AnchorPane itemLayout;

    public ItemsPane(){
        itemLayout = new AnchorPane();
        itemsTextArea();
        anchorItemText();
    }

    public AnchorPane makeItemAnchorPane(){
        return itemLayout;
    }

    private void itemsTextArea(){
        itemsText.setPrefSize(350, 320);
        itemLayout.getChildren().addAll(itemsText, itemTitle);
    }

    private void anchorItemText(){
        AnchorPane.setLeftAnchor(itemsText, 10d);
        AnchorPane.setTopAnchor(itemsText, 200d);
        AnchorPane.setBottomAnchor(itemsText, 100d);
        AnchorPane.setLeftAnchor(itemTitle, 10d);
        AnchorPane.setBottomAnchor(itemTitle, 200d);
        AnchorPane.setTopAnchor(itemTitle, 150d);
    }
}