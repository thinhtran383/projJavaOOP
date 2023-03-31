package com.Controller;

import java.io.IOException;
import java.util.Optional;

import com.App;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

// xu ly nut dang xuat
public class LogoutController {
    public void initialize() {

    }

    private static boolean showLogoutAlert() {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Bạn có muốn đăng xuất?");
        alert.setTitle("Xác nhận");
        alert.setHeaderText(null);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    public static void onClickLogout(ActionEvent actionEvent) {
        if (showLogoutAlert()) {
            try {
                App.setRoot("LoginFrm");
            } catch (IOException e) {
                System.out.println("Không tìm thấy tài nguyên");
                e.printStackTrace();
            }
        } else {
            System.out.println("Cancel");
        }
    }

}
