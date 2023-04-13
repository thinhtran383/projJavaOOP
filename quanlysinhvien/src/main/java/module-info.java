module com {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires transitive javafx.graphics;
    requires java.desktop;
    requires java.base;

    // requires org.controlsfx.controls;

    // requires javafx.swing;

    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    // requires org.apache.pdfbox;

    opens com to javafx.fxml;

    exports com;
    exports com.Controller;
    exports com.Models;
    exports com.utils;
    exports com.constants;

    exports com.Controller.Admin;
    exports com.Controller.Student;

    opens com.Controller to javafx.fxml;
    opens com.Controller.Admin to javafx.fxml;
    opens com.Controller.Student to javafx.fxml;
}
