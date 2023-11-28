package com.example.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.ResultSet;

// kết nối DataBase
public class DataBaseConnection {
    public Connection connection;

    public Connection getConnection() {
        try {
            String url = "jdbc:sqlite:"+ Main.class.getResource("dictionary.db");
            connection = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
