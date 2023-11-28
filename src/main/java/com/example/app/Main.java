package com.example.app;

import com.example.cmd.Dictionary;
import com.example.cmd.Word;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.*;


public class Main extends Application  {
    /**
     * Project Ứng dụng học tiếng anh
     * Thành viên:
     * Trương Đức Quang 22026536
     * Võ Quang Sáng 22026526
     * Tạ Duy Thuyên 22026505
     */

    // Từ điển sử dụng xuyên suất chương trình chạy
    static Dictionary dictionary = new Dictionary();
    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("startView.fxml"));
            Parent root = fxmlLoader.load();
            AnchorPane anchorPane = new AnchorPane(root);
            AnchorPane anchorPane1 = new AnchorPane();
            anchorPane1.getChildren().add(anchorPane);
            Scene scene = new Scene(anchorPane1);
            scene.getStylesheets().add(Main.class.getResource("Style.css").toExternalForm());
            stage.setTitle("");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            // nạp từ điển
            DataBaseConnection connectNow = new DataBaseConnection();
            Connection connection = connectNow.getConnection();

            String WordViewQuery = "Select word,description,pronounce From av order by word";
            Statement statement = connection.createStatement();
            ResultSet queryOutput = statement.executeQuery(WordViewQuery);

            while (queryOutput.next()) {
                String queryWord_target = queryOutput.getString("word");
                String queryWord_explain = queryOutput.getString("description");
                String queryWord_pronounce = queryOutput.getString("pronounce");
                Word word = new Word(queryWord_target, queryWord_explain, queryWord_pronounce);
                dictionary.addWord(word);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
