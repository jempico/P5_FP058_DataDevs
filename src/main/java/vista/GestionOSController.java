package main.java.vista;
import main.java.entity.Articulo;
import main.java.entity.Cliente;
import main.java.entity.Pedido;

import main.java.controlador.Controlador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class GestionOSController implements Initializable {

    public Controlador controlador;
    private AnadirArticuloController addArticuloController;
    // private MostrarArticulosController mostrarArticulosController;

    @FXML
    private Button btnAddArticulo;

    @FXML
    private Button btnMostrarArticulos;

    @FXML
    private Button btnAddCliente;

    @FXML
    private Button btnMostrarClientes;

    @FXML
    private Button btnMostrarClientesEstandar;

    @FXML
    private Button btnMostrarClientesPremium;

    @FXML
    private Button btnAddPedido;

    @FXML
    private Button btnEliminarPedido;

    @FXML
    private Button btnMostrarPedidos;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Im in initalizator gestionOS ");

        controlador = new Controlador();
    }



    @FXML
    private void handleAddArticuloAction() {
        // Lógica cuando se hace clic en "Anadir Articulo"
        abrirVentana("Añadir Artículo", "AnadirArticulo.fxml", new AnadirArticuloController(), null);
    }


    @FXML
    private void handleMostrarArticulosAction() {
        // Lógica cuando se hace clic en "Mostrar Articulos"
        //abrirVentana("Mostrar Artículos", "MostrarArticulos.fxml", new MostrarArticulosController(), controller -> {
            // Lógica para configurar el controlador de MostrarArticulos si es necesario
            //mostrarArticulosController = (MostrarArticulosController) controller;
            //mostrarArticulosController.setGestionOSController(this); // Asegúrate de llamar a este método
            // Obtiene la lista de artículos del método obtenerListaDeArticulos y la pasa al método mostrarArticulos
            //ArrayList<Articulo> listaDeArticulos = obtenerListaDeArticulos();
            //mostrarArticulosController.mostrarArticulos(listaDeArticulos);
   //     });
    }

    @FXML
    private void handleAddClienteAction() {
        // Lógica cuando se hace clic en "Añadir Cliente"
        addCliente();
    }

    @FXML
    private void handleMostrarClientesAction() {
        // Lógica cuando se hace clic en "Mostrar Clientes"
        mostrarClientes();
    }

    @FXML
    private void handleMostrarClientesEstandarAction() {
        // Lógica cuando se hace clic en "Mostrar Clientes Estándar"
        mostrarClientesEstandar();
    }

    @FXML
    private void handleMostrarClientesPremiumAction() {
        // Lógica cuando se hace clic en "Mostrar Clientes Premium"
        mostrarClientesPremium();
    }

    @FXML
    private void handleAddPedidoAction() {
        // Lógica cuando se hace clic en "Añadir Pedido"
        addPedido();
    }

    @FXML
    private void handleEliminarPedidoAction() {
        // Lógica cuando se hace clic en "Eliminar Pedido"
        eliminarPedido();
    }

    @FXML
    private void handleMostrarPedidosAction() {
        // Lógica cuando se hace clic en "Mostrar Pedidos"
        mostrarPedidos();
    }

    @FXML
    private void handleMostrarPedidosEnviados(){
        // Lógica cuando se hace clic en "Mostrar Pedidos"
        mostrarPedidosEnviados();
    }
    @FXML
    private void handleMostrarPedidosPendientes() {
        // Lógica cuando se hace clic en "Mostrar Pedidos"
        mostrarPedidosPendientes();

    }
    private void mostrarArticulos() {
        // Lógica para "Mostrar Artículos" - ahora llamando al método correspondiente en el controlador de MostrarArticulos
        //ArrayList<Articulo> listaDeArticulos = obtenerListaDeArticulos();
       // mostrarArticulosController.mostrarArticulos(listaDeArticulos);
    }

    private void addCliente() {
        // Implementa la lógica para "Añadir Cliente"
        System.out.println("Se hizo clic en Añadir Cliente");
    }

    private void mostrarClientes() {
        // Implementa la lógica para "Mostrar Clientes"
        System.out.println("Se hizo clic en Mostrar Clientes");
    }

    private void mostrarClientesEstandar() {
        // Implementa la lógica para "Mostrar Clientes Estándar"
        System.out.println("Se hizo clic en Mostrar Clientes Estándar");
    }

    private void mostrarClientesPremium() {
        // Implementa la lógica para "Mostrar Clientes Premium"
        System.out.println("Se hizo clic en Mostrar Clientes Premium");
    }

    private void addPedido() {
        // Implementa la lógica para "Añadir Pedido"
        System.out.println("Se hizo clic en Añadir Pedido");
    }

    private void eliminarPedido() {
        // Implementa la lógica para "Eliminar Pedido"
        System.out.println("Se hizo clic en Eliminar Pedido");
    }

    private void mostrarPedidos() {
        // Implementa la lógica para "Mostrar Pedidos"
        System.out.println("Se hizo clic en Mostrar Pedidos");
    }

    private void mostrarPedidosPendientes() {
            // Implementa la lógica para "Mostrar Pedidos"
            System.out.println("Se hizo clic en Mostrar Pedidos Pendientes");
    }
    private void mostrarPedidosEnviados() {
            // Implementa la lógica para "Mostrar Pedidos"
            System.out.println("Se hizo clic en Mostrar Pedidos Enviados");
    }

    private void abrirVentana(String titulo, String fxml, Object controller, Consumer<Object> configureController) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));

            // Usa el controlador existente si se proporciona, de lo contrario, crea uno nuevo
            Object actualController = controller != null ? controller : fxmlLoader.getController();

            Parent root = fxmlLoader.load();

            // Configura el controlador (si es necesario)
            if (configureController != null) {
                configureController.accept(actualController);
            } else {
                // Si no se proporciona un configurador externo, intenta configurar el controlador por defecto
                if (actualController instanceof Initializable) {
                    ((Initializable) actualController).initialize(null, null);

                    // Verifica si el controlador es de tipo MostrarArticulosController y configura la referencia de GestionOSController
                   // if (actualController instanceof MostrarArticulosController) {
                   //     ((MostrarArticulosController) actualController).setGestionOSController(this);
                   // }
                }
            }

            // Resto del código para mostrar la ventana
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //private ArrayList<Articulo> obtenerListaDeArticulos() {
        // Llamada a tu método en el DAO para obtener todos los artículos de la base de datos

    //}

    public void mostrarMensaje(String mensaje) {
        // Implementa la lógica para mostrar un mensaje (puede ser un cuadro de diálogo, consola, etc.)
        System.out.println(mensaje);
    }

    public void setAnadirArticuloController(AnadirArticuloController addArticuloController) {
        this.addArticuloController = addArticuloController;
    }

    public void setGestionOSController(GestionOSController gestionOSController) {
    }

    //public void setMostrarArticulosController(MostrarArticulosController mostrarArticulosController) {
    //    this.mostrarArticulosController = mostrarArticulosController;
    //}
}



