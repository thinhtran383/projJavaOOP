package com.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.Helper.AlertHelper;
import com.Models.Student;
import com.utils.ExecuteQuery;
import com.utils.ExportToExcel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class StudentManagementController {
    @FXML
    private TableColumn<Student, String> idColumn;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> genderColumn;
    @FXML
    private TableColumn<Student, String> dobColumn;
    @FXML
    private TableColumn<Student, String> emailColumn;
    @FXML
    private TableColumn<Student, String> addressColumn;
    @FXML
    private TableColumn<Student, String> phoneColumn;
    @FXML
    private TableView<Student> tableStudents;
    @FXML
    private TextField txtStudentId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtPhone;
    @FXML
    private ComboBox<String> cbGender;
    @FXML
    private DatePicker cbDob;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnExport;

    public ObservableList<Student> studentsList = FXCollections.observableArrayList();

    public void initialize() {
        initStudents();
        showOnTable();
        setCbGender();
    }

    private void setCbGender() {
        cbGender.getItems().addAll("Male", "Female");
    }

    private void showOnTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentAddress"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentPhone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentEmail"));
        dobColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentBirthday"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentGender"));
        tableStudents.setItems(studentsList);
    }

    private void initStudents() {
        ExecuteQuery query = new ExecuteQuery("SELECT * FROM students");
        ResultSet resultSet = query.executeQuery();
        try {
            while (resultSet.next()) {
                LocalDate dob = LocalDate.parse(resultSet.getString("date_of_birth"));
                Student student = new Student(
                        resultSet.getString("student_id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("email"),
                        dob,
                        resultSet.getString("gender"));
                studentsList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clear() {
        txtAddress.clear();
        txtEmail.clear();
        txtName.clear();
        txtPhone.clear();
        txtStudentId.clear();
        cbDob.setValue(null);
        cbGender.setValue(null);
    }

    public void onClickAdd(ActionEvent actionEvent) {
        String id = txtStudentId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        LocalDate dob = cbDob.getValue();
        String gender = cbGender.getValue();
        if (id.isEmpty() || name.isEmpty() || address.isEmpty()
                || email.isEmpty() || phone.isEmpty() || dob == null
                || gender == null) {
            AlertHelper.showAlert(AlertType.ERROR, "Lỗi", null, "Vui lòng nhập đầy đủ thông tin");
            return;
        }
        for (Student student : studentsList) {
            if (student.getStudentId().equals(id)) {
                AlertHelper.showAlert(AlertType.ERROR, "Lỗi", null, "Mã sinh viên đã tồn tại");
                return;
            }
        }

        ExecuteQuery query = new ExecuteQuery(
                "INSERT INTO students VALUES('" + id + "', '" + name + "', '" + address + "', '" + email + "', '"
                        + phone
                        + "', '" + dob + "', '" + gender + "')");

        query.executeUpdate();
        studentsList.add(new Student(id, name, address, phone,
                email, dob, gender));

        clear();

    }

    public void onClickDelete(ActionEvent actionEvent) {
        if (tableStudents.getSelectionModel().getSelectedItems() == null) {
            return;
        }

        if (AlertHelper.showConfirmation("Bạn có muốn xoá sinh viên này không?") == true) {
            String id = txtStudentId.getText();
            ExecuteQuery query = new ExecuteQuery("DELETE FROM students WHERE student_id = '" + id + "'");
            query.executeUpdate();
            studentsList.remove(tableStudents.getSelectionModel().getSelectedIndex());
            clear();
        }
    }

    public void onClickClear(ActionEvent actionEvent) {
        clear();
    }

    public void onClickUpdate(ActionEvent actionEvent) {
        String id = txtStudentId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        LocalDate dob = cbDob.getValue();
        String gender = cbGender.getValue();

        ExecuteQuery query = new ExecuteQuery("UPDATE students SET name = '" + name + "', address = '" + address
                + "', phone_number = '" + phone + "', email = '" + email + "', date_of_birth = '" + dob
                + "', gender = '" + gender + "' WHERE student_id = '" + id + "'");
        query.executeUpdate();
        Student student = tableStudents.getSelectionModel().getSelectedItem();
        student.setStudentName(name);
        student.setStudentAddress(address);
        student.setStudentEmail(email);
        student.setStudentPhone(phone);
        student.setStudentAddress(address);
        student.setStudentBirthday(dob);
        student.setStudentGender(gender);
        tableStudents.refresh();
        AlertHelper.showAlert(AlertType.INFORMATION, "Thông báo", null, "Cập nhật thành công!");
    }

    public void onClickExport(ActionEvent actionEvent) {
        if (studentsList.isEmpty()) {
            AlertHelper.showAlert(AlertType.ERROR, "Lỗi", null, "Không có dữ liệu để xuất");
            return;
        } else {
            if (AlertHelper.showConfirmation("Bạn có muốn xuất dữ liệu ra excel không?")) {
                ExportToExcel.exportToExcel(tableStudents, "students.xlsx");
                AlertHelper.showAlert(AlertType.INFORMATION, "Thông báo", null, "Xuất dữ liệu thành công!");
            }
        }
    }

    public void onMouseClicked(MouseEvent mouseEvent) {
        Student student = tableStudents.getSelectionModel().getSelectedItem();
        txtAddress.setText(student.getStudentAddress());
        txtEmail.setText(student.getStudentEmail());
        txtName.setText(student.getStudentName());
        txtPhone.setText(student.getStudentPhone());
        txtStudentId.setText(student.getStudentId());
        cbDob.setValue(student.getStudentBirthday());
        cbGender.setValue(student.getStudentGender());
    }
}
