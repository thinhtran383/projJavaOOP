package com.Controller;

import java.io.IOException;
import java.util.ArrayList;

import com.App;
import com.Helper.AlertHelper;
import com.Helper.DataManager;
import com.Models.Account;
import com.Models.Grade;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class LoginController {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private ComboBox<String> cbRole;
    @FXML
    private TableView<Grade> tableGrades;

    @FXML
    Button btnSubmit;

    public static String selectedRole = "Admin";
    private ArrayList<Account> studentAccounts = DataManager.getStudentAccounts();
    private ArrayList<Account> adminAccounts = DataManager.getAdminAccounts();

    public static String username;

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

    private int checkAccount() { // kiem tra account co ton tai hay khong
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        for (Account account : adminAccounts) {
            if (username.equals(account.getUsername()) && password.equals(account.getPassword())) {
                return 1;
            }
        }
        for (Account account : studentAccounts) {
            if (username.equals(account.getUsername()) && password.equals(account.getPassword())) {
                return 2;
            }
        }
        return 0;
    }

    public void btnSubmit(ActionEvent actionEvent) throws IOException { // khi nhan nut
        if (txtUsername.getText().equals("") || txtPassword.getText().equals("")) { // neu khong nhap du thong tin bao
                                                                                    // loi
            AlertHelper.showAlert(AlertType.ERROR, "Lỗi", null, "Vui lòng nhập đầy đủ thông tin");
            return;
        }

        if (checkAccount() == 1 && selectedRole.equals("Admin")) {
            AlertHelper.showAlert(AlertType.INFORMATION, "Thành công", null, "Đăng nhập thành công");
            App.setRoot("Frm");
        } else if (checkAccount() == 2 && selectedRole.equals("Student")) {
            AlertHelper.showAlert(AlertType.INFORMATION, "Thành công", null, "Đăng nhập thành công");
            username = txtUsername.getText();
            App.setRoot("Frm");
        } else {
            AlertHelper.showAlert(AlertType.ERROR, "Lỗi", null, "Tài khoản hoặc mật khẩu không đúng ");
        }

    }

}
