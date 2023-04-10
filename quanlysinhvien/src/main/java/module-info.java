module com {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires transitive javafx.graphics;
    requires java.desktop;
    requires java.base;

    // requires javafx.swing;

    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    // requires org.apache.pdfbox;

    opens com to javafx.fxml;

    exports com;
    exports com.Controller;
    exports com.Models;
    exports com.utils;

    opens com.Controller to javafx.fxml;
}
