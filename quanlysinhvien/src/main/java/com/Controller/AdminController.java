package com.Controller;

import javafx.event.ActionEvent;

import java.io.IOException;

import com.App;
import com.Controller.LogoutController;

public class AdminController {
    // public void onClickLogout(ActionEvent actionEvent) {
    // LogoutController.onClickLogout(actionEvent);
    // // try {
    // // App.setRootPop("InputInformation", "Cập nhật thông tin", false);
    // // } catch (IOException e) {
    // // System.out.println("Không tìm thấy tài nguyên");
    // // e.printStackTrace();
    // // }
    // }

    public void onClickLogout(ActionEvent actionEvent) {
        // System.out.println("Logout");
        LogoutController.onClickLogout(actionEvent);
    }

}
