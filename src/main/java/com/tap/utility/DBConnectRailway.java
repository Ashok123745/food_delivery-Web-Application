package com.tap.utility; // Use your actual package name

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectRailway {
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");

                String host = "altaria.proxy.rlwy.net";
                String port = "15419";
                String dbName = "railway";
                String user = "root";
                String pass = "aTbqRYzKwhTYGzsXqyLtXmREHlrKifsl";

                String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?useSSL=false&allowPublicKeyRetrieval=true";

                connection = DriverManager.getConnection(url, user, pass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}