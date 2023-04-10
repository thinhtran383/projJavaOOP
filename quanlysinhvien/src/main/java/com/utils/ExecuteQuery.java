package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteQuery {
    private String query;

    private final static String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/quanlysinhvien";
    private final static String username = "root";
    private final static String password = "Matkhaunek123";

    public ExecuteQuery(String query) {
        this.query = query;
    }

    private static Connection getConnection() throws SQLException { // tạo kết nối đến database
        return DriverManager.getConnection(jdbcUrl, username, password);
    }

    // public static void executeQuery(String query) {
    // try (Connection connection = getConnection()) {
    // Statement statement = connection.createStatement();
    // ResultSet resultSet = statement.executeQuery(query);

    // while (resultSet.next()) {
    // // luu thong tin vao bien static

    // }
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // }

    public ResultSet executeQuery() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void executeUpdate() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
