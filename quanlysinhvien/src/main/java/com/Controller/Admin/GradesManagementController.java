package com.Controller.Admin;

import java.net.URL;
import java.util.ResourceBundle;

import com.Helper.AlertHelper;
import com.Helper.DataManager;
import com.Interfaces.ButtonAction;
import com.Models.Grade;
import com.utils.ExecuteQuery;
import com.utils.ExportToExcel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class GradesManagementController extends AlertHelper implements ButtonAction, Initializable {

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
    private TableColumn<Grade, Float> totalColumn;
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
    @FXML
    private ComboBox<String> cbFilter;

    private ObservableList<Grade> gradesList = DataManager.getGradesList();

    private String selectedFilter = "<None>";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showOnTable();
        setCbFillter();
    }

    private void setCbFillter() {
        cbFilter.getItems().addAll("Trượt", "Đỗ", "<None>");
        cbFilter.getSelectionModel().selectLast();
        cbFilter.setOnAction(e -> {
            selectedFilter = cbFilter.getSelectionModel().getSelectedItem().toString();
            filter();
            System.out.println(selectedFilter);
        });
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
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("totalGrade"));
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

        if (attendanceGrade < 0 || attendanceGrade > 10 || midTermGrade < 0 || midTermGrade > 10 || finalGrade < 0
                || finalGrade > 10) {
            AlertHelper.showAlert(AlertType.ERROR, "Thông báo", null, "Điểm không hợp lệ!");
            return;
        }

        float totalGrade = (float) Math.round((attendanceGrade * 0.1f + midTermGrade * 0.4f + finalGrade * 0.5f) * 100)
                / 100;

        ExecuteQuery query = new ExecuteQuery("UPDATE grades SET attendance_grade ='" + attendanceGrade
                + "', midterm_grade ='" + midTermGrade + "', final_grade ='" + finalGrade + "' WHERE student_id ='"
                + studentId + "' AND course_id ='" + subjectId + "'");
        query.executeUpdate();

        Grade grade = tableGrades.getSelectionModel().getSelectedItem();

        grade.setAttendanceGrade(attendanceGrade);
        grade.setMidTermGrade(midTermGrade);
        grade.setFinalGrade(finalGrade);
        grade.setTotalGrade(totalGrade);

        tableGrades.refresh();

        AlertHelper.showAlert(AlertType.INFORMATION, "Thông báo", null, "Cập nhật thành công!");
        clear();
    }

    @Override
    public void onClickExport(ActionEvent actionEvent) {
        if (gradesList.isEmpty()) {
            AlertHelper.showAlert(AlertType.ERROR, "Thông báo", null, "Không có dữ liệu để xuất!");
            return;
        }
        if (AlertHelper.showConfirmation("Bạn có muốn xuất dữ liệu ra excel không?")) {
            ExportToExcel.exportToExcel(tableGrades, "students.xlsx");
            AlertHelper.showAlert(AlertType.INFORMATION, "Thông báo", null, "Xuất dữ liệu thành công!");
        }
    }

    public void onClickRefresh(ActionEvent actionEvent) { // van de: khi gradesList rong thi khong the
        gradesList.clear();
        if (gradesList.isEmpty()) {
            DataManager.initGrade();
        }
        gradesList = DataManager.getGradesList();
        showOnTable();
    }

    public void search(KeyEvent keyEvent) {
        String search = txtSearch.getText().toLowerCase();
        if (search.isEmpty()) {
            tableGrades.setItems(gradesList);
        } else {
            ObservableList<Grade> searchList = FXCollections.observableArrayList();
            for (Grade grade : gradesList) {
                if (grade.getStudentId().toLowerCase().contains(search)
                        || grade.getStudentName().toLowerCase().contains(search)
                        || grade.getSubjectId().toLowerCase().contains(search)
                        || grade.getSubjectName().toLowerCase().contains(search)) {
                    searchList.add(grade);
                }
            }
            tableGrades.setItems(searchList);
        }
    }

    public void filter() {
        if (selectedFilter.equals("<None>")) {
            tableGrades.setItems(gradesList);
        } else {
            ObservableList<Grade> filterList = FXCollections.observableArrayList();
            for (Grade grade : gradesList) {
                if (grade.getTotalGrade() < 5 && selectedFilter.equals("Trượt")) {
                    filterList.add(grade);
                } else if (grade.getTotalGrade() >= 5 && selectedFilter.equals("Đỗ")) {
                    filterList.add(grade);
                }
            }
            tableGrades.setItems(filterList);
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

}
