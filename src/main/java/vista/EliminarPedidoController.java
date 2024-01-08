package main.java.vista;
import main.java.controlador.Controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EliminarPedidoController implements Initializable {
    @FXML
    private TextField txtid_pedido;

    private GestionOSController controller;

    private Controlador controlador;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller = new GestionOSController();
        controlador = new Controlador();
    }

    @FXML
    private void deletePedido() {

        try {
            // Obtén los valores de los TextField
            Integer idPedido = Integer.parseInt(txtid_pedido.getText());

            // Llama al método addCliente del controlador
            controlador.eliminarPedido(idPedido);

            // Puedes mostrar un mensaje de éxito o realizar otras acciones según tus requisitos
            controller.mostrarMensaje("Pedido eliminado correctamente");

        } catch (NumberFormatException e) {
            mostrarMensaje("Ha habido un error en el formato de entrada");
        }
    }

    private void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
// Resto del código de la clase AnadirArticuloController