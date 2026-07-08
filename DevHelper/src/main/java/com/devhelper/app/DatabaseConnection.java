package com.devhelper.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {


    private static final String URL = "jdbc:mysql://localhost:3306/dev_helper_db";
    private static final String USER = "root";
    private static final String PASSWORD = "01557272090Hasan@";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}