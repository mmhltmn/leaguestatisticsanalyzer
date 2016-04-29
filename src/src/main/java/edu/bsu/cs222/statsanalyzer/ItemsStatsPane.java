package edu.bsu.cs222.statsanalyzer;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;

public class ItemsStatsPane{
    private Text itemTitle = new Text("Items: ");
    private TextArea itemsText = new TextArea();
    private AnchorPane itemLayout;

    public ItemsStatsPane(){
        itemLayout = new AnchorPane();
        itemsTextArea();
        anchorItemText();
    }

    public AnchorPane makeItemAnchorPane(){
        return itemLayout;
    }

    public void addItemReport(ReportView reportView) throws FileNotFoundException {
        itemsText.setText(reportView.getWinLossReport());
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

}