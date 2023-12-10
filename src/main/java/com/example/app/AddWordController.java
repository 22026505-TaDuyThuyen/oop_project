package com.example.app;

import com.example.cmd.Dictionary;
import com.example.cmd.Word;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

// thêm từ vựng
public class AddWordController {

    @FXML
    TextField addWord_target;

    @FXML
    TextField addWord_pronounce;

    @FXML
    TextArea addWord_explain;

    private Dictionary dictionary = Main.dictionary;

    // thêm từ
    @FXML
    void addWord(ActionEvent event) throws SQLException {
        String AddWord_target = addWord_target.getText();
        String AddWord_explain = addWord_explain.getText();
        String AddWord_pronounce = addWord_pronounce.getText();

        // kiểm tra có để trống ô
        if(AddWord_target.equals("")||AddWord_explain.equals("")){
            String Error ="Thêm từ không thành công !\n"
                    +"Không được để trống ô";
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setHeaderText("Error");
            error.setContentText(Error);
            error.show();
        }
        // kiểm tra xem từ đã có trong từ điển chưa
        else if (dictionary.lookup(AddWord_target)!=null) {
            Word word = dictionary.lookup(AddWord_target);
            String Error =String.format("Từ này đã có trong từ điển !\n"
                    +"Nghĩa tiếng việt: %s\n"
                    +"Phát âm: %s\n",
                    word.getWord_explain(), word.getWord_pronounce());
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setHeaderText("Error");
            error.setContentText(Error);
            error.show();
        }
        // thêm từ
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("CONFIRMATION");
            alert.setContentText("Bạn chắc chắn muốn thêm từ này chứ ?");
            ButtonType buttonTypeYes = new ButtonType("YES", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeCancel = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes,buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get().getButtonData() == ButtonBar.ButtonData.YES){
                try {
                    DataBaseConnection connectNow = new DataBaseConnection();
                    Connection connection = connectNow.getConnection();
                    String AddWordQuery = String.format("Insert into av(word,html,description,pronounce) " +
                                    "values('%s','<h1>%s</h1><h3><i>/%s/</i></h3><h2>%s</h2>','%s','%s')",
                            AddWord_target,AddWord_target,AddWord_pronounce,AddWord_explain, AddWord_explain, AddWord_pronounce);
                    Statement statement = connection.createStatement();
                    statement.execute(AddWordQuery);
                    dictionary.addWordSort(new Word(AddWord_target,AddWord_explain,AddWord_pronounce));

                    new ShowInformationAlert("SUCCESSFUL","Thêm từ vựng thành công!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
