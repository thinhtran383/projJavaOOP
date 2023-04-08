package com.Controller;

import com.Models.Courses;
import com.Models.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataManager {
    private static ObservableList<Courses> coursesList = FXCollections.observableArrayList();
    private static ObservableList<Student> studentsList = FXCollections.observableArrayList();

    public static ObservableList<Courses> getCoursesList() {
        return coursesList;
    }

    public static ObservableList<Student> getStudentsList() {
        return studentsList;
    }

    public static void setStudentsList(ObservableList<Student> students) {
        studentsList.setAll(students);
    }

    public static void setCoursesList(ObservableList<Courses> courses) {
        coursesList.setAll(courses);
    }

}
