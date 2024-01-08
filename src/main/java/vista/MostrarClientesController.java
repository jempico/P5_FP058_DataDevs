package main.java.vista;
import main.java.controlador.Controlador;
import main.java.entity.Cliente;

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

public class MostrarClientesController implements Initializable {

    private Controlador controlador;
    @FXML
    private TableView<Cliente> tableViewClientes;

    @FXML
    private TableColumn<Cliente, Integer> colid_cliente;

    @FXML
    private TableColumn<Cliente, String> colnombre;

    @FXML
    private TableColumn<Cliente, String> coldomicilio;

    @FXML
    private TableColumn<Cliente, String> colemail;

    @FXML
    private TableColumn<Cliente, String> coltipoCliente;

    //private ObservableValue<Integer> preparacionCellValueFactory(TableColumn.CellDataFeatures<Articulo, Integer> cellData) {
    //    return cellData.getValue().preparacionProperty().asObject();
    //}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controlador = new Controlador();

        // Configurar las columnas de la tabla
        colid_cliente.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("idCliente"));
        colnombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
        coldomicilio.setCellValueFactory(new PropertyValueFactory<Cliente, String>("domicilio"));
        colemail.setCellValueFactory(new PropertyValueFactory<Cliente, String>("email"));
        coltipoCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("tipoCliente"));

        // Inicializar la tabla
        tableViewClientes.setItems(FXCollections.observableArrayList()); // Asegúrate de que la lista de elementos se inicializa correctamente

        // Cargar datos desde la base de datos u otro origen si es necesario
        cargarDatos();
        mostrarArticulos();
    }

    private void cargarDatos() {
        // Obtener datos desde el DAO
        try {
            List<Cliente> listaDeCliente = controlador.mostrarClientes();
            System.out.println(listaDeCliente);
            // Agregar datos a la tabla
            tableViewClientes.getItems().addAll(listaDeCliente);
            this.mostrarClientes(listaDeCliente);
        } catch (Exception e) {
            e.printStackTrace();
            // Manejar la excepción apropiadamente en tu aplicación
        }
    }
    public void mostrarClientes(List<Cliente> listaDeClientes) {
        // Asegúrate de que tableViewClientes esté inicializado antes de intentar trabajar con él
        if (tableViewClientes != null) {
            // Limpia los elementos existentes en la tabla
            tableViewClientes.getItems().clear();

            // Agrega los nuevos elementos a la tabla
            tableViewClientes.getItems().addAll(listaDeClientes);
        } else {
            System.out.println("La tabla de clientes no está inicializada correctamente.");
        }
    }



    public void setGestionOSController(GestionOSController gestionOSController) {
    }

    public void mostrarArticulos() {

    }
}
