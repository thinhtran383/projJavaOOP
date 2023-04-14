package com.Controller.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.Helper.DataManager;
import com.Models.Courses;
import com.utils.ExecuteQuery;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RegisterManagementController {
    @FXML
    private TableView<Courses> tableCourses;
    @FXML
    private TableColumn<Courses, String> subjectColumn;
    @FXML
    private TableColumn<Courses, String> subjectNameColumn;
    @FXML
    private TableColumn<Courses, String> creditsColumn;

    // private ObservableList<Courses> coursesList =
    // DataManager.getCoursesRegisterList();
    private ObservableList<Courses> coursesList = FXCollections.observableArrayList();

    public void initialize() {
        initCourses();
        showOnTable();

    }

    private void initCourses() {
        ExecuteQuery query = new ExecuteQuery(
                "SELECT course_id, course_name, course_credit " +
                        "FROM courses " +
                        "WHERE course_id NOT IN (" +
                        "SELECT course_id FROM grades " +
                        "WHERE student_id = '" + InforStudentController.getStudentId() + "'" +
                        ")");
        ResultSet resultSet = query.executeQuery();

        try {
            while (resultSet.next()) {
                Courses courses = new Courses(
                        resultSet.getString("course_id"),
                        resultSet.getString("course_name"),
                        resultSet.getInt("course_credit"));
                coursesList.add(courses);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showOnTable() {
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        subjectNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        creditsColumn.setCellValueFactory(new PropertyValueFactory<>("credits"));

        tableCourses.setItems(coursesList);
    }

    public void onClckRegister(ActionEvent actionEvent) {
        String subjectId = tableCourses.getSelectionModel().getSelectedItem().getCourseId();

        ExecuteQuery query = new ExecuteQuery(
                "INSERT INTO grades (student_id, course_id) " +
                        "VALUES ('" + InforStudentController.getStudentId() + "', '" + subjectId + "')");
        query.executeUpdate();

        coursesList.remove(tableCourses.getSelectionModel().getSelectedItem());

    }
}
