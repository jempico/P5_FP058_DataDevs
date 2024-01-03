package modelo;
import java.util.UUID;

public abstract class Cliente {
	private String nombre;
	private String domicilio;
	private String email;
	private String id_cliente;
	private String tipoCliente;


	public abstract String tipoCliente();
	public abstract float calcAnual();
	public abstract float descuentoEnv();
	public Cliente(String nombre, String domicilio, String email, String id_cliente, String tipoCliente) {
		super();
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.email = email;
		this.id_cliente = id_cliente;
		this.tipoCliente = tipoCliente;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}
	private UUID generateNIF() {
		UUID uuid = UUID.randomUUID();
		return uuid;
	}
	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", domicilio=" + domicilio + ", email=" + email + ", id_cliente=" + id_cliente + "]";
	}
	
	
	

}
