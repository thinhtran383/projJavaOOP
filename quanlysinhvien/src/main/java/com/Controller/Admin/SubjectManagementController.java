package com.Controller.Admin;

import java.net.URL;
import java.util.ResourceBundle;

import com.Helper.AlertHelper;
import com.Helper.DataManager;
import com.Interfaces.ButtonAction;
import com.Models.Courses;
import com.utils.ExecuteQuery;
import com.utils.ExportToExcel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class SubjectManagementController extends AlertHelper implements Initializable, ButtonAction {
    @FXML
    private Button btnDelete;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnAdd;
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

    // private final ObservableList<Courses> coursesList =
    // FXCollections.observableArrayList();

    private ObservableList<Courses> coursesList = DataManager.getCoursesList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // initCourses();
        showOnTable(); // khoi tao bang du lieu
    }

    // private void initCourses() {
    // ExecuteQuery query = new ExecuteQuery("SELECT * FROM courses");
    // ResultSet resultSet = query.executeQuery();
    // try {
    // while (resultSet.next()) {
    // Courses course = new Courses(
    // resultSet.getString("course_id"),
    // resultSet.getString("course_name"),
    // resultSet.getInt("course_credit"));
    // coursesList.add(course);
    // }
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // }

    private void showOnTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<Courses, String>("courseId")); // ep du lieu vao table
                                                                                             // view
        nameColumn.setCellValueFactory(new PropertyValueFactory<Courses, String>("courseName"));
        creditsColumn.setCellValueFactory(new PropertyValueFactory<Courses, Integer>("credits"));
        tableCourses.setItems(coursesList);
    }

    public void setOnMouseClick(MouseEvent mouseEvent) {
        Courses course = tableCourses.getSelectionModel().getSelectedItem();
        txtid.setText(course.getCourseId());
        txtName.setText(course.getCourseName());
        txtCredits.setText(String.valueOf(course.getCredits()));

    }

    private void clear() {
        txtid.clear();
        txtName.clear();
        txtCredits.clear();
    }

    @Override
    public void onClickDelete(ActionEvent actionEvent) { // bug
        if (tableCourses.getSelectionModel().getSelectedItem() == null)
            return;
        if (!AlertHelper.showConfirmation("Bạn có muốn xóa khóa học này?\n"
                + "Lưu ý: Việc xóa khóa học sẽ xóa tất cả sinh viên \nđăng ký khóa học này"))
            return;
        String id = txtid.getText();
        ExecuteQuery query = new ExecuteQuery("DELETE FROM courses WHERE course_id ='" + id + "'");
        query.executeUpdate();
        coursesList.remove(tableCourses.getSelectionModel().getSelectedItem());
        clear();
    }

    @Override
    public void onClickAdd(ActionEvent actionEvent) { // chua hoan thien
        int credits;
        String id = txtid.getText();
        String name = txtName.getText();

        // kiem tra neu so tin chi khong la so thi se bao loi
        try {
            credits = Integer.parseInt(txtCredits.getText());
        } catch (NumberFormatException e) {
            AlertHelper.showAlert(AlertType.ERROR, "Lỗi", null, "Số tín chỉ không hợp lệ");
            return;
        }

        credits = txtCredits.getText().isEmpty() ? 0 : Integer.parseInt(txtCredits.getText());
        if (id.isEmpty() || name.isEmpty() || credits == 0) {
            AlertHelper.showAlert(AlertType.ERROR, "Lỗi", null, "Vui lòng nhập đầy đủ thông tin");
            return;
        } else if (credits < 0) {
            AlertHelper.showAlert(AlertType.ERROR, "Lỗi", null, "Số tín chỉ không hợp lệ");
            return;
        }

        // kiem tra co bi trung khong
        for (Courses course : coursesList) {
            if (course.getCourseId().equals(id)) {
                AlertHelper.showAlert(AlertType.ERROR, "Lỗi", null, "Mã khoá học đã tồn tại");
                return;
            }
        }
        ExecuteQuery query = new ExecuteQuery(
                "INSERT INTO courses VALUES ('" + id + "', '" + name + "', " + credits +
                        ")");
        query.executeUpdate();
        coursesList.add(new Courses(id, name, credits));
        clear();
    }

    public void onClickClear(ActionEvent actionEvent) {
        clear();
    }

    @Override
    public void onClickUpdate(ActionEvent actionEvent) {
        String id = txtid.getText();

        String oldId = tableCourses.getSelectionModel().getSelectedItem().getCourseId();
        System.out.println(oldId + "\n" + id);
        String name = txtName.getText();
        int credits = txtCredits.getText().isEmpty() ? 0 : Integer.parseInt(txtCredits.getText());
        if (id.isEmpty() || name.isEmpty() || credits == 0) {
            AlertHelper.showAlert(AlertType.ERROR, "Lỗi", null, "Vui lòng nhập đầy đủ thông tin");
            return;
        } else if (credits < 0) {
            AlertHelper.showAlert(AlertType.ERROR, "Lỗi", null, "Số tín chỉ không hợp lệ");
            return;
        }

        // kiem tra neu la chuoi thi bao loi
        try {
            credits = Integer.parseInt(txtCredits.getText());
        } catch (NumberFormatException e) {
            AlertHelper.showAlert(AlertType.ERROR, "Lỗi", null, "Số tín chỉ không hợp lệ");
            return;
        }

        ExecuteQuery query = new ExecuteQuery("UPDATE courses SET course_id = '" + id + "', course_name = '" + name
                + "', course_credit = " + credits + " WHERE course_id = '" + oldId + "'");
        query.executeUpdate();
        Courses course = tableCourses.getSelectionModel().getSelectedItem();
        course.setCourseName(name);
        course.setCredits(credits);
        course.setCourseId(id);
        tableCourses.refresh();
        AlertHelper.showAlert(AlertType.INFORMATION, "Thông báo", null, "Cập nhật dữ liệu thành công!");
        clear();
    }

    public void search(KeyEvent keyEvent) {
        String search = txtSearch.getText();
        System.out.println(search);
        if (search.isEmpty()) {
            tableCourses.setItems(coursesList);
            return;
        } else {
            ObservableList<Courses> searchList = FXCollections.observableArrayList();
            for (Courses course : coursesList) {
                if (course.getCourseId().toLowerCase().contains(search)
                        || course.getCourseName().toLowerCase().contains(search)) {
                    searchList.add(course);
                }
            }
            tableCourses.setItems(searchList);
        }
    }

    @Override
    public void onClickExport(ActionEvent event) {
        if (coursesList.isEmpty()) {
            AlertHelper.showAlert(AlertType.ERROR, "Lỗi", null, "Không có dữ liệu để xuất");
        } else {

            if (AlertHelper.showConfirmation("Bạn có muốn xuất dữ liệu ra excel không?")) {
                ExportToExcel.exportToExcel(tableCourses, "Courses.xlsx");
                AlertHelper.showAlert(AlertType.INFORMATION, "Thông báo", null, "Xuất dữ liệu thành công!");
            }
        }
    }

    @Override
    public void onClickRefresh(ActionEvent event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onClickRefresh'");
    }

}