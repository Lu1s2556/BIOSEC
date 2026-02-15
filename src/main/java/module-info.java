module luis_rojas.luisr {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.kordamp.ikonli.core;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    // Open the application package to JavaFX modules so Application subclasses and FXML controllers
    // can be accessed reflectively by the JavaFX launcher and the FXML loader.
    opens luis_rojas.luisr to javafx.graphics, javafx.fxml;
    exports luis_rojas.luisr;
}