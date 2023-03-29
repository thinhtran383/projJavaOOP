package com;

import java.io.IOException;
import javafx.fxml.FXML;

public class LoginController {

    @FXML
    private void btnLoginClick() throws IOException {
        App.setRoot("WellcomeFrm");
    }

}
