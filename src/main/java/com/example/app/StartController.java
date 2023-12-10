package com.example.app;

import com.almasb.fxgl.entity.action.Action;
import com.almasb.fxgl.texture.ColoredTexture;
import impl.org.controlsfx.skin.StatusBarSkin;
import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.concurrent.Task;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;


// Mở đầu chương trình
public class StartController implements Initializable {
    @FXML
    private AnchorPane scene1AnchorPane;


    @Override
    public void initialize(URL url, ResourceBundle resource) {
    }

    // xem thông tin
    @FXML
    private void getInformation(ActionEvent event) throws IOException {
        String infor ="Ứng dụng được thực hiện bởi: \n" +
                "Tạ Duy Thuyên_22026505\n" +
                "Võ Quang Sáng_22026526\n" +
                "Trương Đức Quang_22026536";
        new ShowInformationAlert("Information",infor);
    }

    // bắt đầu chương trình
    @FXML
    void start(ActionEvent event) throws InterruptedException, IOException {
        new SceneSwitch(scene1AnchorPane,"runView.fxml");
    }
}
