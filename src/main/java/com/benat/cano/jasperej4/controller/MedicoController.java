package com.benat.cano.jasperej4.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.util.HashMap;

/**
 * Controlador para la interfaz de usuario de la sección de "Médico".
 * Maneja los eventos de la interfaz, como la generación de informes y la validación de entradas.
 * También gestiona la interacción con el sistema de informes JasperReports.
 */
public class MedicoController {

    @FXML
    private VBox boxGeneral;

    @FXML
    private Button btGenerar;

    @FXML
    private Button btLimpiar;

    @FXML
    private Button btSalir;

    @FXML
    private Label lblCod;

    @FXML
    private Label lblDir;

    @FXML
    private Label lblEsp;

    @FXML
    private Label lblNomMed;

    @FXML
    private Label lblNomPac;

    @FXML
    private Label lblNum;

    @FXML
    private Label lblTitulo;

    @FXML
    private Label lblTrat;

    @FXML
    private FlowPane panelBotones;

    @FXML
    private GridPane panelPreguntas;

    @FXML
    private FlowPane panelTitulo;

    @FXML
    private TextField txtCod;

    @FXML
    private TextField txtDir;

    @FXML
    private TextField txtEsp;

    @FXML
    private TextField txtNomMed;

    @FXML
    private TextField txtNomPac;

    @FXML
    private TextField txtNum;

    @FXML
    private TextArea txtTrat;
    /**
     * Genera el informe en formato JasperReport y lo muestra en una ventana de visualización.
     * Realiza las validaciones necesarias antes de proceder con la generación del informe.
     *
     * @param event El evento de acción (clic en el botón).
     */
    @FXML
    void informe(ActionEvent event) {
        if (validar()) {
            try {
                HashMap<String, Object> parameters = new HashMap<>();

                parameters.put("NUMERO", txtNum.getText());
                parameters.put("NOMPAC", txtNomPac.getText());
                parameters.put("DIRECCION", txtDir.getText());
                parameters.put("CODIGO", txtCod.getText());
                parameters.put("NOMMED", txtNomMed.getText());
                parameters.put("ESPECIALIDAD", txtEsp.getText());
                parameters.put("TRATAMIENTO", txtTrat.getText());

                String imagePath = getClass().getResource("/com/benat/cano/jasperej4/img/logo.png").toString();
                parameters.put("IMAGE", imagePath);
                InputStream reportStream = getClass().getResourceAsStream("/com/benat/cano/jasperej4/jasper/medico.jasper");
                JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

                JasperViewer viewer = new JasperViewer(jasperPrint, false);
                viewer.setVisible(true);

            } catch (JRException e) {
                System.err.println(e.getMessage());
                mostrarAlerta("Ha ocurrido un error cargando el informe");
                e.printStackTrace();
            }

        }

    }
    /**
     * Valida los datos introducidos en los campos de texto antes de generar el informe.
     * Verifica si los campos están vacíos o contienen datos incorrectos (como números en campos de texto no numéricos).
     *
     * @return true si los datos son válidos, false en caso contrario.
     */
    private boolean validar() {
        StringBuilder error = new StringBuilder();

        if (txtNum.getText().isEmpty()) {
            error.append("El Número del Paciente no puede estar vacío!\n");
        } else {
            try {
                Integer.parseInt(txtNum.getText());
            } catch (NumberFormatException e) {
                error.append("El Número del Paciente debe ser numérico!\n");
            }
        }


        if (txtNomPac.getText().isEmpty()) {
            error.append("El campo de nombre del paciente no puede estar vacío!\n");
        } else {
            if (contieneNumeros(txtNomPac.getText())) {
                error.append("El nombre del paciente no puede contener números!\n");
            }
        }

        if (txtDir.getText().isEmpty()) {
            error.append("El campo de dirección del paciente no puede estar vacío!\n");
        }

        if (txtCod.getText().isEmpty()) {
            try {
                Integer.parseInt(txtCod.getText());
                error.append("El Código del Médico no puede estar vacío!\n");
            } catch (NumberFormatException e) {
                error.append("El Código del Médico no puede estar vacío y debe ser numérico!\n");
            }
        }

        if (txtNomMed.getText().isEmpty()) {
            error.append("El campo de nombre del médico no puede estar vacío!\n");
        } else {
            if (contieneNumeros(txtNomMed.getText())) {
                error.append("El nombre del médico no puede contener números!\n");
            }
        }

        if (txtEsp.getText().isEmpty()) {
            error.append("El campo de especialidad del médico no puede estar vacío!\n");
        } else {
            if (contieneNumeros(txtEsp.getText())) {
                error.append("La especialidad del médico no puede contener números!\n");
            }
        }

        if (txtTrat.getText().isEmpty()) {
            error.append("El campo de tratamiento no puede estar vacío!\n");
        }

        if (error.length() > 0) {
            mostrarAlerta(error.toString());
            return false;
        }

        return true;
    }

    /**
     * Verifica si el texto contiene números.
     *
     * @param texto El texto a verificar.
     * @return true si el texto contiene números, false si no los contiene.
     */
    private boolean contieneNumeros(String texto) {
        return texto.matches(".*\\d.*");
    }
    /**
     * Limpia todos los campos de texto en la interfaz de usuario.
     *
     * @param event El evento de acción (clic en el botón de limpiar).
     */
    @FXML
    void limpiar(ActionEvent event) {
        txtCod.setText("");
        txtDir.setText("");
        txtEsp.setText("");
        txtNum.setText("");
        txtTrat.setText("");
        txtNomMed.setText("");
        txtNomPac.setText("");
    }
    /**
     * Cierra la aplicación cuando el usuario hace clic en el botón de salir.
     *
     * @param event El evento de acción (clic en el botón de salir).
     */
    @FXML
    void salir(ActionEvent event) {
        Platform.exit();
    }
    /**
     * Muestra una alerta con el mensaje proporcionado.
     *
     * @param mensaje El mensaje a mostrar en la alerta.
     */
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de validación");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
