package com.Controller.Student;

import com.Helper.AlertHelper;
import com.constants.Regex;
import com.utils.ExecuteQuery;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class InputInfoController {
    public DatePicker pickDate;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtName;
    @FXML
    private ComboBox<String> cbGender;

    public void initialize() {
        cbGender.getItems().addAll("Male", "Female");
    }

    private void clear() {
        txtAddress.clear();
        txtEmail.clear();

        txtName.clear();
        txtPhone.clear();
        pickDate.setValue(null);
        cbGender.setValue(null);

    }

    private boolean checkNull() {
        if (txtAddress.getText().isEmpty() || txtEmail.getText().isEmpty() ||
                txtName.getText().isEmpty() || txtPhone.getText().isEmpty() || pickDate.getValue() == null ||
                cbGender.getValue() == null) {
            AlertHelper.showAlert(AlertType.ERROR, "Lỗi", null, "Vui lòng nhập đầy đủ thông tin");
            return false;
        }
        return true;
    }

    private boolean checkValidate() {
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        if (!email.matches(Regex.EMAIL)) {
            AlertHelper.showAlert(AlertType.ERROR, "Lỗi", null, "Email không hợp lệ");
            return false;
        }

        if (!phone.matches(Regex.PHONE)) {
            AlertHelper.showAlert(AlertType.ERROR, "Lỗi", null, "Số điện thoại không hợp lệ");
            return false;
        }
        return true;
    }

    private int chk;

    public void onClickSubmit() {
        if (chk == 1) {
            AlertHelper.showAlert(AlertType.ERROR, "Lỗi", null, "Bạn đã gửi thông tin rồi");
            return;
        }

        if (!checkNull())
            return;
        if (!checkValidate())
            return;
        String sql = "INSERT INTO pending (student_id, name, address, phone_number, email, date_of_birth, gender) " +
                "VALUES ('" + InforStudentController.getStudentId() + "', '" + txtName.getText() + "', '"
                + txtAddress.getText() + "', '" +
                txtPhone.getText() + "', '" + txtEmail.getText() + "', '" + pickDate.getValue() + "', '"
                + cbGender.getValue() + "')";

        ExecuteQuery executeQuery = new ExecuteQuery(sql);
        executeQuery.executeUpdate();
        System.out.println("Done");
        AlertHelper.showAlert(AlertType.INFORMATION, "Thông báo", null, "Gửi thông tin thành công");
        chk = 1;
    }
}