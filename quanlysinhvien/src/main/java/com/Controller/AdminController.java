package com.Controller;//package com.Controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;

import javafx.scene.control.TabPane;

public class AdminController {

    public TabPane tabPane;

    @FXML
    private void initialize() {

        Tab tab1 = tabPane.getTabs().get(0);

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/SubjectMangement.fxml"));
        try {
            Parent root = loader.load();
            tab1.setContent(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Tab tab2 = tabPane.getTabs().get(1);

        FXMLLoader loader1 = new FXMLLoader(
                getClass().getResource("/com/StudentMangementFrm.fxml"));
        try {
            Parent root = loader1.load();
            tab2.setContent(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Tab tab3 = tabPane.getTabs().get(2);

        FXMLLoader loader2 = new FXMLLoader(
                getClass().getResource("/com/EnrollmentManagementFrm.fxml"));
        try {
            Parent root = loader2.load();
            tab3.setContent(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
