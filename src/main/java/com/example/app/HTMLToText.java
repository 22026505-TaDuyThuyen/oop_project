package com.example.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

// hiển thị html tất cả thông tin từ vựng
public class HTMLToText {

    public HTMLToText(String html){
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(html);

        BorderPane borderPane = new BorderPane();

        AnchorPane anchorPaneTop = new AnchorPane();
        anchorPaneTop.setMinWidth(400);
        anchorPaneTop.setMinHeight(40);
        anchorPaneTop.setStyle("-fx-background-color: #afe6f3");

        AnchorPane anchorPaneBottom = new AnchorPane();
        anchorPaneBottom.setMinWidth(400);
        anchorPaneBottom.setMinHeight(40);
        anchorPaneBottom.setStyle("-fx-background-color: #afe6f3");

        AnchorPane anchorPaneLeft = new AnchorPane();
        anchorPaneLeft.setMinWidth(40);
        anchorPaneLeft.setMinHeight(460);
        anchorPaneLeft.setStyle("-fx-background-color: #afe6f3");

        AnchorPane anchorPaneRight = new AnchorPane();
        anchorPaneRight.setMinWidth(40);
        anchorPaneRight.setMinHeight(460);
        anchorPaneRight.setStyle("-fx-background-color: #afe6f3");

        borderPane.setTop(anchorPaneTop);
        borderPane.setBottom(anchorPaneBottom);
        borderPane.setCenter(webView);
        borderPane.setLeft(anchorPaneLeft);
        borderPane.setRight(anchorPaneRight);

        Scene scene = new Scene(borderPane, 400, 540);
        Stage stage = new Stage();
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }

}
