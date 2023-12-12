package com.example.app;

import com.example.cmd.Dictionary;
import com.example.cmd.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import static com.example.app.Speaker.speakWord;

// tìm kiếm, sửa, xóa, phát âm từ vựng
public class SearchWordController implements Initializable {
    @FXML
    TextField searchTextField;
    @FXML
    private TableView<Word> tableView;
    @FXML
    private TableColumn<Word, String> wordColumn;
    @FXML
    TextArea word_explainTextArea;
    @FXML
    TextField word_pronounceTextField;
    private Dictionary dictionary = Main.dictionary;
    private DataBaseConnection connectNow = new DataBaseConnection();
    private Connection connection = connectNow.getConnection();
    private ObservableList<Word> wordSearched = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resource) {
            try {
                wordColumn.setCellValueFactory(new PropertyValueFactory<>("word_target"));
                word_pronounceTextField.setEditable(false);
                // khi tra từ
                searchTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                    word_explainTextArea.setEditable(false);
                    word_explainTextArea.setText("");
                    word_pronounceTextField.setText("");
                    wordSearched = dictionary.searcher2(searchTextField.getText());
                    tableView.setItems(wordSearched);
                });

                // khi chọn từ
                tableView.getSelectionModel().selectedItemProperty().addListener((observableValue, wordSearchModel, t1) -> {
                    word_explainTextArea.setEditable(false);
                    word_pronounceTextField.setEditable(false);
                    if(tableView.getSelectionModel().getSelectedItem()!=null) {
                        word_explainTextArea.setText(tableView.getSelectionModel().getSelectedItem().getWord_explain());
                        word_pronounceTextField.setText(tableView.getSelectionModel().getSelectedItem().getWord_pronounce());
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    // hiển thị tất cả nghĩa của từ
    @FXML
    void showAllInfoWord(ActionEvent event) throws IOException {
        if(tableView.getSelectionModel().getSelectedItem()!=null) {
            try {
                String WordViewQuery = String.format("select html from av where word='%s'",
                        tableView.getSelectionModel().getSelectedItem().getWord_target());
                Statement statement = connection.createStatement();
                ResultSet queryOutput = statement.executeQuery(WordViewQuery);

                String html = queryOutput.getString("html");
                new HTMLToText(html);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // sửa từ (khi nhấn nút sửa từ thì cho phép người dùng sửa trực tiếp trên TextArea)
    @FXML
    void fixWord(ActionEvent event) throws IOException {
        word_explainTextArea.setEditable(true);
    }

    // xóa từ vựng
    @FXML
    void deleteWord(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("CONFIRMATION");
        alert.setContentText("Bạn chắc chắn muốn xóa từ này chứ ?");
        ButtonType buttonTypeYes = new ButtonType("YES", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeCancel = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYes,buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get().getButtonData() == ButtonBar.ButtonData.YES){
            try {
                String WordFixQuery = String.format("delete from av where word='%s'",
                        tableView.getSelectionModel().getSelectedItem().getWord_target());
                Statement statement = connection.createStatement();
                statement.execute(WordFixQuery);

                Word word = tableView.getSelectionModel().getSelectedItem();
                dictionary.deleteWord(word);
                wordSearched.remove(word);

            }catch (Exception e) {
                e.printStackTrace();
            }

            new ShowInformationAlert("SUCCESSFUL","Bạn đã xóa từ vựng thành công!");
        }
        word_explainTextArea.setEditable(false);
        word_explainTextArea.setText("");
        word_pronounceTextField.setText("");
    }

    // lưu lại phần đã sửa
    @FXML
    void save(ActionEvent event) throws IOException {
        if(tableView.getSelectionModel().getSelectedItem()==null){

        }
        else if(word_explainTextArea.getText()==""){
            new ShowInformationAlert("ERROR","Sửa không hợp lệ !");
        }
        else if(!tableView.getSelectionModel().getSelectedItem().getWord_explain().equals(word_explainTextArea.getText())||
                !tableView.getSelectionModel().getSelectedItem().getWord_pronounce().equals(word_pronounceTextField.getText())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("CONFIRMATION");
            alert.setContentText("Bạn chắc chắn muốn sửa từ này chứ ?");
            ButtonType buttonTypeYes = new ButtonType("YES", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeCancel = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes,buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get().getButtonData() == ButtonBar.ButtonData.YES){
                try {
                    Word word = tableView.getSelectionModel().getSelectedItem();
                    String htmlFixFrom = dictionary.lookup(word.getWord_target()).getWord_explain();
                    for(int i=0;i<htmlFixFrom.length();i++){
                        if(htmlFixFrom.charAt(i)==':'){
                            htmlFixFrom = htmlFixFrom.substring(i+2);
                        }
                    }
                    String htmlFixTo = word_explainTextArea.getText();
                    for(int i=0;i<htmlFixTo.length();i++){
                        if(htmlFixTo.charAt(i)==':'){
                            htmlFixTo = htmlFixTo.substring(i+2);
                        }
                    }
                    String WordFixQuery = String.format("update av set description = '%s',html = replace(html,'%s','%s') where word='%s'",
                            word_explainTextArea.getText(),
                            htmlFixFrom,htmlFixTo,
                            tableView.getSelectionModel().getSelectedItem().getWord_target());
                    Statement statement = connection.createStatement();
                    statement.execute(WordFixQuery);

                    dictionary.lookup(word.getWord_target()).setWord_explain(word_explainTextArea.getText());
                    dictionary.lookup(word.getWord_target()).setWord_pronounce(word_pronounceTextField.getText());

                    tableView.getSelectionModel().getSelectedItem().setWord_explain(word_explainTextArea.getText());
                    tableView.getSelectionModel().getSelectedItem().setWord_pronounce(word_pronounceTextField.getText());

                }catch (Exception e) {
                    e.printStackTrace();
                }
                new ShowInformationAlert("SUCCESFUL","Bạn đã sửa từ vựng thành công!");
            }
        }
        word_explainTextArea.setEditable(false);
    }

    // tìm kiếm
    @FXML
    void search(ActionEvent event) throws IOException {
        if(tableView.getSelectionModel().getSelectedItem()==null){
            tableView.getSelectionModel().selectFirst();
        }
        else {
            tableView.getSelectionModel().selectNext();
        }
    }

    // phát âm
    @FXML
    void speak(ActionEvent event) throws Exception {
        if(tableView.getSelectionModel().getSelectedItem()!=null) {
            speakWord(tableView.getSelectionModel().getSelectedItem().getWord_target());
        }
    }
}