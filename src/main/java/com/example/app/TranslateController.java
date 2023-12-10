package com.example.app;

import com.detectlanguage.errors.APIError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.app.Translate.googleTranslate;
// dịch từ
public class TranslateController implements Initializable {
    @FXML
    Button switchButton;

    @FXML
    Label english;

    @FXML
    Label vietnam;

    @FXML
    TextArea translateFromTextArea;

    @FXML
    TextArea translateToTextArea;

    boolean translateToVietnamese = true;


    @Override
    public void initialize(URL url, ResourceBundle resource) {
        translateToTextArea.setEditable(false);
    }

    @FXML
    void switchTranslate(ActionEvent event) throws AbstractMethodError{
        if(translateToVietnamese) {
            english.setTranslateX(420);
            vietnam.setTranslateX(0);
            String switchText = translateFromTextArea.getText();
            translateFromTextArea.setText(translateToTextArea.getText());
            translateToTextArea.setText(switchText);
            translateToVietnamese = false;
        }
        else {
            english.setTranslateX(0);
            vietnam.setTranslateX(420);
            String switchText = translateFromTextArea.getText();
            translateFromTextArea.setText(translateToTextArea.getText());
            translateToTextArea.setText(switchText);
            translateToVietnamese = true;
        }
    }

    @FXML
    void translate(ActionEvent event) throws APIError, IOException {
        try {
            if (translateToVietnamese == true) {
                translateToTextArea.setText(googleTranslate("en", "vi", translateFromTextArea.getText()));
            } else {
                translateToTextArea.setText(googleTranslate("vi", "en", translateFromTextArea.getText()));
            }
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error");
            alert.setContentText("Lỗi kết nối mạng !");
            alert.show();
        }
    }
}
