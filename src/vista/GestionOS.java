package vista;

import controlador.Controlador;
import modelo.Articulo;
import modelo.Cliente;
import modelo.Pedido;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionOS {


    private Controlador controlador;
    Scanner teclado = new Scanner(System.in);

    public void inicio() {
        controlador = new Controlador();
        boolean salir = false;
        String opcion;
        cargarDatosEjemplo();

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

    public void cargarDatosEjemplo() {
        controlador.addArticulo(111, "mesa", 40.5F, 10.5F, 5000);
        controlador.addArticulo(222, "silla", 25.5F, 5.5F, 5000);
        controlador.addArticulo(333, "armario", 115.5F, 25.5F, 5000);
        controlador.addCliente("Ana", "C/Estevez 1", "ana@gmail.com", "44488765J", "Estándar");
        controlador.addCliente("Sofía", "Plaça Catalunya 2", "sofia@gmail.com", "78653325N", "Estándar");
        controlador.addCliente("Miguel", "Passeig de Gracia 2", "miguel@gmail.com", "6667895T", "Premium");
        controlador.addCliente("Carlos", "C/Verdi 7", "carlos@gmail.com", "17894565R", "Premium");
        controlador.addPedido(56401,"78653325N", 111, 2, "2023-11-01 21:15");
        controlador.addPedido(97415,"6667895T", 222, 4, "2023-10-28 11:30");
    }

    public String getCurrentDateTime() {
        return "2023-11-29T00:00:00";
    }
    private void mostrarPedidosEnviados() {
        System.out.println("¿Quieres filtrar por cliente? Sí/No");
        String bool = teclado.next();
        if (bool.equalsIgnoreCase("No")) {
            ArrayList<Pedido> lista = controlador.mostrarPedidosEnviados();
            System.out.println("************** PEDIDOS ENVIADOS **************");
            for (Pedido pedido : lista) {
                System.out.println("-----------------------------------------");
                System.out.println("Fecha pedido: " + pedido.getFecha());
                System.out.println("Tiempo preparacion (en minutos): " + pedido.getArticulo().getPreparacion());
                System.out.println("Fecha actual: " + LocalDateTime.now());
                System.out.println("Minutos de diferencia: " + pedido.calcDiferencia(pedido.getFecha(), LocalDateTime.now()));

            }
            System.out.println("**************************************************");
        } else {
            System.out.println("***** Escoge un cliente *****");
            this.mostrarClientes();
            System.out.println("Ingresa el número del cliente: ");
            Integer indexCliente = teclado.nextInt();
            controlador.mostrarPedidosEnviados(indexCliente);
        }
    }

    private void mostrarPedidosPendientes() {
        System.out.println("¿Quieres filtrar por cliente? Sí/No");
        String bool = teclado.next();
        if (bool.equalsIgnoreCase("No")) {
            ArrayList<Pedido> lista = controlador.mostrarPedidosPendientes();
            System.out.println("************** PEDIDOS PENDIENTES **************");
            for (Pedido pedido : lista) {
                System.out.println(pedido.toString());
                System.out.println("-----------------------------------------");
                System.out.println("Fecha pedido: " + pedido.getFecha());
                System.out.println("Tiempo preparacion (en minutos): " + pedido.getArticulo().getPreparacion());
                System.out.println("Fecha actual: " + LocalDateTime.now());
                System.out.println("Minutos de diferencia: " + pedido.calcDiferencia(pedido.getFecha(), LocalDateTime.now()));

            }
            System.out.println("**************************************************");
        } else {
            System.out.println("***** Escoge un cliente *****");
            this.mostrarClientes();
            System.out.println("Ingresa el número del cliente: ");
            Integer indexCliente = teclado.nextInt();
            controlador.mostrarPedidosPendientes(indexCliente);

        }
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

    private void addPedido() throws InputMismatchException {
        // TODO Auto-generated method stub
        Integer indexCliente = 0;
        int idArticulo = 0;
        int unidades = 0;
        try {
            System.out.println("Numero de pedido: ");
            int numPedido = Integer.parseInt(teclado.nextLine());

            if (controlador.existePedido(numPedido)) {
                System.out.println("Ya existe un pedido con ese codigo");
            } else {
                System.out.println("Escoge el cliente del pedido.");
                System.out.println("----------------------------------");
                System.out.println("");
                this.mostrarClientes();
                System.out.println("");
                System.out.println("Ingresa el nif del cliente: ");
                String nifCliente = teclado.next();

                System.out.println("Escoge el articulo del pedido.");
                System.out.println("----------------------------------");
                mostrarArticulos();
                System.out.println("----------------------------------");
                System.out.println("Ingresa el número de ID del articulo: ");
                idArticulo = teclado.nextInt();
                System.out.println("Ingresa el número de unidades del artículo: ");
                unidades = teclado.nextInt();
                System.out.println(numPedido);
                System.out.println(idArticulo);
                System.out.println(unidades);
                controlador.addPedido(numPedido, nifCliente, idArticulo, unidades,  "2023-11-29 00:00");
            }
        } catch (InputMismatchException e) {
            System.out.println("Ha habido algún error en el tipo de dato introducido. Vuelve a intentarlo");
            System.exit(0);
        }
    }

    private void mostrarPedidos() {
        ArrayList<Pedido> lista = controlador.mostrarPedidos();

        System.out.println("******* PEDIDOS  ********");
        for (Pedido pedido : lista) {
            System.out.println(pedido.toString());
        }
        System.out.println("*************************");
    }

    private void mostrarClientesEstandar() {
        ArrayList<Cliente> lista = controlador.mostrarClientesEstandar();

        System.out.println("******* LISTADO DE CLIENTES (ESTÁNDAR)  ********");
        for (Cliente cliente : lista) {
            System.out.println(cliente);
        }
        System.out.println("**************************************************");


    }

    private void mostrarClientesPremium() {
        ArrayList<Cliente> lista = controlador.mostrarClientesPremium();

        System.out.println("******* LISTADO DE CLIENTES (PREMIUM)  ********");
        for (Cliente cliente : lista) {
            System.out.println(cliente);
        }
        System.out.println("**************************************************");

    }

    private void mostrarClientes() {
        ArrayList<Cliente> lista = controlador.mostrarClientes();

        System.out.println("******* LISTADO DE CLIENTES (ESTÁNDAR Y PREMIUM)  ********");
        for (Cliente cliente : lista) {
            System.out.println(lista.indexOf(cliente) + 1 + ": " + cliente);
        }
        System.out.println("***********************************************************");
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

            System.out.println("Tipo de cliente (Estándar/Premium): ");
            String tipoCliente = teclado.next();

            controlador.addCliente(nombre, domicilio, email, nif, tipoCliente);
        } catch (InputMismatchException e) {
            System.out.println("Ha habido algún error en el tipo de dato introducido. Vuelve a intentarlo");
            System.exit(0);
        }

    }

    private void mostrarArticulos() {
        ArrayList<Articulo> lista = controlador.mostrarArticulos();

        System.out.println("******* LISTADO DE ARTÍCULOS  ********");
        for (Articulo articulo : lista) {
            System.out.println(lista.indexOf(articulo)+1 + ": " + articulo);
        }
        System.out.println("************************************");
    }

    private void addArticulo() throws InputMismatchException {
        try {
            System.out.println("Código del artículo: ");
            Integer codigo = teclado.nextInt();
            System.out.println("Descripción: ");
            String descripcion = teclado.next();
            System.out.println("Precio: ");
            float precio = teclado.nextFloat();
            System.out.println("Gastos de envío: ");
            float gastos = teclado.nextFloat();
            System.out.println("Preparación: ");
            Integer preparacion = teclado.nextInt();
            controlador.addArticulo(codigo, descripcion, precio, gastos, preparacion);
        } catch (InputMismatchException e) {
            System.out.println("Ha habido algún error en el tipo de dato introducido. Vuelve a intentarlo");
            System.exit(0);
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