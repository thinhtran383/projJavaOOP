package com.Controller;


import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.Helper.DataManager;
import com.Models.Courses;
import com.Models.Student;
import com.utils.ExecuteQuery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
public class inforStudentController {
    @FXML 
    Text txtStudentId;
    @FXML 
    Text txtName;
    @FXML 
    Text txtGender;
    @FXML 
    Text txtEmail;
    @FXML 
    Text txtPhoneNumber;
    @FXML 
    Text txtDob;
    public void initialize() {
        showStudentInfor();
    }
    public void showStudentInfor(){

    }
    

}
    