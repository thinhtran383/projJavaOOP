package com.Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.App;
import com.Models.Account;
import com.Models.ExecuteQuery;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    TextField txtEmail;
    @FXML
    TextField txtPassword;

    ArrayList<Account> list = new ArrayList<>();

    ExecuteQuery query = new ExecuteQuery("SELECT * FROM studentaccount");
    ResultSet resultSet = query.executeQuery();

    private int checkAccount() {
        try {
            while (resultSet.next()) {
                list.add(new Account(resultSet.getString("username"),
                        resultSet.getString("password")));
            }
        } catch (SQLException e) {
            System.out.println("Loi2");
            e.printStackTrace();
        }

        if (txtEmail.getText().equals(list.get(0).getUsername())
                && txtPassword.getText().equals(list.get(0).getPassword())) {
            return 1;
        }
        return 0;
    }

    public void btnRegisterClick(ActionEvent actionEvent) throws IOException {
        if (checkAccount() == 1) {
            System.out.println("Dit me may");
        } else {
            System.out.println("Sai tai khoan");
        }

        // ExecuteQuery query = new ExecuteQuery("SELECT * FROM studentaccount");
        // ResultSet resultSet = query.executeQuery();
        // try {
        // while (resultSet.next()) {
        // // System.out.println(resultSet.getString("username"));
        // list.add(new Account(resultSet.getString("username"),
        // resultSet.getString("password")));
        // System.out.println(list.get(0).getUsername());
        // System.out.println(list.get(0).getPassword());
        // }

        // } catch (SQLException e) {
        // e.printStackTrace();
        // }
    }
}
