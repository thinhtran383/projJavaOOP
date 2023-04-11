package com.Controller;

import com.Helper.DataManager;
import com.Models.Grade;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
}
