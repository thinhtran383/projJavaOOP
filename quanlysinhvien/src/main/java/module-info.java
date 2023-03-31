module com {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires transitive javafx.graphics;
    requires java.desktop;

    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    opens com to javafx.fxml;

    exports com;
    exports com.Controller;

    opens com.Controller to javafx.fxml;
}
