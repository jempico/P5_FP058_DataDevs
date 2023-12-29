package modelo;

public class ClienteEstandard extends Cliente{

	public ClienteEstandard(String nombre, String domicilio, String email, String nif) {
		super(nombre, domicilio, email, nif, "estandar");

	}

	@Override
	public String tipoCliente() {
		return "Cliente Estandar";
	}

	@Override
	public float calcAnual() {
		return 0;
	}

	@Override
	public float descuentoEnv() {
		return 0;
	}

	@Override
	public String toString() {
		return "ClienteEstandard{} " + super.toString()+ ", cuota anual="+ calcAnual()+ ", descuento gastos envío=" + descuentoEnv();
	}
}
