package com.Controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.Models.Courses;
import com.Models.Student;
import com.utils.ExecuteQuery;
import com.Controller.StudentManagementController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EnrollmentManagementFrm {
    @FXML
    public TableView<Student> tableStudents;
    @FXML
    private TableView<Courses> tableCourses;
    @FXML
    private TableColumn<Student, String> studentIdColumn;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> dobColumn;
    @FXML
    private TableColumn<Courses, String> subjectIdColumn;
    @FXML
    private TableColumn<Courses, String> subjectNameColumn;
    @FXML
    private TableColumn<Courses, String> creditColumn;

    InitData initData = new InitData();
    private ObservableList<Student> studentsList = initData.studentsList;

    public void initialize() {
        // initStudents();
        // initCourses();
        showStudentOnTable();
    }

    private void showStudentOnTable() {
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentName"));
        dobColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentBirthday"));
        tableStudents.setItems(studentsList);
    }

    private void initCourses() {
    }

    // private void initStudents() {
    // ExecuteQuery query = new ExecuteQuery("SELECT student_id, name, date_of_birth
    // FROM students");
    // ResultSet resultSet = query.executeQuery();
    // try {
    // while (resultSet.next()) {
    // LocalDate dob = LocalDate.parse(resultSet.getString("date_of_birth"));
    // Student student = new Student(resultSet.getString("student_id"),
    // resultSet.getString("name"), dob);
    // studentsList.add(student);
    // }
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }

    // }

}
