package main.java.vista;
import main.java.controlador.Controlador;
import main.java.entity.Articulo;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MostrarArticulosController implements Initializable {

    private Controlador controlador;
    @FXML
    private TableView<Articulo> tableViewArticulos;

    @FXML
    private TableColumn<Articulo, Integer> colid_articulo;

    @FXML
    private TableColumn<Articulo, String> coldescripcion;

    @FXML
    private TableColumn<Articulo, Double> colpvp;

    @FXML
    private TableColumn<Articulo, Double> colgastosenvio;

    @FXML
    private TableColumn<Articulo, Integer> colpreparacion;

    //private ObservableValue<Integer> preparacionCellValueFactory(TableColumn.CellDataFeatures<Articulo, Integer> cellData) {
    //    return cellData.getValue().preparacionProperty().asObject();
    //}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controlador = new Controlador();

        // Configurar las columnas de la tabla
        colid_articulo.setCellValueFactory(new PropertyValueFactory<Articulo, Integer>("id_articulo"));
        coldescripcion.setCellValueFactory(new PropertyValueFactory<Articulo, String>("descripcion"));
        colpvp.setCellValueFactory(new PropertyValueFactory<Articulo, Double>("pvp"));
        colgastosenvio.setCellValueFactory(new PropertyValueFactory<Articulo, Double>("gastosenvio"));
        colpreparacion.setCellValueFactory(new PropertyValueFactory<Articulo, Integer>("preparacion"));

        // Inicializar la tabla
        tableViewArticulos.setItems(FXCollections.observableArrayList()); // Asegúrate de que la lista de elementos se inicializa correctamente

        // Cargar datos desde la base de datos u otro origen si es necesario
        cargarDatos();
        mostrarArticulos();
    }

    private void cargarDatos() {
        // Obtener datos desde el DAO
        try {
            List<Articulo> listaDeArticulos = controlador.mostrarArticulos();
            System.out.println("Lista de articulos fetched");
            System.out.println(listaDeArticulos);
            // Agregar datos a la tabla
            tableViewArticulos.getItems().addAll(listaDeArticulos);
            this.mostrarArticulos(listaDeArticulos);
        } catch (Exception e) {
            e.printStackTrace();
            // Manejar la excepción apropiadamente en tu aplicación
        }
    }
    public void mostrarArticulos(List<Articulo> listaDeArticulos) {
        // Asegúrate de que tableViewArticulos esté inicializado antes de intentar trabajar con él
        if (tableViewArticulos != null) {
            // Limpia los elementos existentes en la tabla
            tableViewArticulos.getItems().clear();

            // Agrega los nuevos elementos a la tabla
            tableViewArticulos.getItems().addAll(listaDeArticulos);
        } else {
            System.out.println("La tabla de artículos no está inicializada correctamente.");
        }
    }

    

    public void setGestionOSController(GestionOSController gestionOSController) {
    }

    public void mostrarArticulos() {

    }
}
