package vista;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Puedes realizar inicializaciones adicionales aquí si es necesario
    }

    @FXML
    private void addArticulo() {
        try {
            // Obtén los valores de los TextField
            int id_articulo = Integer.parseInt(txtid_articulo.getText());
            String descripcion = txtdescripcion.getText();
            float pvp = Float.parseFloat(txtpvp.getText());
            float gastosenvio = Float.parseFloat(txtgastosenvio.getText());
            int preparacion = Integer.parseInt(txtpreparacion.getText());

            // Llama al método addArticulo del controlador
            GestionOSController controller = new GestionOSController();
            controller.addArticulo(id_articulo, descripcion, pvp, gastosenvio, preparacion);

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