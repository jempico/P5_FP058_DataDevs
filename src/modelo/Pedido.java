package modelo;
import java.sql.Date;
import java.time.*;
import java.time.format.DateTimeFormatter;


public class Pedido {
	private int idPedido;
	private Cliente cliente;
	private Articulo articulo;
	private int cantidad;
	private LocalDateTime fecha;

	public boolean pedidoEnviado() {
		return false;
	}

	public float precioEnvio() {
		Float pvp = articulo.getPvp();
		Float costeEnvio = articulo.getGastos();
		String tipoCliente = cliente.getTipoCliente();
		if (tipoCliente == "premium") {
			return (pvp * cantidad) + (costeEnvio - costeEnvio * (cliente.descuentoEnv()/100));
		}
		return (pvp * cantidad) + costeEnvio;
	}

	public Pedido(int idPedido, Cliente cliente, Articulo articulo, int cantidad, String fechaHoraPedido) {
		this.idPedido = idPedido;
 		this.cantidad = cantidad;
		 this.cliente = cliente;
		 this.articulo = articulo;
		this.fecha = parseFecha(fechaHoraPedido);
	}


	public int getNumeroPedido() {
		return idPedido;
	}

	public void setNumeroPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}
	public Date getFechaSqlDate() {
		// Step 1: Convert LocalDateTime to java.util.Date
		java.util.Date utilDate = java.util.Date.from(fecha.atZone(ZoneId.systemDefault()).toInstant());
		// Step 2: Convert java.util.Date to java.sql.Date
		Date sqlDate = new Date(utilDate.getTime());
		return sqlDate;
	}

	private LocalDateTime parseFecha(String fechaToParse) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(fechaToParse, formatter);
		return dateTime;
	}

	public long calcDiferencia(LocalDateTime datePedido, LocalDateTime dateNow) {
		Duration duration = Duration.between(datePedido, dateNow);
		long diferencia = Math.abs(duration.toMinutes());
		return diferencia;
	}

	@Override
	public String toString() {
		return "Pedido{" +
				"idPedido=" + idPedido +
				", cliente=" + cliente +
				", articulo=" + articulo +
				", cantidad=" + cantidad +
				", fecha=" + fecha +
				'}';
	}
}

