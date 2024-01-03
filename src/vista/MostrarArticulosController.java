package vista;

import controlador.Util;
import dao.DaoException;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Articulo;
import mysql.MysqlArticuloDAO;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MostrarArticulosController implements Initializable {
    @FXML
    private TableView<Articulo> tableViewArticulos;

    @FXML
    private TableColumn<Articulo, Integer> colid_articulo;

    @FXML
    private TableColumn<Articulo, String> coldescripcion;

    @FXML
    private TableColumn<Articulo, Float> colpvp;

    @FXML
    private TableColumn<Articulo, Float> colgastosenvio;

    @FXML
    private TableColumn<Articulo, Integer> colpreparacion;

    private ObservableValue<Integer> preparacionCellValueFactory(TableColumn.CellDataFeatures<Articulo, Integer> cellData) {
        return cellData.getValue().preparacionProperty().asObject();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configurar las columnas de la tabla
        colid_articulo.setCellValueFactory(cellData -> cellData.getValue().id_articuloProperty().asObject());
        coldescripcion.setCellValueFactory(cellData -> cellData.getValue().descripcionProperty());
        colpvp.setCellValueFactory(cellData -> cellData.getValue().pvpProperty().asObject());
        colgastosenvio.setCellValueFactory(cellData -> cellData.getValue().gastosEnvioProperty().asObject());
        colpreparacion.setCellValueFactory(cellData -> cellData.getValue().preparacionProperty().asObject());

        // Inicializar la tabla
        tableViewArticulos.setItems(FXCollections.observableArrayList()); // Asegúrate de que la lista de elementos se inicializa correctamente

        // Cargar datos desde la base de datos u otro origen si es necesario
        cargarDatos();
    }

    private void cargarDatos() {
        // Obtener datos desde el DAO
        try {
            MysqlArticuloDAO mysqlArticuloDAO = new MysqlArticuloDAO(Util.conectar());
            ArrayList<Articulo> listaDeArticulos = mysqlArticuloDAO.obtenerTodos();

            // Agregar datos a la tabla
            tableViewArticulos.getItems().addAll(listaDeArticulos);
        } catch (DaoException e) {
            e.printStackTrace();
            // Manejar la excepción apropiadamente en tu aplicación
        }
    }
    public void mostrarArticulos(ArrayList<Articulo> listaDeArticulos) {
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
