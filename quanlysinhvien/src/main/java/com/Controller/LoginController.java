package com.Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.App;
import com.Models.Account;
import com.utils.ExecuteQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class LoginController {

    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private ComboBox<String> cbRole;

    @FXML
    Button btnSubmit;

    private String selectedRole = "Admin";
    private ArrayList<Account> studentAccounts = new ArrayList<>();
    private ArrayList<Account> adminAccounts = new ArrayList<>();

    @FXML
    public void initialize() { // tu dong goi khi chuong trinh chay
        setCbRole(); // xu ly combobox
        initAccount(); // load accout admin, student tu db
    }

    private void setCbRole() {
        cbRole.getItems().addAll("Admin", "Student");
        cbRole.getSelectionModel().selectFirst(); // set gia tri mac dinh cho combobox
        cbRole.setOnAction(e -> {
            selectedRole = cbRole.getSelectionModel().getSelectedItem().toString();
            System.out.println(selectedRole);
        });
    }

    private void showLoginError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Lỗi đăng nhập");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void initAccount() {
        ExecuteQuery queryAdmin = new ExecuteQuery("SELECT * FROM adminaccount"); // lay du lieu account admin tu
                                                                                  // database

        ResultSet resultSet = queryAdmin.executeQuery(); // lay tai khoan admin tu db
        try {
            while (resultSet.next()) { // lay du lieu theo thuoc tinh db
                adminAccounts.add(new Account(resultSet.getString("username"),
                        resultSet.getString("password")));
            }
        } catch (SQLException e) {
            System.out.println("Không thể lấy dữ liệu từ database");
            e.printStackTrace();
        }

        ExecuteQuery queryStudent = new ExecuteQuery("SELECT * FROM studentaccount"); // lay du lieu account student tu
                                                                                      // db
        resultSet = queryStudent.executeQuery();
        try {
            while (resultSet.next()) {
                studentAccounts.add(new Account(resultSet.getString("username"),
                        resultSet.getString("password")));
            }
        } catch (SQLException e) {
            System.out.println("Không thể lấy dữ liệu từ database");
            e.printStackTrace();
        }
    }

    private int checkAccount() { // kiem tra account co ton tai hay khong
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

    public void btnSubmit(ActionEvent actionEvent) throws IOException { // khi nhan nut
        if (txtEmail.getText().equals("") || txtPassword.getText().equals("")) { // neu khong nhap du thong tin bao loi
            showLoginError("Vui lòng nhập đầy đủ thông tin");
            return;
        }

        if (checkAccount() == 1 && selectedRole.equals("Admin")) {
            App.setRoot("CPAdminFrm"); // khoi chay CPAdminFrm
        } else if (checkAccount() == 1 && selectedRole.equals("Student")) {
            App.setRoot("CPStudentFrm");
        } else {
            showLoginError("Sai tên đăng nhập hoặc mật khẩu");
        }

        // System.out.println("Pass: " + getPassword());
    }

}
