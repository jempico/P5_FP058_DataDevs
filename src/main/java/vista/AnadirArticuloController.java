package main.java.vista;
import main.java.controlador.Controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AnadirArticuloController implements Initializable {

    @FXML
    private TextField txtid_articulo;

    @FXML
    private TextField txtdescripcion;

    @FXML
    private TextField txtpvp;

    @FXML
    private TextField txtgastosenvio;

    @FXML
    private TextField txtpreparacion;

    private GestionOSController controller;

    private Controlador controlador;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller = new GestionOSController();
        controlador = new Controlador();

    }

    @FXML
    private void addArticulo() {

        try {
            // Obtén los valores de los TextField
            int id_articulo = Integer.parseInt(txtid_articulo.getText());
            String descripcion = txtdescripcion.getText();
            Double pvp = Double.parseDouble(txtpvp.getText());
            Double gastosenvio = Double.parseDouble(txtgastosenvio.getText());
            int preparacion = Integer.parseInt(txtpreparacion.getText());

            // Llama al método addArticulo del controlador
            controlador.addArticulo(descripcion, pvp, gastosenvio, preparacion);

            // Puedes mostrar un mensaje de éxito o realizar otras acciones según tus requisitos
            controller.mostrarMensaje("Artículo agregado correctamente");

        } catch (NumberFormatException e) {
            mostrarMensaje("Ha habido un error en el formato de entrada");
        }
    }

    private void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
// Resto del código de la clase AnadirArticuloController