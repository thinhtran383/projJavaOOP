package com.Models;

public class Account {
    private String username;
    private String password;

    private String studentId;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account(String Id, String username, String password) {
        this.studentId = Id;
        this.username = username;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account [username=" + username + ", password=" + password + "]";
    }

}
