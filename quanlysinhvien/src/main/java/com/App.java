package com;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("LoginFrmBeta"), 500, 500);
        stage.setScene(scene);
        stage.show();

    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml +
                ".fxml"));
        return fxmlLoader.load();
    }

    // public static void setRootPop(String fxml, String title, boolean resizable)
    // throws IOException {
    // Stage stage = new Stage();
    // Scene newScene = new Scene(loadFXML(fxml), 700, 500);
    // stage.setResizable(resizable);
    // stage.setScene(newScene);
    // stage.setTitle(title);

    // // khi stage moi duoc hien thi thi stage cha se bi tat
    // Stage parentStage = (Stage) scene.getWindow();
    // parentStage.setOpacity(0.95);

    // // Re-enable the parent stage when the new stage is hidden
    // stage.setOnHidden(e -> parentStage.setOpacity(1.0));

    // // Show the new stage as a dialog and wait for it to close
    // stage.initModality(Modality.APPLICATION_MODAL);
    // stage.showAndWait();
    // }

    public static void main(String[] args) {
        launch();
        // baor ddax owr day 
        
    }

}