package com.Controller;

import java.io.IOException;

import com.App;
import com.Helper.AlertHelper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class FrmLoadController {

    public Text lblLogin;
    @FXML
    private AnchorPane pane;

    @FXML
    private void initialize() {
        loadPane();
    }

    private void loadPane() {
        String frm = "";
        if (LoginController.selectedRole.equals("Admin")) {
            lblLogin.setText("Admin Control Panel");
            frm = "/com/CPAdminFrmBeta.fxml";
        } else {
            lblLogin.setText("Student Control Panel");
            frm = "/com/CPStudentFrm.fxml";
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(frm));
            AnchorPane adminPane = loader.load();
            pane.getChildren().add(adminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickLogout(ActionEvent actionEvent) {
        if (AlertHelper.showConfirmation("Bạn có muốn đăng xuất?")) {
            try {
                App.setRoot("LoginFrmBeta");
            } catch (IOException e) {
                System.out.println("Không tìm thấy tài nguyên");
                e.printStackTrace();
            }
        } else {
            System.out.println("Cancel");
        }
    }

}
