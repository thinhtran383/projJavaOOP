package com.Helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.Models.Courses;
import com.Models.Student;
import com.utils.ExecuteQuery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataManager {
    private static ObservableList<Courses> coursesList = FXCollections.observableArrayList();
    private static ObservableList<Student> studentsList = FXCollections.observableArrayList();

    private static DataManager instance = null;

    private DataManager() {
        initStudents();
        initCourses();
    }

    private void initStudents() {
        ExecuteQuery query = new ExecuteQuery("SELECT * FROM students");
        ResultSet resultSet = query.executeQuery();
        try {
            while (resultSet.next()) {
                LocalDate dob = LocalDate.parse(resultSet.getString("date_of_birth"));
                Student student = new Student(
                        resultSet.getString("student_id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("email"),
                        dob,
                        resultSet.getString("gender"));
                studentsList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initCourses() {
        ExecuteQuery query = new ExecuteQuery("SELECT * FROM courses");
        ResultSet resultSet = query.executeQuery();
        try {
            while (resultSet.next()) {
                Courses course = new Courses(
                        resultSet.getString("course_id"),
                        resultSet.getString("course_name"),
                        resultSet.getInt("course_credit"));
                coursesList.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Courses> getCoursesList() {
        return coursesList;
    }

    public static ObservableList<Student> getStudentsList() {
        if (instance == null)
            instance = new DataManager();
        return studentsList;
    }

    public static void setStudentsList(ObservableList<Student> students) {
        studentsList.setAll(students);
    }

    public static void setCoursesList(ObservableList<Courses> courses) {
        coursesList.setAll(courses);
    }

}
