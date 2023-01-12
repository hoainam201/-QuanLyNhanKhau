module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens view to javafx.fxml;
    exports view;

    opens controllers to javafx.fxml;
    exports controllers;

    opens controllers.nhanKhauControllers to javafx.fxml;
    exports controllers.nhanKhauControllers;

    opens controllers.hoKhauControllers to javafx.fxml;
    exports controllers.hoKhauControllers;

    opens controllers.covidControllers to javafx.fxml;
    exports controllers.covidControllers;

    opens models to javafx.base;
    exports models;
}