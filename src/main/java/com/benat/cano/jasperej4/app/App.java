package com.benat.cano.jasperej4.app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * La clase principal que arranca la aplicación JavaFX y genera el informe usando JasperReports.
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/benat/cano/jasperej3/fxml/super.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setWidth(400);
        stage.setHeight(200);
        try {
            Image img = new Image(getClass().getResource("/com/benat/cano/jasperej3/img/logo.jpg").toString());
            stage.getIcons().add(img);
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen: " + e.getMessage());
        }
        stage.setTitle("SuperWomble");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> {
            // Confirmar si es necesario realizar acciones antes de cerrar
            System.out.println("Cerrando la aplicación...");
            Platform.exit(); // Termina la ejecución de JavaFX
            System.exit(0);  // Asegura que el proceso se detenga completamente
        });
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
