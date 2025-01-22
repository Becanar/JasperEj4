package com.benat.cano.jasperej4.app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Esta es la clase principal de la aplicación JavaFX que lanza la interfaz gráfica del usuario.
 * Se encarga de cargar el archivo FXML y establecer el escenario inicial de la aplicación.
 * También gestiona la carga del icono de la ventana y el manejo del cierre de la aplicación.
 */
public class App extends Application {

    /**
     * Método que inicia la aplicación JavaFX.
     *
     * @param stage El escenario principal de la aplicación, proporcionado por JavaFX.
     * @throws IOException Si ocurre un error al cargar el archivo FXML o los recursos.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/benat/cano/jasperej4/fxml/medico.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        try {
            Image img = new Image(getClass().getResource("/com/benat/cano/jasperej4/img/logo.png").toString());
            stage.getIcons().add(img);
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen: " + e.getMessage());
        }
        stage.setTitle("Consultas");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> {
            // Confirmar si es necesario realizar acciones antes de cerrar
            System.out.println("Cerrando la aplicación...");
            Platform.exit(); // Termina la ejecución de JavaFX
            System.exit(0);  // Asegura que el proceso se detenga completamente
        });
        stage.show();
    }
    /**
     * Método principal para iniciar la aplicación.
     *
     * @param args Argumentos de línea de comandos que pueden ser utilizados al ejecutar la aplicación.
     */
    public static void main(String[] args) {
        Application.launch();
    }
}
