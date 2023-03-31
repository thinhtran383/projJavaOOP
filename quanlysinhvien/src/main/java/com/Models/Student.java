package com.Models;

public class Student {
    private int studentId;
    private String studentName;
    private String studentGender;
    private String studentAddress;
    private String studentEmail;
    private String studentPhone;
    private String studentBirthday;

    public Student(int studentId, String studentName, String studentGender, String studentAddress, String studentEmail,
            String studentPhone, String studentBirthday) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentGender = studentGender;
        this.studentAddress = studentAddress;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.studentBirthday = studentBirthday;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
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

    public String getStudentBirthday() {
        return studentBirthday;
    }

    public void setStudentBirthday(String studentBirthday) {
        this.studentBirthday = studentBirthday;
    }

    @Override
    public String toString() {
        return "Student [studentId=" + studentId + ", studentName=" + studentName + ", studentGender=" + studentGender
                + ", studentAddress=" + studentAddress + ", studentEmail=" + studentEmail + ", studentPhone="
                + studentPhone + ", studentBirthday=" + studentBirthday + "]";
    }

}
