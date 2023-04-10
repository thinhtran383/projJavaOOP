package com.Models;

public class Grade {
    private String studentId;
    private String courseId;
    private String attendanceGrade;
    private String midTermGrade;
    private String finalGrade;

    public Grade(String studentId, String courseId, String attendanceGrade, String midTermGrade, String finalGrade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.attendanceGrade = attendanceGrade;
        this.midTermGrade = midTermGrade;
        this.finalGrade = finalGrade;
    }

    public Grade() {
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getAttendanceGrade() {
        return attendanceGrade;
    }

    public void setAttendanceGrade(String attendanceGrade) {
        this.attendanceGrade = attendanceGrade;
    }

    public String getMidTermGrade() {
        return midTermGrade;
    }

    public void setMidTermGrade(String midTermGrade) {
        this.midTermGrade = midTermGrade;
    }

    public String getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(String finalGrade) {
        this.finalGrade = finalGrade;
    }

}
