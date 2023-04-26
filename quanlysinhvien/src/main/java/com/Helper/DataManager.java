package com.Helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.Models.Account;
import com.Models.Courses;
import com.Models.Grade;
import com.Models.Student;
import com.utils.ExecuteQuery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataManager {
    private static ObservableList<Courses> coursesList = FXCollections.observableArrayList();
    private static ObservableList<Student> studentsList = FXCollections.observableArrayList();
    private static ObservableList<Grade> gradesList = FXCollections.observableArrayList();
    // private static ObservableList<Courses> coursesRegisterList =
    // FXCollections.observableArrayList();

    // private static ObservableList<Student> studentInfoList =
    // FXCollections.observableArrayList();

    private static ArrayList<Account> studentAccounts = new ArrayList<>();
    private static ArrayList<Account> adminAccounts = new ArrayList<>();

    private static DataManager instance = null;

    private DataManager() { // sington
        initCourses();
        intitAccount();
        initStudents();
        initGrade();
        // initCoursesRegister();
    }

    // private void initCoursesRegister() {
    // ExecuteQuery query = new ExecuteQuery(
    // "SELECT course_id, course_name, course_credit " +
    // "FROM courses " +
    // "WHERE course_id NOT IN (" +
    // "SELECT course_id FROM grades " +
    // "WHERE student_id = '" + InforStudentController.getStudentId() + "'" +
    // ")");
    // ResultSet resultSet = query.executeQuery();

    // try {
    // while (resultSet.next()) {
    // Courses courses = new Courses(
    // resultSet.getString("course_id"),
    // resultSet.getString("course_name"),
    // resultSet.getInt("course_credit"));
    // coursesRegisterList.add(courses);

    // }
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // }

    public static void initGrade() {

        ExecuteQuery query = new ExecuteQuery(
                "SELECT s.student_id, s.name, c.course_id, c.course_name, g.attendance_grade, g.midterm_grade, g.final_grade, (g.attendance_grade * 0.1 + g.midterm_grade * 0.4 + g.final_grade * 0.5) AS total_grade "
                        +
                        "FROM students s JOIN grades g ON s.student_id = g.student_id JOIN courses c ON g.course_id = c.course_id");
        ResultSet resultSet = query.executeQuery();
        try {
            while (resultSet.next()) {
                Grade grade = new Grade(
                        resultSet.getString("student_id"),
                        resultSet.getString("name"),
                        resultSet.getString("course_name"),
                        resultSet.getString("course_id"),
                        resultSet.getFloat("attendance_grade"),
                        resultSet.getFloat("midterm_grade"),
                        resultSet.getFloat("final_grade"),
                        resultSet.getFloat("total_grade"));
                gradesList.add(grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void intitAccount() {
        ExecuteQuery queryAdmin = new ExecuteQuery("SELECT * FROM adminaccount"); // lay du lieu account admin tu
                                                                                  // database

        ResultSet resultSet = queryAdmin.executeQuery(); // lay tai khoan admin tu db
        try {
            while (resultSet.next()) { // lay du lieu theo thuoc tinh db
                adminAccounts.add(new Account(resultSet.getString("username"),
                        resultSet.getString("password")));
            }
        } catch (SQLException e) {
            System.out.println("Không thể lấy dữ liệu từ database");
            e.printStackTrace();
        }

        ExecuteQuery queryStudent = new ExecuteQuery("SELECT * FROM studentaccount"); // lay du lieu account student tu
                                                                                      // db
        resultSet = queryStudent.executeQuery();
        try {
            while (resultSet.next()) {
                studentAccounts.add(new Account(
                        resultSet.getString("student_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password")));
            }
        } catch (SQLException e) {
            System.out.println("Không thể lấy dữ liệu từ database");
            e.printStackTrace();
        }
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
        if (instance == null)
            instance = new DataManager();
        return coursesList;
    }

    public static ObservableList<Student> getStudentsList() {
        if (instance == null)
            instance = new DataManager();
        return studentsList;
    }

    // public static ObservableList<Courses> getCoursesRegisterList() {
    // if (instance == null)
    // instance = new DataManager();
    // return coursesRegisterList;
    // }

    public static void setStudentsList(ObservableList<Student> students) {
        studentsList.setAll(students);
    }

    public static void setCoursesList(ObservableList<Courses> courses) {
        coursesList.setAll(courses);
    }

    public static ArrayList<Account> getStudentAccounts() {
        if (instance == null)
            instance = new DataManager();
        return studentAccounts;
    }

    public static ArrayList<Account> getAdminAccounts() {
        if (instance == null)
            instance = new DataManager();
        return adminAccounts;
    }

    public static ObservableList<Grade> getGradesList() {
        if (instance == null)
            instance = new DataManager();
        return gradesList;
    }

    // get Instance
    public static DataManager getInstance() {
        if (instance == null)
            instance = new DataManager();
        return instance;
    }

}
