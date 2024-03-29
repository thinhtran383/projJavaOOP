package com.Controller.Student;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.App;
import com.Helper.AlertHelper;
import com.Interfaces.ButtonAction;
import com.Models.Courses;
import com.utils.ExecuteQuery;
import com.utils.ExportToExcel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class DanhSachMonDaDangKyController extends AlertHelper implements Initializable, ButtonAction {
    @FXML
    private TableView<Courses> tableMonDaDangKy;
    @FXML
    private TableColumn<Courses, String> subjectIdColumn;
    @FXML
    private TableColumn<Courses, String> subjectNameColumn;
    @FXML
    private TableColumn<Courses, Integer> creditsColumn;
    @FXML
    private Button btExport;
    @FXML
    private ObservableList<Courses> coursesList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCourses();
        showOnTable();
    }

    private void initCourses() {
        ExecuteQuery query = new ExecuteQuery(
                "SELECT course_id, course_name, course_credit " +
                        "FROM courses " +
                        "WHERE course_id IN (" +
                        "SELECT course_id FROM grades " +
                        "WHERE student_id = '" + InforStudentController.getStudentId() + "'" +
                        ")");
        ResultSet resultSet = query.executeQuery();

        try {
            while (resultSet.next()) {
                Courses courses = new Courses(
                        resultSet.getString("course_id"),
                        resultSet.getString("course_name"),
                        resultSet.getInt("course_credit"));
                coursesList.add(courses);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showOnTable() {
        subjectIdColumn.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        subjectNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        creditsColumn.setCellValueFactory(new PropertyValueFactory<>("credits"));

        tableMonDaDangKy.setItems(coursesList);
    }

    @Override
    public void onClickExport(ActionEvent actionEvent) {
        if (coursesList.isEmpty()) {
            AlertHelper.showAlert(AlertType.ERROR, "Lỗi", null, "Không có dữ liệu để xuất");
            return;
        } else if (AlertHelper.showConfirmation("Bạn có muốn xuất dữ liệu ra excel không?")) {
            ExportToExcel.exportToExcel(tableMonDaDangKy, "danh_sach_da_dang_ki.xlsx");
            AlertHelper.showAlert(AlertType.INFORMATION, "Thông báo", null, "Xuất dữ liệu thành công!");
        }
    }

    @Override
    public void onClickAdd(ActionEvent event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onClickAdd'");
    }

    @Override
    public void onClickDelete(ActionEvent event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onClickDelete'");
    }

    @Override
    public void onClickUpdate(ActionEvent event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onClickUpdate'");
    }

    @Override
    public void onClickClear(ActionEvent event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onClickClear'");
    }

    @Override
    public void onClickRefresh(ActionEvent event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onClickRefresh'");
    }

}
