package com;

import java.io.IOException;
import javafx.fxml.FXML;

public class LoginController {

    @FXML
    private void btnRegisterClick() throws IOException {
        App.setRoot("WellcomeFrm");
    }
}
