package com.Controller.Admin;

import java.io.IOException;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.App;
import com.Models.Notification;
import com.utils.ExecuteQuery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class NotificationController {
    @FXML
    private TableView<Notification> tbNoti;
    @FXML
    private TableColumn<String, String> columnId;
    @FXML
    private TableColumn<String, String> columnIdStudent;
    @FXML
    private TableColumn<String, String> columnTime;

    private static String id;

    protected static ObservableList<Notification> list = FXCollections.observableArrayList();

    public void initialize() {
        showOnTable();
        initNoti();
    }

    public NotificationController() {
    }

    private void initNoti() {
        ExecuteQuery query = new ExecuteQuery("Select id, student_id, created_at from pending");
        ResultSet rs = query.executeQuery();

        try {
            while (rs.next()) {
                list.add(new Notification(rs.getString("id"), rs.getString("student_id"), rs.getString("created_at")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showOnTable() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnIdStudent.setCellValueFactory(new PropertyValueFactory<>("studentid"));
        columnTime.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        tbNoti.setItems(list);
    }

    public void onMouseClick(MouseEvent mouseEvent) {
        // String idSelected = tbNoti.getSelectionModel().getSelectedItem().getId();
        // System.out.println(idSelected);
    }

    public void onClickDetail(ActionEvent actionEvent) throws IOException {
        String idSelected = tbNoti.getSelectionModel().getSelectedItem().getId();
        String idSelectedStudent = tbNoti.getSelectionModel().getSelectedItem().getStudentid();
        id = idSelectedStudent;
        if (idSelected != null) {
            App.setRootPop("DetailFrm", "Detail", false);
        }
    }

    public void refresh() {
        list.removeIf(notification -> notification.getStudentid().equals(id));
        tbNoti.refresh();
    }

    public static String getStudentId() {
        return id;
    }

}
