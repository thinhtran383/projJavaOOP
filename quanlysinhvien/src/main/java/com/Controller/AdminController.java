package com.Controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.Models.Courses;
import com.utils.ExecuteQuery;
import com.utils.ExportToExcel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

// import java.io.IOException;

// import com.App;
// import com.Controller.LogoutController;

public class AdminController {
    @FXML
    private TableView<Courses> table;
    @FXML
    private TableColumn<Courses, String> idColumn;
    @FXML
    private TableColumn<Courses, String> nameColumn;
    @FXML
    private TableColumn<Courses, Integer> creditsColumn;
    @FXML
    Button btnExport;
    private ObservableList<Courses> coursesList = FXCollections.observableArrayList();

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
        table.setItems(coursesList);
    }

    public void onPressExport(ActionEvent actionEvent) {
        try {
            // Tạo một workbook mới
            XSSFWorkbook workbook = new XSSFWorkbook();
            // Tạo một sheet mới
            XSSFSheet sheet = workbook.createSheet("Courses");
            // Tạo một hàng mới trong sheet
            XSSFRow headerRow = sheet.createRow(0);

            // Tạo các cell cho header
            XSSFCell idHeader = headerRow.createCell(0);
            idHeader.setCellValue("ID");

            XSSFCell nameHeader = headerRow.createCell(1);
            nameHeader.setCellValue("Name");

            XSSFCell creditsHeader = headerRow.createCell(2);
            creditsHeader.setCellValue("Credits");

            // Duyệt qua các dòng của table view và thêm vào sheet
            for (int i = 0; i < coursesList.size(); i++) {
                Courses course = coursesList.get(i);
                XSSFRow row = sheet.createRow(i + 1);

                XSSFCell idCell = row.createCell(0);
                idCell.setCellValue(course.getCourseId());

                XSSFCell nameCell = row.createCell(1);
                nameCell.setCellValue(course.getCourseName());

                XSSFCell creditsCell = row.createCell(2);
                creditsCell.setCellValue(course.getCredits());
            }

            // Tạo một file mới
            FileOutputStream fileOut = new FileOutputStream("courses.xlsx");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // try {
        // ExportToExcel.export(table, "courses.xlsx");
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
    }
}
