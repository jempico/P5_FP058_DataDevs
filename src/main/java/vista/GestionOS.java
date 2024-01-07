package main.java.vista;

import main.java.controlador.Controlador;
import main.java.entity.Articulo;
import main.java.entity.Cliente;
import main.java.entity.Pedido;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GestionOS {


    private Controlador controlador;
    Scanner teclado = new Scanner(System.in);

    public void inicio() {
        controlador = new Controlador();
        boolean salir = false;
        String opcion;
        //cargarDatosEjemplo();
        //controlador.addArticulo(456345645, "kokokoko", 10.0, 50.0, 0);
        // controlador.addPedido(2, 456,"17894565R", 222, 4, "2023-10-28 11:30");
        //controlador.addCliente("Antonio", "C/Estevez 1", "ant@gmail.com", "00088765K", "premium");
        // controlador.addCliente("Marga", "C/Estevez 1", "ant@gmail.com", "00088765L", "estandar");

        do {

            System.out.println("1. Añadir Artículo");
            System.out.println("2. Mostrar Artículos");
            System.out.println("3. Añadir Clientes");
            System.out.println("4. Mostrar Clientes");
            System.out.println("5. Mostrar Clientes Estándar");
            System.out.println("6. Mostrar Clientes Premium");
            System.out.println("7. Añadir Pedido");
            System.out.println("8. Eliminar Pedido");
            System.out.println("9. Mostar pedidos pendientes");
            System.out.println("10. Mostrar pedidos enviados");
            System.out.println("11. Mostrar todos los pedidos ");
            System.out.println("0. Salir");
            opcion = pedirOpcion();
            switch (opcion) {
                case "1":
                    addArticulo();
                    break;
                case "2":
                    mostrarArticulos();
                    break;
                case "3":
                    addCliente();
                    break;
                case "4":
                    mostrarClientes();
                    break;
                case "5":
                    mostrarClientesEstandar();
                    break;
                case "6":
                    mostrarClientesPremium();
                    break;
                case "7":
                    addPedido();
                    break;
                case "8":
                    eliminarPedido();
                    break;
                case "9":
                    mostrarPedidosPendientes();
                    break;
                case "10":
                    mostrarPedidosEnviados();
                    break;
                case "11":
                    mostrarPedidos();
                    break;
                case "0":
                    salir = true;
                    break;

            }

        } while (!salir);

    }

    private void addArticulo() throws InputMismatchException {
        try {
            System.out.println("Descripción: ");
            String descripcion = teclado.next();
            System.out.println("Precio: ");
            Double precio = teclado.nextDouble();
            System.out.println("Gastos de envío: ");
            Double gastos = teclado.nextDouble();
            System.out.println("Preparación: ");
            Integer preparacion = teclado.nextInt();
            controlador.addArticulo(descripcion, precio, gastos, preparacion);
        } catch (InputMismatchException e) {
            System.out.println("Ha habido algún error en el tipo de dato introducido. Vuelve a intentarlo");
            System.exit(0);
        }

    }

    private void mostrarArticulos() {
        List<Articulo> lista = controlador.mostrarArticulos();

        System.out.println("******* LISTADO DE ARTÍCULOS  ********");
        for (Articulo articulo : lista) {
            System.out.println(lista.indexOf(articulo)+1 + ": " + articulo);
        }
        System.out.println("************************************");
    }

    private void addCliente() throws InputMismatchException {
        try {
            System.out.println("Nombre del cliente: ");
            String nombre = teclado.next();
            System.out.println("Domicilio del cliente: ");
            String domicilio = teclado.next(); // Cambio nextLine() a next()
            teclado.nextLine(); // Agrego esta línea para consumir el salto de línea pendiente

            System.out.println("Email del cliente: ");
            String email = teclado.next();
            System.out.println("NIF del cliente: ");
            String nif = teclado.next();

            System.out.println("Tipo de cliente (ingresa el número): ");
            System.out.println("1: Premium ");
            System.out.println("2: Estandar ");
            Integer tipoCliente = teclado.nextInt();

            controlador.addCliente(nombre, domicilio, email, nif, tipoCliente);
        } catch (InputMismatchException e) {
            System.out.println("Ha habido algún error en el tipo de dato introducido. Vuelve a intentarlo");
            System.exit(0);
        }
    }

    private void mostrarClientes() {
        List<Cliente> lista = controlador.mostrarClientes();

        System.out.println("******* LISTADO DE CLIENTES (ESTÁNDAR Y PREMIUM)  ********");
        for (Cliente cliente : lista) {
            System.out.println(lista.indexOf(cliente) + 1 + ": " + cliente);
        }
        System.out.println("***********************************************************");
    }

    private void mostrarClientesEstandar() {
        List<Cliente> lista = controlador.mostrarClientesEstandar();

        System.out.println("******* LISTADO DE CLIENTES (ESTÁNDAR)  ********");
        for (Cliente cliente : lista) {
            System.out.println(cliente);
        }
        System.out.println("**************************************************");
    }

    private void mostrarClientesPremium() {
        List<Cliente> lista = controlador.mostrarClientesPremium();

        System.out.println("******* LISTADO DE CLIENTES (PREMIUM)  ********");
        for (Cliente cliente : lista) {
            System.out.println(cliente);
        }
        System.out.println("**************************************************");
    }

    private void eliminarPedido() throws InputMismatchException {

        try {
            System.out.println(" Eliminar pedido ");
            System.out.println("----------------------------------");
            System.out.println("");
            mostrarPedidos();
            System.out.println("Ingresa el NÚMERO de ID de Pedido que quieras eliminar.");
            int idPedido = teclado.nextInt();
            controlador.eliminarPedido(idPedido);
        } catch (InputMismatchException e) {
            System.out.println("Ha habido algún error en el tipo de dato introducido. Vuelve a intentarlo");
            System.exit(0);
        }
    }

    private void mostrarPedidos() {
        List<Pedido> lista  = controlador.mostrarPedidos();

        System.out.println("******* PEDIDOS  ********");
        for (Pedido pedido : lista) {
            System.out.println(pedido.toString());
        }
        System.out.println("*************************");
    }

    private void mostrarPedidosPendientes() {
        System.out.println("¿Quieres filtrar por cliente? Sí/No");
        String bool = teclado.next();
        if (bool.equalsIgnoreCase("No")) {
            List<Pedido> lista = controlador.mostrarPedidosPendientes();
            System.out.println("************** PEDIDOS PENDIENTES **************");
            for (Pedido pedido : lista) {
                System.out.println(pedido.toString());
                System.out.println("-----------------------------------------");
            }
            System.out.println("**************************************************");
        } else {
            System.out.println("***** Escoge un cliente *****");
            this.mostrarClientes();
            System.out.println("Ingresa el número del cliente: ");
            Integer indexCliente = teclado.nextInt();
            List<Pedido> lista = controlador.mostrarPedidosPendientes(indexCliente);

            System.out.println("************** PEDIDOS PENDIENTES POR CLIENTE **************");
            for (Pedido pedido : lista) {
                System.out.println(pedido.toString());
             }
            System.out.println("**************************************************");

        }
    }

    private void addPedido() throws InputMismatchException {
        // TODO Auto-generated method stub
        Integer indexCliente = 0;
        int idArticulo = 0;
        int unidades = 0;
        try {
                System.out.println("Escoge el cliente del pedido.");
                System.out.println("----------------------------------");
                System.out.println("");
                this.mostrarClientes();
                System.out.println("");
                System.out.println("Ingresa el id del cliente: ");
                Integer idCliente = teclado.nextInt();

                System.out.println("Escoge el articulo del pedido.");
                System.out.println("----------------------------------");
                mostrarArticulos();
                System.out.println("----------------------------------");
                System.out.println("Ingresa el número de ID del articulo: ");
                idArticulo = teclado.nextInt();
                System.out.println("Ingresa el número de unidades del artículo: ");
                unidades = teclado.nextInt();

            controlador.addPedido(idCliente, idArticulo, unidades,  "2024-01-02 23:30");

        } catch (InputMismatchException e) {
            System.out.println("Ha habido algún error en el tipo de dato introducido. Vuelve a intentarlo");
            System.exit(0);
        }
    }

    private void mostrarPedidosEnviados() {
        System.out.println("¿Quieres filtrar por cliente? Sí/No");
        String bool = teclado.next();
        if (bool.equalsIgnoreCase("No")) {
            List<Pedido> lista = controlador.mostrarPedidosEnviados();
            System.out.println("************** PEDIDOS ENVIADOS **************");
            for (Pedido pedido : lista) {
                System.out.println("-----------------------------------------");
                System.out.println(pedido.toString());

            }
            System.out.println("**************************************************");
        } else {
            System.out.println("***** Escoge un cliente *****");
            this.mostrarClientes();
            System.out.println("Ingresa el número del cliente: ");
            Integer indexCliente = teclado.nextInt();
            List<Pedido> lista = controlador.mostrarPedidosEnviados(indexCliente);

            System.out.println("************** PEDIDOS ENVIADOS POR CLIENTE **************");
            for (Pedido pedido : lista) {
                System.out.println(pedido.toString());
                System.out.println("-----------------------------------------");
            }
            System.out.println("**************************************************");

        }
    }


    private String pedirOpcion() {
        String respuesta;
        teclado = new Scanner(System.in);
        System.out.print("¿Qué quieres hacer?");
        respuesta = teclado.nextLine();
        return respuesta;
    }

}