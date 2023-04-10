package com.Models;

public class Grade {
    private String studentId;
    private String courseId;
    private float attendanceGrade;
    private float midTermGrade;
    private float finalGrade;

    public Grade(String studentId, String courseId, float attendanceGrade, float midTermGrade, float finalGrade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.attendanceGrade = attendanceGrade;
        this.midTermGrade = midTermGrade;
        this.finalGrade = finalGrade;
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

    public float getAttendanceGrade() {
        return attendanceGrade;
    }

    public void setAttendanceGrade(float attendanceGrade) {
        this.attendanceGrade = attendanceGrade;
    }

    public float getMidTermGrade() {
        return midTermGrade;
    }

    public void setMidTermGrade(float midTermGrade) {
        this.midTermGrade = midTermGrade;
    }

    @Override
    public String toString() {
        return "Grade [studentId=" + studentId + ", courseId=" + courseId + ", attendanceGrade=" + attendanceGrade
                + ", midTermGrade=" + midTermGrade + ", finalGrade=" + finalGrade + "]";
    }

    public float getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(float finalGrade) {
        this.finalGrade = finalGrade;
    }

}
