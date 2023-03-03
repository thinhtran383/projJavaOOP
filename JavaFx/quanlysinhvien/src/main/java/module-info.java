module com.group {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.group to javafx.fxml;
    exports com.group;
}
