package modelo;

import controlador.Util;
import dao.DaoException;
import mysql.MysqlArticuloDAO;
import mysql.MysqlClienteDAO;
import mysql.MysqlPedidoDAO;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Datos {
	private ListaArticulos listaArticulos;
	private ListaClientes listaClientes;
	private ListaPedidos listaPedidos;

	MysqlArticuloDAO mysqlArticuloDAO;
	MysqlPedidoDAO mysqlPedidoDAO;
	MysqlClienteDAO mysqlClienteDAO;

	Connection connection;

	public Datos() {
		listaArticulos = new ListaArticulos();
		listaClientes = new ListaClientes();
		listaPedidos = new ListaPedidos();
		connection = Util.conectar();
		mysqlArticuloDAO = new MysqlArticuloDAO(connection);
		mysqlPedidoDAO = new MysqlPedidoDAO(connection);
		mysqlClienteDAO = new MysqlClienteDAO(connection);
	}

	public void addArticulo(Integer codigo, String descripcion, float precio, float gastos, Integer preparacion) {
		Articulo articulo = new Articulo(codigo, descripcion, precio, gastos, preparacion);
		listaArticulos.add(articulo);
		try {
			mysqlArticuloDAO.insertar(articulo);
		} catch (DaoException e) {
			throw new RuntimeException(e);
		}

		System.out.println("***Artículo correctamente añadido!!***");
		System.out.println(articulo.toString());
	}


	public void addPedido(Integer idPedido, String nif, Integer idArticulo, Integer cantidad, String fechaHoraPedido) {
		try {
			 Articulo articulo = mysqlArticuloDAO.obtener(idArticulo);
			 Cliente cliente = mysqlClienteDAO.obtener(nif);
			 Pedido pedido = new Pedido(idPedido, cliente, articulo, cantidad, fechaHoraPedido);
			 mysqlPedidoDAO.insertar(pedido);
		} catch (DaoException e) {
			throw new RuntimeException(e);
		}
		System.out.println("***Pedido correctamente añadido!!***");
	}

	public ArrayList<Pedido> mostrarPedidos(){
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		 try {
			 pedidos = mysqlPedidoDAO.obtenerTodos();
			} catch (DaoException e) {
				throw new RuntimeException(e);
			}
	 return pedidos;
	}
	public void eliminarPedido(int numeroPedido)
	{
		try {
			mysqlPedidoDAO.eliminar(numeroPedido);
		} catch (DaoException e) {
			throw new RuntimeException(e);
		}
	}


	public ArrayList mostrarArticulos() {
		try {
			return mysqlArticuloDAO.obtenerTodos();
		} catch (DaoException e) {
			throw new RuntimeException(e);
		}

	}

	public void addCliente(String nombre, String domicilio, String email, String nif, String tipoCliente) {

		Cliente cliente;

		if (tipoCliente.equalsIgnoreCase("estandar")) {
			cliente = new ClienteEstandard(nombre, domicilio, email, nif);
		} else if (tipoCliente.equalsIgnoreCase("premium")) {
			cliente = new ClientePremium(nombre, domicilio, email, nif);
		} else {
			System.out.println("Tipo de cliente no válido. Se creará como Estándar por defecto.");
			cliente = new ClienteEstandard(nombre, domicilio, email, nif);
		}

		listaClientes.add(cliente);
		try {
			mysqlClienteDAO.insertar(cliente);
			} catch (DaoException e) {
				throw new RuntimeException(e);
			}
		System.out.println("***Cliente correctamente añadido!!***");
		System.out.println(cliente.toString());
	}

	public ArrayList mostrarClientes() {

		ArrayList<Cliente> clientes = new ArrayList<>();
		try {
			clientes = mysqlClienteDAO.obtenerTodos();
				} catch (DaoException e) {
					throw new RuntimeException(e);
			}
		return clientes;

	}
 	public ArrayList mostrarClientesEstandar() {
		ArrayList<Cliente> clientes = new ArrayList<>();
		ArrayList<Cliente> clientesEstandar = new ArrayList<>();

		try {
			clientes = mysqlClienteDAO.obtenerTodos();

			for (Cliente cliente : clientes) {
				if (cliente.getTipoCliente() == "estandar") {
					clientesEstandar.add(cliente);
				}
			}
		} catch (DaoException e) {
			throw new RuntimeException(e);
		}
		return clientesEstandar;

	}
	public ArrayList mostrarClientesPremium() {

		ArrayList<Cliente> clientes = new ArrayList<>();
		ArrayList<Cliente> clientesPremium = new ArrayList<>();

		try {
			clientes = mysqlClienteDAO.obtenerTodos();

			for (Cliente cliente : clientes) {
				if (cliente.getTipoCliente() == "premium") {
					clientesPremium.add(cliente);
				}
			}
		} catch (DaoException e) {
			throw new RuntimeException(e);
		}
		return clientesPremium;
	}

	public ArrayList<Pedido> mostrarPedidosEnviados() {
		ArrayList<Pedido> pedidosEnviados = new ArrayList<>();
		ArrayList<Pedido> pedidos = new ArrayList<>();

		try {
			pedidos = mysqlPedidoDAO.obtenerTodos();
			for (Pedido pedido : pedidos) {
				if (pedido.getArticulo().getPreparacion() < pedido.calcDiferencia(pedido.getFecha(), LocalDateTime.now())) {
					pedidosEnviados.add(pedido);
				}
			}
		} catch (DaoException e) {
			throw new RuntimeException(e);
		}

		return pedidosEnviados;
	}

	public void mostrarPedidosEnviados(Integer indexCliente) {
		Cliente clienteFound = listaClientes.getAt(indexCliente-1);
		listaPedidos.mostrarPedidosEnviados(clienteFound);
	}
	public ArrayList<Pedido> mostrarPedidosPendientes() {

		ArrayList<Pedido> pedidosPendientes = new ArrayList<>();
		ArrayList<Pedido> pedidos = new ArrayList<>();

		try {
			pedidos = mysqlPedidoDAO.obtenerTodos();
			for (Pedido pedido : pedidos) {
				if (pedido.getArticulo().getPreparacion() > pedido.calcDiferencia(pedido.getFecha(), LocalDateTime.now())) {
					pedidosPendientes.add(pedido);
				}
			}
		} catch (DaoException e) {
			throw new RuntimeException(e);
		}

		return pedidosPendientes;
	}

	public void mostrarPedidosPendientes(Integer indexCliente) {
		Cliente clienteFound = listaClientes.getAt(indexCliente-1);
		listaPedidos.mostrarPedidosPendientes(clienteFound);
	}

	public Cliente findClienteByNif(String nif) {
		return listaClientes.findClienteByNif(nif);
	}

	public Articulo findArticuloById(Integer id) {
		return listaArticulos.findById(id);
	}

}
