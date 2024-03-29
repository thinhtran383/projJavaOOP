package com.Controller;//package com.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;

import javafx.scene.control.TabPane;

public class AdminController implements Initializable {
    @FXML
    private TabPane tabPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Tab tab1 = tabPane.getTabs().get(0);
        loadFrm(tab1, "/com/SubjectMangement.fxml");

        Tab tab2 = tabPane.getTabs().get(1);
        loadFrm(tab2, "/com/StudentMangementFrm.fxml");

        Tab tab3 = tabPane.getTabs().get(2);
        loadFrm(tab3, "/com/EnrollmentManagementFrm.fxml");

        Tab tab4 = tabPane.getTabs().get(3);
        loadFrm(tab4, "/com/GradesManagementFrm.fxml");

        Tab tab5 = tabPane.getTabs().get(4);
        loadFrm(tab5, "/com/notificationFrm.fxml");
    }

    private void loadFrm(Tab tab, String path) {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(path));
        try {
            Parent root = loader.load();
            tab.setContent(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
