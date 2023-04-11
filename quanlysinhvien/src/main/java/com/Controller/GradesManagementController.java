package com.Controller;

import com.Helper.AlertHelper;
import com.Helper.DataManager;
import com.Models.Grade;
import com.utils.ExecuteQuery;
import com.utils.ExportToExcel;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class GradesManagementController {
    @FXML
    TableView<Grade> tableGrades;
    @FXML
    private TableColumn<Grade, String> studentIdColumn;
    @FXML
    private TableColumn<Grade, String> studentNameColumn;
    @FXML
    private TableColumn<Grade, String> subjectNameColumn;
    @FXML
    private TableColumn<Grade, String> subjectIdColumn;
    @FXML
    private TableColumn<Grade, Float> attendanceColumn;
    @FXML
    private TableColumn<Grade, Float> midtermColumn;
    @FXML
    private TableColumn<Grade, Float> finalColumn;
    @FXML
    private TextField txtStudentId;
    @FXML
    private TextField txtSubjectId;
    @FXML
    private TextField txtStudentName;
    @FXML
    private TextField txtSubjectName;
    @FXML
    private TextField txtAttendance;
    @FXML
    private TextField txtMidterm;
    @FXML
    private TextField txtFinal;
    @FXML
    private TextField txtSearch;

    private ObservableList<Grade> gradesList = DataManager.getGradesList();

    public void initialize() {
        showOnTable();
    }

    private void clear() {
        txtStudentId.setText("");
        txtStudentName.setText("");
        txtSubjectId.setText("");
        txtSubjectName.setText("");
        txtAttendance.setText("");
        txtMidterm.setText("");
        txtFinal.setText("");
    }

    private void showOnTable() {
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        subjectIdColumn.setCellValueFactory(new PropertyValueFactory<>("subjectId"));
        subjectNameColumn.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        attendanceColumn.setCellValueFactory(new PropertyValueFactory<>("attendanceGrade"));
        midtermColumn.setCellValueFactory(new PropertyValueFactory<>("midTermGrade"));
        finalColumn.setCellValueFactory(new PropertyValueFactory<>("finalGrade"));
        tableGrades.setItems(gradesList);
    }

    public void onMouseClick(MouseEvent mouseEvent) {
        Grade grade = tableGrades.getSelectionModel().getSelectedItem();
        txtStudentId.setText(grade.getStudentId());
        txtStudentName.setText(grade.getStudentName());
        txtSubjectId.setText(grade.getSubjectId());
        txtSubjectName.setText(grade.getSubjectName());
        txtAttendance.setText(String.valueOf(grade.getAttendanceGrade()));
        txtMidterm.setText(String.valueOf(grade.getMidTermGrade()));
        txtFinal.setText(String.valueOf(grade.getFinalGrade()));
    }

    public void onClickUpdate(ActionEvent actionEvent) {
        String studentId = txtStudentId.getText();
        String subjectId = txtSubjectId.getText();
        float attendanceGrade = Float.parseFloat(txtAttendance.getText());
        float midTermGrade = Float.parseFloat(txtMidterm.getText());
        float finalGrade = Float.parseFloat(txtFinal.getText());

        ExecuteQuery query = new ExecuteQuery("UPDATE grades SET attendance_grade ='" + attendanceGrade
                + "', midterm_grade ='" + midTermGrade + "', final_grade ='" + finalGrade + "' WHERE student_id ='"
                + studentId + "' AND course_id ='" + subjectId + "'");
        query.executeUpdate();

        Grade grade = tableGrades.getSelectionModel().getSelectedItem();

        grade.setAttendanceGrade(attendanceGrade);
        grade.setMidTermGrade(midTermGrade);
        grade.setFinalGrade(finalGrade);

        tableGrades.refresh();

        AlertHelper.showAlert(AlertType.INFORMATION, "Thông báo", null, "Cập nhật thành công!");
        clear();
    }

    public void onClickExport(ActionEvent actionEvent) {

        if (AlertHelper.showConfirmation("Bạn có muốn xuất dữ liệu ra excel không?")) {
            ExportToExcel.exportToExcel(tableGrades, "students.xlsx");
            AlertHelper.showAlert(AlertType.INFORMATION, "Thông báo", null, "Xuất dữ liệu thành công!");
        }
    }
}
