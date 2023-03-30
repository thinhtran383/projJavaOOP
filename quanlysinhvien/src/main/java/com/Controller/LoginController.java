package com.Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.App;
import com.Models.Account;
import com.Models.ExecuteQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;
    @FXML
    private ComboBox cbRole;

    private String selectedRole = "Admin";
    private ArrayList<Account> studentAccounts = new ArrayList<>();
    private ArrayList<Account> adminAccounts = new ArrayList<>();

    @FXML
    public void initialize() { // xu ly combobox
        setCbRole();
        initAccount();
    }

    private void setCbRole() {
        cbRole.getItems().addAll("Admin", "Student");
        cbRole.getSelectionModel().selectFirst(); // set gia tri mac dinh cho combobox
        cbRole.setOnAction(e -> {
            selectedRole = cbRole.getSelectionModel().getSelectedItem().toString();
            System.out.println(selectedRole);
        });
    }

    private void showLoginError() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Lỗi đăng nhập");
        alert.setHeaderText(null);
        alert.setContentText("Tài khoản hoặc mật khẩu không chính xác.");
        alert.showAndWait();
    }

    private void initAccount() {
        ExecuteQuery queryAdmin = new ExecuteQuery("SELECT * FROM adminaccount"); // lay du lieu account admin tu
                                                                                  // database
        ResultSet resultSet = queryAdmin.executeQuery();
        try {
            while (resultSet.next()) {
                adminAccounts.add(new Account(resultSet.getString("username"),
                        resultSet.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ExecuteQuery queryStudent = new ExecuteQuery("SELECT * FROM studentaccount"); // lay du lieu account student tu
                                                                                      // database
        resultSet = queryStudent.executeQuery();
        try {
            while (resultSet.next()) {
                studentAccounts.add(new Account(resultSet.getString("username"),
                        resultSet.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int checkAccount() {

        if (txtEmail.getText().equals(adminAccounts.get(0).getUsername())
                && txtPassword.getText().equals(adminAccounts.get(0).getPassword())) {
            return 1;
        }

        if (txtEmail.getText().equals(studentAccounts.get(0).getUsername())
                && txtPassword.getText().equals(studentAccounts.get(0).getPassword())) {
            return 1;
        }

        return 0;
    }

    // private int checkAccount() {
    // ExecuteQuery query = new ExecuteQuery("SELECT * FROM studentaccount");
    // ResultSet resultSet = query.executeQuery();
    // try {
    // while (resultSet.next()) {
    // studentAccounts.add(new Account(resultSet.getString("username"),
    // resultSet.getString("password")));
    // }
    // } catch (SQLException e) {
    // System.out.println("Loi2");
    // e.printStackTrace();
    // }

    // if (txtEmail.getText().equals(studentAccounts.get(0).getUsername())
    // && txtPassword.getText().equals(studentAccounts.get(0).getPassword())) {
    // return 1;
    // }
    // return 0;
    // }

    public void btnSubmit(ActionEvent actionEvent) throws IOException {
        if (checkAccount() == 1 && selectedRole.equals("Admin")) {
            App.setRoot("CPAdminFrm");
        } else if (checkAccount() == 1 && selectedRole.equals("Student")) {
            App.setRoot("CPStudentFrm");
        } else {
            showLoginError();
        }

        // ExecuteQuery query = new ExecuteQuery("SELECT * FROM studentaccount");
        // ResultSet resultSet = query.executeQuery();
        // try {
        // while (resultSet.next()) {
        // // System.out.println(resultSet.getString("username"));
        // studentAccounts.add(new Account(resultSet.getString("username"),
        // resultSet.getString("password")));
        // System.out.println(studentAccounts.get(0).getUsername());
        // System.out.println(studentAccounts.get(0).getPassword());
        // }

        // } catch (SQLException e) {
        // e.printStackTrace();
        // }
    }

}
