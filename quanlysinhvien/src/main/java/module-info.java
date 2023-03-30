module com {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires transitive javafx.graphics;

    opens com to javafx.fxml;

    exports com;
    exports com.Controller;

    opens com.Controller to javafx.fxml;
}
