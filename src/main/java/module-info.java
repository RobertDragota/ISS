module org.iss2024.bug_detection {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens org.iss2024.bug_detection to javafx.fxml;
    exports org.iss2024.bug_detection;
    exports org.iss2024.bug_detection.Controllers;
    opens org.iss2024.bug_detection.Controllers to javafx.fxml;
    exports org.iss2024.bug_detection.Services;
    exports org.iss2024.bug_detection.Domain;
}