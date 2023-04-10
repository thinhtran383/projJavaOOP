package com.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.Models.Grade;
import com.utils.ExecuteQuery;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class InforStudentController {
    @FXML
    public Label lbStudentId;
    @FXML
    public Label lbStudentName;
    @FXML
    public Label lbStudentGender;
    @FXML
    public Label lbStudentEmail;
    @FXML
    public Label lbPhoneNumber;
    @FXML
    public Label lbDob;
    @FXML
    public Label lbStudentAddress;
    @FXML
    public TableView<Grade> tableGrades;
    @FXML
    public TableColumn<Grade, String> subjectIdColumn;
    @FXML
    public TableColumn<Grade, String> subjectNameColumn;
    @FXML
    public TableColumn<Grade, Float> attendanceColumn;
    @FXML
    public TableColumn<Grade, Float> midtermColumn;
    @FXML
    public TableColumn<Grade, Float> finalColumn;

    public void initialize() {
        ExecuteQuery query = new ExecuteQuery("SELECT * FROM students WHERE student_id = '" + getStudentId() + "' ");
        ResultSet resultSet = query.executeQuery();

        try {
            if (resultSet.next()) {
                LocalDate dob = LocalDate.parse(resultSet.getString("date_of_birth"));

                lbStudentId.setText(resultSet.getString("student_id"));
                lbStudentName.setText(resultSet.getString("name"));
                lbPhoneNumber.setText(resultSet.getString("phone_number"));
                lbStudentEmail.setText(resultSet.getString("email"));
                lbStudentAddress.setText(resultSet.getString("address"));
                lbStudentGender.setText(resultSet.getString("gender"));
                lbDob.setText(dob.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private String getStudentId() {

        String studentId = null;
        ExecuteQuery query = new ExecuteQuery(
                "SELECT student_id FROM studentaccount WHERE username = '" + LoginController.email + "'");
        ResultSet resultSet = query.executeQuery();

        try {
            if (resultSet.next()) {
                studentId = resultSet.getString("student_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentId.trim();
    }

    private void initGrade() {
        ExecuteQuery query = new ExecuteQuery("SELECT * FROM grades WHERE student_id = '" + getStudentId() + "'");
        ResultSet resultSet = query.executeQuery();

    }

}
