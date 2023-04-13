package com.Models;

public class Grade {
    private String subjectName;
    private String subjectId;
    private float attendanceGrade;
    private float midTermGrade;
    private float finalGrade;
    private float totalGrade;

    private String studentId;

    private String studentName;

    public Grade(String studentId, String studentName, String subjectName, String subjectId, float attendanceGrade,
            float midTermGrade, float finalGrade, float totalGrade) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.subjectName = subjectName;
        this.subjectId = subjectId;
        this.attendanceGrade = attendanceGrade;
        this.midTermGrade = midTermGrade;
        this.finalGrade = finalGrade;
        this.totalGrade = totalGrade;
    }

    public Grade(String subjectName, String subjectId, float attendanceGrade, float midTermGrade, float finalGrade) {
        // su dung trong inforStudent
        this.subjectName = subjectName;
        this.subjectId = subjectId;
        this.attendanceGrade = attendanceGrade;
        this.midTermGrade = midTermGrade;
        this.finalGrade = finalGrade;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
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

    public float getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(float finalGrade) {
        this.finalGrade = finalGrade;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public float getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(float totalGrade) {
        this.totalGrade = totalGrade;
    }
}
