package com.example.app;

import javafx.scene.control.Alert;

public class ShowInformationAlert {
    ShowInformationAlert(String header, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
}
