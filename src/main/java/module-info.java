module com.example.ticket_management_system {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.management;

    opens com.example.ticket_management_system to javafx.fxml;
    exports com.example.ticket_management_system;
    exports controller;
    opens controller to javafx.fxml;
}