package com.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.Helper.DataManager;
import com.Models.Courses;
import com.Models.Student;
import com.utils.ExecuteQuery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class EnrollmentManagementController {
    @FXML
    private TableView<Student> tableStudents;
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

    // private ObservableList<Student> studentsList =
    // FXCollections.observableArrayList();
    private ObservableList<Student> studentsList = DataManager.getStudentsList();

    private ObservableList<Courses> enrollList = FXCollections.observableArrayList();

    private String studentId;

    public void initialize() {
        showStudentOnTable();
        showCoursesOnTable();
    }

    private void showStudentOnTable() {
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentName"));
        dobColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentBirthday"));

        tableStudents.setItems(studentsList);

    }

    private void showCoursesOnTable() {
        subjectIdColumn.setCellValueFactory(new PropertyValueFactory<Courses, String>("courseId"));
        subjectNameColumn.setCellValueFactory(new PropertyValueFactory<Courses, String>("courseName"));
        creditColumn.setCellValueFactory(new PropertyValueFactory<Courses, String>("credits"));
        tableCourses.setItems(enrollList);
    }

    public void onClickRefresh(ActionEvent event) {
        tableStudents.refresh();
        tableCourses.refresh();
    }

    public void onMouseClick(MouseEvent event) {
        studentId = tableStudents.getSelectionModel().getSelectedItem().getStudentId();
        System.out.println(studentId);
        String sql = "SELECT s.student_id,s.name,c.course_id,c.course_name,c.course_credit,g.attendance_grade,g.midterm_grade, g.final_grade "
                + "FROM grades g JOIN students s ON g.student_id= s.student_id JOIN courses c ON g.course_id= c.course_id "
                + "WHERE s.student_id='" + studentId + "';";

        ExecuteQuery query = new ExecuteQuery(sql);
        ResultSet rs = query.executeQuery();
        enrollList.clear();
        try {
            while (rs.next()) {
                String courseId = rs.getString("course_id");
                String courseName = rs.getString("course_name");
                int courseCredit = rs.getInt("course_credit");
                Courses course = new Courses(courseId, courseName, courseCredit);
                enrollList.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public void onClickDelete(ActionEvent actionEvent) {
        String subjectId = tableCourses.getSelectionModel().getSelectedItem().getCourseId();
        String sql = "DELETE FROM grades WHERE student_id='" + studentId + "' AND course_id='" + subjectId + "';";
        ExecuteQuery query = new ExecuteQuery(sql);
        query.executeUpdate();

        enrollList.remove(tableCourses.getSelectionModel().getSelectedItem());
    }

}
