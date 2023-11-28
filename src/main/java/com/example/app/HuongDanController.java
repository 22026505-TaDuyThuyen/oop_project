package com.example.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HuongDanController {

    @FXML
    private AnchorPane gameAnchorPane;
    public void quayLai(ActionEvent event) throws IOException {
        new SceneSwitch(gameAnchorPane,"helloGameView.fxml");
    }
}
