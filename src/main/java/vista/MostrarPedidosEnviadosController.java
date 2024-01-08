package main.java.vista;
import main.java.controlador.Controlador;
import main.java.entity.Pedido;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MostrarPedidosEnviadosController implements Initializable {

    private Controlador controlador;
    @FXML
    private TableView<Pedido> tableViewPedidos;

    @FXML
    private TableColumn<Pedido, Integer> colid_pedido;

    @FXML
    private TableColumn<Pedido, String> colcantidad;

    @FXML
    private TableColumn<Pedido, String> colfecha;

    @FXML
    private TableColumn<Pedido, String> colid_articulo;

    @FXML
    private TableColumn<Pedido, String> colid_cliente;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controlador = new Controlador();

        // Configurar las columnas de la tabla
        colid_pedido.setCellValueFactory(new PropertyValueFactory<Pedido, Integer>("idPedido"));
        colcantidad.setCellValueFactory(new PropertyValueFactory<Pedido, String>("cantidad"));
        colfecha.setCellValueFactory(new PropertyValueFactory<Pedido, String>("fecha"));
        colid_articulo.setCellValueFactory(new PropertyValueFactory<Pedido, String>("idArticulo"));
        colid_cliente.setCellValueFactory(new PropertyValueFactory<Pedido, String>("idCliente"));

        // Inicializar la tabla
        tableViewPedidos.setItems(FXCollections.observableArrayList()); // Asegúrate de que la lista de elementos se inicializa correctamente

        // Cargar datos desde la base de datos u otro origen si es necesario
        cargarDatos();
    }

    private void cargarDatos() {
        // Obtener datos desde el DAO
        try {
            List<Pedido> listaDePedidosEnviados = controlador.mostrarPedidosEnviados();
            System.out.println(listaDePedidosEnviados);
            // Agregar datos a la tabla
            tableViewPedidos.getItems().addAll(listaDePedidosEnviados);
            this.mostrarPedidosEnviados(listaDePedidosEnviados);
        } catch (Exception e) {
            e.printStackTrace();
            // Manejar la excepción apropiadamente en tu aplicación
        }
    }
    public void mostrarPedidosEnviados(List<Pedido> listaDePedidos) {
        // Asegúrate de que tableViewClientes esté inicializado antes de intentar trabajar con él
        if (tableViewPedidos != null) {
            // Limpia los elementos existentes en la tabla
            tableViewPedidos.getItems().clear();

            // Agrega los nuevos elementos a la tabla
            tableViewPedidos.getItems().addAll(listaDePedidos);
        } else {
            System.out.println("La tabla de clientes no está inicializada correctamente.");
        }
    }



    public void setGestionOSController(GestionOSController gestionOSController) {
    }

    public void mostrarArticulos() {

    }
}
