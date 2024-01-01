package com.example.musicplayerfx.infrastructures;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {
    private static final String CONNECTION_URL =
            "jdbc:sqlserver://LENOVO-T470-BX;databaseName=MusicPlayer; integratedSecurity=true;encrypt=false;";

    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(CONNECTION_URL);
        }
        return connection;
    }
}
