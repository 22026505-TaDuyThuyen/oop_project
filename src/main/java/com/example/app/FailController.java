package com.example.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FailController implements Initializable {
    @FXML
    private Text diemCao1;

    @FXML
    private AnchorPane gameAnchorPane;
    public void returnMenu(ActionEvent event) throws IOException {
        new SceneSwitch(gameAnchorPane,"helloGameView.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int diemCuaBan= gameScore.getIns().getDiemCuaBan();
        diemCao1.setText("Điểm của bạn là: " + diemCuaBan);
    }

}
