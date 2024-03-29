package com.Controller.Student;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.App;
import com.Controller.LoginController;
import com.Helper.AlertHelper;
import com.Helper.DataManager;
import com.Interfaces.ButtonAction;
import com.Models.Grade;
import com.Models.Student;
import com.utils.ExecuteQuery;
import com.utils.ExportToExcel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class InforStudentController extends AlertHelper implements Initializable, ButtonAction {
    @FXML
    private Label lbStudentId;
    @FXML
    private Label lbStudentName;
    @FXML
    private Label lbStudentGender;
    @FXML
    private Label lbStudentEmail;
    @FXML
    private Label lbPhoneNumber;
    @FXML
    private Label lbDob;
    @FXML
    private Label lbStudentAddress;
    @FXML
    private TableView<Grade> tableGrades;
    @FXML
    private TableColumn<Grade, String> subjectIdColumn;
    @FXML
    private TableColumn<Grade, String> subjectNameColumn;
    @FXML
    private TableColumn<Grade, Float> attendanceColumn;
    @FXML
    private TableColumn<Grade, Float> midtermColumn;
    @FXML
    private TableColumn<Grade, Float> finalColumn;
    @FXML
    private TableColumn<Grade, Float> totalColumn;

    private ObservableList<Grade> gradeList = FXCollections.observableArrayList();

    private ObservableList<Student> studentList = DataManager.getStudentsList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initGrade();
        showOnTable();
        initInfo_2();
    }

    private void initInfo_2() {
        for (Student student : studentList) {
            if (student.getStudentId().equals(getStudentId())) {
                lbStudentId.setText(student.getStudentId());
                lbStudentName.setText(student.getStudentName());
                lbPhoneNumber.setText(student.getStudentPhone());
                lbStudentEmail.setText(student.getStudentEmail());
                lbStudentAddress.setText(student.getStudentAddress());
                lbStudentGender.setText(student.getStudentGender());
                lbDob.setText(student.getStudentBirthday().toString());
            }
        }
    }

    private void initInfo() {
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

    public static String getStudentId() {

        String studentId = null;
        ExecuteQuery query = new ExecuteQuery(
                "SELECT student_id FROM studentaccount WHERE username = '" + LoginController.username + "'");
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

        String sql = "SELECT courses.course_id, courses.course_name, grades.attendance_grade, grades.midterm_grade, grades.final_grade, ROUND(( grades.attendance_grade * 0.1)+( grades.midterm_grade * 0.4)+(grades.final_grade * 0.5),2) as total_grade FROM students JOIN grades ON students.student_id = grades.student_id JOIN courses ON grades.course_id = courses.course_id WHERE students.student_id = '"
                + getStudentId() + "'";

        ExecuteQuery query = new ExecuteQuery(sql);
        ResultSet resultSet = query.executeQuery();

        try {
            while (resultSet.next()) {
                Grade grade = new Grade(
                        resultSet.getString("course_id"),
                        resultSet.getString("course_name"),
                        resultSet.getFloat("attendance_grade"),
                        resultSet.getFloat("midterm_grade"),
                        resultSet.getFloat("final_grade"),
                        resultSet.getFloat("total_grade"));
                gradeList.add(grade);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showOnTable() {
        subjectIdColumn.setCellValueFactory(new PropertyValueFactory<Grade, String>("subjectId"));
        subjectNameColumn.setCellValueFactory(new PropertyValueFactory<Grade, String>("subjectName"));
        attendanceColumn.setCellValueFactory(new PropertyValueFactory<Grade, Float>("attendanceGrade"));
        midtermColumn.setCellValueFactory(new PropertyValueFactory<Grade, Float>("midTermGrade"));
        finalColumn.setCellValueFactory(new PropertyValueFactory<Grade, Float>("finalGrade"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<Grade, Float>("totalGrade"));
        tableGrades.setItems(gradeList);
    }

    @Override
    public void onClickExport(ActionEvent actionEvent) {
        if (AlertHelper.showConfirmation("Bạn có muốn xuất dữ liệu ra excel không?")) {
            ExportToExcel.exportToExcel(tableGrades, "students.xlsx");
            AlertHelper.showAlert(AlertType.INFORMATION, "Thông báo", null, "Xuất dữ liệu thành công!");
        }
    }

    @Override
    public void onClickUpdate(ActionEvent actionEvent) {
        // App.setRootPop("DanhSachMonDaDangKyFrm", "Danh sách môn đã đăng ký", false);
        try {
            App.setRootPop("InputInfoFrm", "Info", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClickAdd(ActionEvent event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onClickAdd'");
    }

    @Override
    public void onClickDelete(ActionEvent event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onClickDelete'");
    }

    @Override
    public void onClickClear(ActionEvent event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onClickClear'");
    }

    @Override
    public void onClickRefresh(ActionEvent event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onClickRefresh'");
    }
}
