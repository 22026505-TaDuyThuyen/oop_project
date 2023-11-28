module com.example.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires com.dlsc.formsfx;
    //requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    //requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires com.microsoft.sqlserver.jdbc;
    requires java.sql;
    requires javafx.base;
    requires controlsfx;
    requires org.xerial.sqlitejdbc;
    requires java.compiler;
    requires voicerss.tts;
    requires javafx.media;
    requires java.desktop;

    opens com.example.app to javafx.fxml;
    exports com.example.app;
    exports com.example.cmd;
    opens com.example.cmd to javafx.fxml;

}