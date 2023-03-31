package com.Controller;

import javafx.event.ActionEvent;
import com.Controller.LogoutController;

public class AdminController {

    public void onClickLogout(ActionEvent actionEvent) {
        // System.out.println("Logout");
        LogoutController.onClickLogout(actionEvent);
    }
}
