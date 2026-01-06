package org.g9.challenge.aluralatam.miconversordemonedas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ConversorView.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Conversor");
        stage.setScene(scene);
        // Iniciar maximizado para que ocupe toda la pantalla
        stage.setMaximized(true);
        // Establecer un tamaño mínimo razonable para evitar controles demasiado pequeños
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.show();
    }
}
