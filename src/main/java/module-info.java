module org.g9.challenge.aluralatam.miconversordemonedas {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.net.http;
    requires com.google.gson;
    requires javafx.media;
    //requires eu.hansolo.tilesfx;

    opens org.g9.challenge.aluralatam.miconversordemonedas to javafx.fxml;
    exports org.g9.challenge.aluralatam.miconversordemonedas;
}