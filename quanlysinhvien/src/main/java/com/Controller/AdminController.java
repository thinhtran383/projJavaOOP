package com.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.Models.Courses;
import com.utils.ExecuteQuery;
import com.utils.ExportToExcel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class AdminController {
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnExport;
    @FXML
    private TextField txtid;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtCredits;
    @FXML
    private TableView<Courses> tableCourses;
    @FXML
    private TableColumn<Courses, String> idColumn;
    @FXML
    private TableColumn<Courses, String> nameColumn;
    @FXML
    private TableColumn<Courses, Integer> creditsColumn;

    private ObservableList<Courses> coursesList = FXCollections.observableArrayList(); // tao rang buoc du lieu voi bang

    public void initialize() {
        initCourses();
        showOnTable();
    }

    private void initCourses() {
        ExecuteQuery query = new ExecuteQuery("SELECT * FROM courses");
        ResultSet resultSet = query.executeQuery();
        try {
            while (resultSet.next()) {
                Courses course = new Courses(
                        resultSet.getString("course_id"),
                        resultSet.getString("course_name"),
                        resultSet.getInt("course_credit"));
                coursesList.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showOnTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<Courses, String>("courseId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Courses, String>("courseName"));
        creditsColumn.setCellValueFactory(new PropertyValueFactory<Courses, Integer>("credits"));
        tableCourses.setItems(coursesList);
    }

    private static boolean showWarningDelete() {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Bạn có muốn xóa khóa học này?\n"
                + "Lưu ý: Việc xóa khóa học sẽ xóa tất cả sinh viên \nđăng ký khóa học này");
        alert.setTitle("Xác nhận");
        alert.setHeaderText(null);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    public void onPressExport(ActionEvent actionEvent) {

        ExportToExcel.exportToExcel(tableCourses, "Courses.xlsx");
    }

    public void setOnMouseClick(MouseEvent mouseEvent) {
        Courses course = tableCourses.getSelectionModel().getSelectedItem();
        txtid.setText(course.getCourseId());
        txtName.setText(course.getCourseName());
        txtCredits.setText(String.valueOf(course.getCredits()));

        if (txtid.getText().isEmpty() || txtName.getText().isEmpty() || txtCredits.getText().isEmpty()) {
            btnDelete.setDisable(true);
        } else {
            btnDelete.setDisable(false);
        }
    }

    public void onClickLogout(ActionEvent actionEvent) {
        LogoutController.onClickLogout(actionEvent);
    }

    private void clear() {
        txtid.clear();
        txtName.clear();
        txtCredits.clear();
    }

    public void onClickDelete(ActionEvent actionEvent) {

        if (showWarningDelete() == false)
            return;
        String id = txtid.getText();
        ExecuteQuery query = new ExecuteQuery("DELETE FROM courses WHERE course_id = '" + id + "'");
        query.executeUpdate();
        coursesList.remove(tableCourses.getSelectionModel().getSelectedItem());
        clear();
    }

    public void onClickAdd(ActionEvent actionEvent) { // chua hoan thien

        String id = txtid.getText();
        String name = txtName.getText();
        int credits;
        if (txtCredits.getText().isEmpty()) {
            credits = 0;
        } else {
            credits = Integer.parseInt(txtCredits.getText());
        }
        if (id.isEmpty() || name.isEmpty() || credits == 0) {
            Alert alert = new Alert(AlertType.ERROR, "Vui lòng nhập đầy đủ thông tin");
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        } else if (credits < 0) {
            Alert alert = new Alert(AlertType.ERROR, "Số tín chỉ không hợp lệ");
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        } // kiem tra co bi trung khong
        for (Courses course : coursesList) {
            if (course.getCourseId().equals(id)) {
                Alert alert = new Alert(AlertType.ERROR, "Mã khóa học đã tồn tại");
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.showAndWait();
                return;
            }
        }
        ExecuteQuery query = new ExecuteQuery(
                "INSERT INTO courses VALUES ('" + id + "', '" + name + "', " + credits + ")");
        query.executeUpdate();
        coursesList.add(new Courses(id, name, credits));
        clear();
    }

    public void onClickClear(ActionEvent actionEvent) {
        clear();
    }

}
