package com.Controller.Admin;

import com.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.AccessibleAction;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class NotificationController {
    @FXML
    TableView<String> tbNoti;
    @FXML
    TableColumn<String,type> columnType;
    @FXML
    TableColumn<String,idStudent> columnIdStudent;
    @FXML
    TableColumn<String,id> columnId;
    @FXML
    TableColumn<LocalDateTime,time> columnTime;
    @FXML
    Button btDetail;
    public void onClickDetail(ActionEvent actionEvent) throws IOException {

        App.setRootPop("DetailFrm","Chi tiet",false);
    }

}
