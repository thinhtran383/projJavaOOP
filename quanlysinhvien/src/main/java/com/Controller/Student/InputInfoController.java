package com.Controller.Student;
import com.App;
import com.Controller.LoginController;
import com.Helper.AlertHelper;
import com.Models.Grade;
import com.Models.Student;
import com.constants.Regex;
import com.utils.ExecuteQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class InputInfoController {
    @FXML
    private TextField txtName,txtEmail,txtSdt,txtAddress;
    @FXML
    private ComboBox<String> cbGender;
    @FXML
    private Button btSubmit;
    @FXML
    private DatePicker dpDob;

    public void initialize() {
        setCbGender();
    }
    private void setCbGender() {
        cbGender.getItems().addAll("Male", "Female");
    }
    public void onClickSubmit(ActionEvent actionEvent){
        String name = txtName.getText();
        String id = LoginController.idNow;
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String phone = txtSdt.getText();
        LocalDate dob = dpDob.getValue();
        String gender = cbGender.getValue();
        LocalDate time =
        if (!email.matches(Regex.EMAIL)) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Lỗi", null, "Email không hợp lệ");
            return;
        }

        if (!phone.matches(Regex.PHONE)) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Lỗi", null, "Số điện thoại không hợp lệ");
            return;
        }

        String sql = "INSERT INTO pending SET name = '" + name + "', address = '" + address
                + "', dob = '" + dob + "', email = '" + email + "', sdt = '" + phone
                + "', gender = '" + gender + "'";
        ExecuteQuery query = new ExecuteQuery(sql);
        query.executeUpdate();
        AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Thông báo", null, "Cập nhật thành công!");

    }

}
