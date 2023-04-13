package com.Controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class StudentController {
    @FXML
    private TabPane tabPane;

    @FXML
    private void initialize() {
        Tab tab1 = tabPane.getTabs().get(0);

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/InforStudentFrm.fxml"));
        try {
            Parent root = loader.load();
            tab1.setContent(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Tab tab2 = tabPane.getTabs().get(1);
        FXMLLoader loader2 = new FXMLLoader(
                getClass().getResource("/com/RegisterStudentFrm.fxml"));
        try {
            Parent root = loader2.load();
            tab2.setContent(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
