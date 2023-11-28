package com.example.app;

import com.almasb.fxgl.texture.ColoredTexture;
import com.example.app.SceneSwitch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

// lớp điều khiển chính
public class RunController implements Initializable {

    @FXML
    AnchorPane centerAnchorPane;

    @FXML
    Button searchButton;

    @FXML
    Button addButton;

    @FXML
    Button translateButton;

    @FXML
    Button gameButton;

    boolean isSearchChoosing = false;

    boolean isAddChoosing = false;

    boolean isTranslateChoosing = false;

    boolean isGameChoosing = false;

    // bắt đầu chương trình và chon tra từ
    @Override
    public void initialize(URL url, ResourceBundle resource) {
        try {
            new SceneSwitch(centerAnchorPane, "searchView.fxml");
            searchChoosing();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // đi đến tra từ
    @FXML
    void goSearchWord(ActionEvent event) throws Exception {
        if(isSearchChoosing == false) {
            new SceneSwitch(centerAnchorPane, "searchView.fxml");
            searchChoosing();
        }
    }

    // đi tới thêm từ
    @FXML
    void goAddWord(ActionEvent event) throws Exception {
        if(isAddChoosing == false) {
            new SceneSwitch(centerAnchorPane, "addView.fxml");
            addChoosing();
        }
    }

    // đi tới dịch
    @FXML
    void goTranslate(ActionEvent event) throws Exception {
        if(isTranslateChoosing == false) {
            new SceneSwitch(centerAnchorPane, "translateView.fxml");
            translateChoosing();
        }
    }

    // đi tới game
    @FXML
    void goGame(ActionEvent event) throws Exception {
        if(isGameChoosing == false) {
            new SceneSwitch(centerAnchorPane, "helloGameView.fxml");
            gameChoosing();
        }
    }

    // khi tra từ được chọn
    void searchChoosing() {
        searchButton.setStyle( "-fx-background-color: #2cb1d0");
        addButton.setStyle(null);
        translateButton.setStyle(null);
        gameButton.setStyle(null);
        isSearchChoosing = true;
        isAddChoosing = false;
        isTranslateChoosing = false;
        isGameChoosing = false;
    }

    // khi thêm từ được chọn
    void addChoosing() {
        addButton.setStyle( "-fx-background-color: #2cb1d0");
        searchButton.setStyle(null);
        translateButton.setStyle(null);
        gameButton.setStyle(null);
        isSearchChoosing = false;
        isAddChoosing = true;
        isTranslateChoosing = false;
        isGameChoosing = false;
    }

    // khi dịch được chọn
    void translateChoosing() {
        translateButton.setStyle( "-fx-background-color: #2cb1d0");
        addButton.setStyle(null);
        searchButton.setStyle(null);
        gameButton.setStyle(null);
        isSearchChoosing = false;
        isAddChoosing = false;
        isTranslateChoosing = true;
        isGameChoosing = false;
    }

    // khi game được chọn
    void gameChoosing() {
        gameButton.setStyle( "-fx-background-color: #2cb1d0");
        addButton.setStyle(null);
        searchButton.setStyle(null);
        translateButton.setStyle(null);
        isSearchChoosing = false;
        isAddChoosing = false;
        isTranslateChoosing = false;
        isGameChoosing = true;
    }
}
