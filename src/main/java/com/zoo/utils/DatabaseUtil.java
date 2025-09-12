package com.zoo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/zoo";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connessione al database riuscita!");

        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC non trovato.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Errore nella connessione al database.");
            e.printStackTrace();
        }
        return connection;
    }
}
