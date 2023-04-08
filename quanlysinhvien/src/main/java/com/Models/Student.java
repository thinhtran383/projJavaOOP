package com.Models;

import java.time.LocalDate;

public class Student {
    private String studentId;
    private String studentName;
    private String studentGender;
    private String studentAddress;
    private String studentEmail;
    private String studentPhone;
    private LocalDate studentBirthday;

    public Student(String studentId, String studentName, String studentAddress, String studentPhone,
            String studentEmail, LocalDate studentBirthday, String studentGender) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAddress = studentAddress;
        this.studentPhone = studentPhone;
        this.studentEmail = studentEmail;
        this.studentBirthday = studentBirthday;
        this.studentGender = studentGender;
    }

    public Student(String studentId, String studentName, LocalDate studentBirthday) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentBirthday = studentBirthday;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public LocalDate getStudentBirthday() {
        return studentBirthday;
    }

    public void setStudentBirthday(LocalDate studentBirthday) {
        this.studentBirthday = studentBirthday;
    }

    @Override
    public String toString() {
        return "Student [studentId=" + studentId + ", studentName=" + studentName + ", studentGender=" + studentGender
                + ", studentAddress=" + studentAddress + ", studentEmail=" + studentEmail + ", studentPhone="
                + studentPhone + ", studentBirthday=" + studentBirthday + "]";
    }

}
