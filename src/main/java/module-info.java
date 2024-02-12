module com.example.storenewproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires mongo.java.driver;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.example.storenewproject to javafx.fxml;
    exports com.example.storenewproject;
}