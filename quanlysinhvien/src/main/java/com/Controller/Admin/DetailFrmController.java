package com.Controller.Admin;

import java.sql.ResultSet;

import com.Helper.AlertHelper;
import com.Helper.DataManager;
import com.Models.Student;
import com.utils.ExecuteQuery;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

public class DetailFrmController {
    @FXML
    private Label lbNameOld;
    @FXML
    private Label lbGenderOld;
    @FXML
    private Label lbEmailOld;
    @FXML
    private Label lbPhoneOld;
    @FXML
    private Label lbDobOld;
    @FXML
    private Label lbAddressOld;

    @FXML
    private Label lbNameNew;
    @FXML
    private Label lbGenderNew;
    @FXML
    private Label lbEmailNew;
    @FXML
    private Label lbPhoneNew;
    @FXML
    private Label lbPDobNew;
    @FXML
    private Label lbAddressNew;

    private ObservableList<Student> studentsList = DataManager.getStudentsList();

    public void initialize() {
        showOldInfor();
        showNewInfor();
    }

    private void showOldInfor() {
        for (Student student : studentsList) {
            if (student.getStudentId().equals(NotificationController.getStudentId())) {
                lbNameOld.setText(student.getStudentName());
                lbGenderOld.setText(student.getStudentGender());
                lbEmailOld.setText(student.getStudentEmail());
                lbPhoneOld.setText(student.getStudentPhone());
                lbDobOld.setText(student.getStudentBirthday().toString());
                lbAddressOld.setText(student.getStudentAddress());
            }
        }
    }

    private void showNewInfor() {
        String sql = "SELECT name, address, phone_number, email, date_of_birth, gender, created_at " + "FROM pending"
                + " WHERE student_id = '" +
                NotificationController.getStudentId() + "'";
        ExecuteQuery query = new ExecuteQuery(sql);
        ResultSet resultSet = query.executeQuery();

        try {
            while (resultSet.next()) {
                lbNameNew.setText(resultSet.getString("name"));
                lbGenderNew.setText(resultSet.getString("gender"));
                lbEmailNew.setText(resultSet.getString("email"));
                lbPhoneNew.setText(resultSet.getString("phone_number"));
                lbPDobNew.setText(resultSet.getString("date_of_birth"));
                lbAddressNew.setText(resultSet.getString("address"));

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void onClickAccept(ActionEvent actionEvent) {
        String sql = "UPDATE students SET name = '" + lbNameNew.getText() + "', address = '" + lbAddressNew.getText()
                + "', phone_number = '" + lbPhoneNew.getText() + "', email = '" + lbEmailNew.getText()
                + "', date_of_birth = '"
                + lbPDobNew.getText().toString()
                + "', gender = '" + lbGenderNew.getText() + "' WHERE student_id = '"
                + NotificationController.getStudentId()
                + "'";

        ExecuteQuery query = new ExecuteQuery(sql);
        query.executeUpdate();

        AlertHelper.showAlert(AlertType.INFORMATION, "Success", null, "Cập nhật thành công");

        String sqlDetele = "DELETE FROM pending WHERE student_id = '" + NotificationController.getStudentId() + "'";
        ExecuteQuery queryDelete = new ExecuteQuery(sqlDetele);
        queryDelete.executeUpdate();

        NotificationController.list.removeIf(
                notification -> notification.getStudentid().equals(NotificationController.getStudentId()));

    }

    public void onClickCancel(ActionEvent actionEvent) {
        NotificationController.list.removeIf(
                notification -> notification.getStudentid().equals(NotificationController.getStudentId()));
        AlertHelper.showAlert(AlertType.INFORMATION, "Thông báo", null, "Bạn đã từ chối cập nhật");
    }
}
