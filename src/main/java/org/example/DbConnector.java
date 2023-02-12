package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private static final String URL = "jdbc:postgresql://localhost:5432/jdbc_example";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin123";

    public static Connection connect(){
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("connected to DB");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
