package luis_rojas.luisr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/luis_rojas/luisr/clinical_lab.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        if (getClass().getResource("/luis_rojas/luisr/style.css") != null) {
            scene.getStylesheets().add(getClass().getResource("/luis_rojas/luisr/style.css").toExternalForm());
        }
        stage.setTitle("LabFlow - Presupuestos");
        stage.setScene(scene);
        stage.setWidth(1360);
        stage.setHeight(768);
        stage.setMinWidth(1280);
        stage.setMinHeight(720);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
