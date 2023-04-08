package com.Controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class FrmLoadController {

    private AnchorPane pane;

    @FXML
    private void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/CPAdminFrm.fxml"));
            AnchorPane root = loader.load();
            pane.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
