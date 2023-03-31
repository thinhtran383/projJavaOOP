package com.Models;

public class Courses {
    private String courseId;
    private String courseName;

    public Courses(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public String toString() {
        return "Courses [courseId=" + courseId + ", courseName=" + courseName + "]";
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

}
