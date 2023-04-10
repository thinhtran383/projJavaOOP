package com.Models;

public class Grade {
    private String subjectName;
    private String subjectId;
    private float attendanceGrade;
    private float midTermGrade;
    private float finalGrade;

    public Grade(String subjectName, String subjectId, float attendanceGrade, float midTermGrade, float finalGrade) {
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

}
