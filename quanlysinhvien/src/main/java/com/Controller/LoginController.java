package com.Controller;

import java.io.IOException;
import java.util.ArrayList;

import com.App;
import com.Helper.AlertHelper;
import com.Helper.DataManager;
import com.Models.Account;

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

    public static String selectedRole = "Admin";
    private ArrayList<Account> studentAccounts = DataManager.getStudentAccounts();
    private ArrayList<Account> adminAccounts = DataManager.getAdminAccounts();

    @FXML
    public void initialize() { // tu dong goi khi chuong trinh chay
        setCbRole(); // xu ly combobox

    }

    private void setCbRole() {
        cbRole.getItems().addAll("Admin", "Student");
        cbRole.getSelectionModel().selectFirst(); // set gia tri mac dinh cho combobox
        cbRole.setOnAction(e -> {
            selectedRole = cbRole.getSelectionModel().getSelectedItem().toString();
            System.out.println(selectedRole);
        });
    }

    // private int checkAccount() { // kiem tra account co ton tai hay khong
    // if (txtEmail.getText().equals(adminAccounts.get(0).getUsername())
    // && txtPassword.getText().equals(adminAccounts.get(0).getPassword())) {
    // return 1;
    // }

    // if (txtEmail.getText().equals(studentAccounts.get(0).getUsername())
    // && txtPassword.getText().equals(studentAccounts.get(0).getPassword())) {
    // return 2;
    // }

    // return 0;
    // }

    private int checkAccount() { // kiem tra account co ton tai hay khong
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        for (Account account : adminAccounts) {
            if (email.equals(account.getUsername()) && password.equals(account.getPassword())) {
                return 1;
            }
        }
        for (Account account : studentAccounts) {
            if (email.equals(account.getUsername()) && password.equals(account.getPassword())) {
                return 2;
            }
        }
        return 0;
    }

    public void btnSubmit(ActionEvent actionEvent) throws IOException { // khi nhan nut
        if (txtEmail.getText().equals("") || txtPassword.getText().equals("")) { // neu khong nhap du thong tin bao loi
            // showLoginError("Vui lòng nhập đầy đủ thông tin");
            AlertHelper.showAlert(AlertType.ERROR, "Lỗi", null, "Vui lòng nhập đầy đủ thông tin");
            return;
        }

        if (checkAccount() == 1 && selectedRole.equals("Admin")) {
            AlertHelper.showAlert(AlertType.INFORMATION, "Thành công", null, "Đăng nhập thành công");
            App.setRoot("Frm");
        } else if (checkAccount() == 2 && selectedRole.equals("Student")) {
            AlertHelper.showAlert(AlertType.INFORMATION, "Thành công", null, "Đăng nhập thành công");
            App.setRoot("Frm");
        } else {
            AlertHelper.showAlert(AlertType.ERROR, "Lỗi", null, "Tài khoản hoặc mật khẩu không đúng ");
        }

        // System.out.println("Pass: " + getPassword());
    }

}
