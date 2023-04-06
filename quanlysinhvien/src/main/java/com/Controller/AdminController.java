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
        SubjectMangement subjectMangement = new SubjectMangement();

        Tab tab1 = tabPane.getTabs().get(0);

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/SubjectMangement.fxml"));
        try {
            Parent root = loader.load();
            tab1.setContent(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
