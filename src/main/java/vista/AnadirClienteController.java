package main.java.vista;
import main.java.controlador.Controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AnadirClienteController implements Initializable {

    @FXML
    private TextField txtnif;

    @FXML
    private TextField txtnombre;

    @FXML
    private TextField txtdomicilio;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txttipo;

    private GestionOSController controller;

    private Controlador controlador;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller = new GestionOSController();
        controlador = new Controlador();
    }

    @FXML
    private void addCliente() {

        try {
            // Obtén los valores de los TextField
            String nombre = txtnombre.getText();
            String domicilio = txtdomicilio.getText();
            String email = txtemail.getText();
            String nif = txtnif.getText();
            Integer tipoCliente = Integer.parseInt(txttipo.getText());

            // Llama al método addCliente del controlador
            controlador.addCliente(nombre, domicilio, email, nif, tipoCliente);
             // Puedes mostrar un mensaje de éxito o realizar otras acciones según tus requisitos
            controller.mostrarMensaje("Cliente agregado correctamente");

        } catch (NumberFormatException e) {
            mostrarMensaje("Ha habido un error en el formato de entrada");
        }
    }

    private void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
// Resto del código de la clase AnadirArticuloController