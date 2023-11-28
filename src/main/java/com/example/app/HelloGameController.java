package com.example.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloGameController implements Initializable {

   @FXML
   private Text diemCao;
   private  int max_value ;

   @FXML
   private AnchorPane gameAnchorPane;

   @FXML
   public void scenePlaying(ActionEvent event) throws IOException {
        new SceneSwitch(gameAnchorPane,"playGameView.fxml");
   }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       max_value = gameScore.getIns().getMaxScore();
       if(diemCao!= null) {
           diemCao.setText("Điểm cao nhất là: " + max_value);
       }
    }

    @FXML
    public void huongDan(ActionEvent event) throws IOException {
        new SceneSwitch(gameAnchorPane,"huongDanView.fxml");
    }
}

// 770 540