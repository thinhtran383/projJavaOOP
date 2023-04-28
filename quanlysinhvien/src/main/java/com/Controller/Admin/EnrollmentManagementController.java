package com.Controller.Admin;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.Helper.AlertHelper;
import com.Helper.DataManager;
import com.Interfaces.ButtonAction;
import com.Models.Courses;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class EnrollmentManagementController extends AlertHelper implements Initializable, ButtonAction {
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

    private ObservableList<Student> studentsList = DataManager.getStudentsList();
    private ObservableList<Courses> enrollList = FXCollections.observableArrayList();
    private ObservableList<Grade> subjectRegistrationList = DataManager.getGradesList();

    private String studentId; // su dung trong ham onMouseClick va onClickDelete

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        if (AlertHelper.showConfirmation("Bạn có chắc chắn muốn huỷ học phần này của sinh viên?") == false) {
            return;
        }
        String sql = "DELETE FROM grades WHERE student_id='" + studentId + "' AND course_id='" + subjectId + "';";
        ExecuteQuery query = new ExecuteQuery(sql);
        query.executeUpdate();

        enrollList.remove(tableCourses.getSelectionModel().getSelectedItem());
    }

    @Override
    public void onClickExport(ActionEvent actionEvent) {
        // tao mot table view de chua du lieu
        TableView<Grade> table = new TableView<Grade>();
        // tao cac cot cho table view
        TableColumn<Grade, String> studentIdColumn = new TableColumn<Grade, String>("Student ID");
        TableColumn<Grade, String> studentNameColumn = new TableColumn<Grade, String>("Student Name");
        TableColumn<Grade, String> subjectIdColumn = new TableColumn<Grade, String>("Subject ID");
        TableColumn<Grade, String> subjectNameColumn = new TableColumn<Grade, String>("Subject Name");
        // gan du lieu cho cac cot
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        subjectIdColumn.setCellValueFactory(new PropertyValueFactory<>("subjectId"));
        subjectNameColumn.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        table.setItems(subjectRegistrationList);

        // them cac cot vao table view
        table.getColumns().addAll(studentIdColumn, studentNameColumn, subjectIdColumn, subjectNameColumn);
        ExportToExcel.exportToExcel(table, "EnrollList.xlsx");
        AlertHelper.showAlert(AlertType.INFORMATION, "Thông báo", null, "Xuất dữ liệu thành công!");
    }

    @Override
    public void onClickAdd(ActionEvent event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onClickAdd'");
    }

    @Override
    public void onClickUpdate(ActionEvent event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onClickUpdate'");
    }

    @Override
    public void onClickClear(ActionEvent event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onClickClear'");
    }

}
