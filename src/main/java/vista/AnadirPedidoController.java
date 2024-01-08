package main.java.vista;
import main.java.controlador.Controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AnadirPedidoController implements Initializable {
    @FXML
    private TextField txtcantidad;

    @FXML
    private TextField txtfecha;

    @FXML
    private TextField txtid_articulo;

    @FXML
    private TextField txtid_cliente;

    private GestionOSController controller;

    private Controlador controlador;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller = new GestionOSController();
        controlador = new Controlador();
    }

    @FXML
    private void addPedido() {

        try {
            // Obtén los valores de los TextField
            Integer cantidad = Integer.parseInt(txtcantidad.getText());
            String fechaHoraPedido = "2024-01-08 22:00";
            Integer idArticulo = Integer.parseInt(txtid_articulo.getText());
            Integer idCliente = Integer.parseInt(txtid_cliente.getText());

            // Llama al método addCliente del controlador
             controlador.addPedido(idCliente, idArticulo, cantidad, fechaHoraPedido);

            // Puedes mostrar un mensaje de éxito o realizar otras acciones según tus requisitos
            controller.mostrarMensaje("Pedido agregado correctamente");

        } catch (NumberFormatException e) {
            mostrarMensaje("Ha habido un error en el formato de entrada");
        }
    }

    private void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
// Resto del código de la clase AnadirArticuloController