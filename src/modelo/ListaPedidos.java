package modelo;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ListaPedidos extends Lista<Pedido> {

	public ListaPedidos() {
		super();
	}

	public void add(Pedido pedido) {
		super.add(pedido);
	}

	public void borrar(Pedido pedido) {
		super.borrar(pedido);
	}

	public Pedido getAt(int position) {
		return super.getAt(position);
	}

	public void clear() {
		super.clear();
	}

	public boolean isEmpty() {
		return super.isEmpty();
	}

	public int getSize() {
		return super.getSize();
	}

	@Override
	public String toString() {
		return "Lista de Pedidos: " + this.lista;
	}

	public void eliminarPedido(int numeroPedido) {
		Boolean found = false;
		for (Pedido pedido : this.lista) {
			if (pedido.getNumeroPedido() == numeroPedido) {
				found = true;
				this.lista.remove(pedido);
				System.out.println("***Pedido correctamente eliminado!!***");
				return;
			}
		}
		if (found == false) {
			System.out.println("No se ha encontrado el pedido");
		}
	}

	public ArrayList mostrarPedidosEnviados() {
		ArrayList pedidosEnviados = new ArrayList<>();
		for (Pedido pedido : this.lista) {
			if (pedido.getArticulo().getPreparacion() < pedido.calcDiferencia(pedido.getFecha(), LocalDateTime.now())) {
				pedidosEnviados.add(pedido);
			}
 		}
		 return pedidosEnviados;
	}
	public void mostrarPedidosEnviados(Cliente cliente) {
		Boolean found = false;
		System.out.println("************** PEDIDOS ENVIADOS **************");
		for (Pedido pedido : this.lista) {
			if (pedido.getCliente() == cliente) {
				if (pedido.getArticulo().getPreparacion() < pedido.calcDiferencia(pedido.getFecha(), LocalDateTime.now())) {
					found = true;
					System.out.println(this.lista.indexOf(pedido) + 1 + ": " + pedido);
					System.out.println("-----------------------------------------");
					System.out.println("Fecha pedido: " + pedido.getFecha());
					System.out.println("Tiempo preparacion: " + pedido.getArticulo().getPreparacion());
					System.out.println("Fecha actual: " + LocalDateTime.now());
					System.out.println("Minutos de diferencia: " + pedido.calcDiferencia(pedido.getFecha(), LocalDateTime.now()));
				}
				System.out.println("-----------------------------------------");
			}
		}
		if (found == false) {
			System.out.println(" No hay pedidos enviados");
			System.out.println("-----------------------------------------");
		}
	}

	public ArrayList mostrarPedidosPendientes() {
		ArrayList pedidosPendientes = new ArrayList<>();
		for (Pedido pedido : this.lista) {
			if (pedido.getArticulo().getPreparacion() > pedido.calcDiferencia(pedido.getFecha(), LocalDateTime.now())) {
				pedidosPendientes.add(pedido);
			}
		}
		return pedidosPendientes;
	}


	public void mostrarPedidosPendientes(Cliente cliente) {
		Boolean found = false;
		System.out.println("************** PEDIDOS PENDIENTES **************");
		for (Pedido pedido : this.lista) {
			if (pedido.getCliente() == cliente) {
				if (pedido.getArticulo().getPreparacion() > pedido.calcDiferencia(pedido.getFecha(), LocalDateTime.now())) {
					found = true;
					System.out.println(this.lista.indexOf(pedido) + 1 + ": " + pedido);
					System.out.println("-----------------------------------------");
					System.out.println("Fecha pedido: " + pedido.getFecha());
					System.out.println("Tiempo preparacion: " + pedido.getArticulo().getPreparacion());
					System.out.println("Fecha actual: " + LocalDateTime.now());
					System.out.println("Minutos de diferencia: " + pedido.calcDiferencia(pedido.getFecha(), LocalDateTime.now()));
				}
				System.out.println("-----------------------------------------");
			}
		}
		if (found == false) {
			System.out.println(" No hay pedidos pendientes");
			System.out.println("-----------------------------------------");
		}
	}




}
