package com.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnection {
    final String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/quanlysinhvien";
    final String username = "root";
    final String password = "Thinh@123";

    public static void main(String[] args) {
        executeQuery("SELECT * FROM studentaccount");
        // executeUpdate("INSERT INTO studentaccount () VALUES (1,'21010636',
        // 'Thinh@123')");
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/quanlysinhvien", "root", "Thinh@123");
    }

    public static void executeQuery(String query) {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println(resultSet.getString("username"));

                System.out.println(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void executeUpdate(String query) {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate(query);
            System.out.println(rowsAffected + " rows affected.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
