package modelo;

import java.util.ArrayList;
public class ListaClientes extends Lista<Cliente> {
    public ArrayList getEstandardClients() {
        ArrayList clientesEstandar = new ArrayList<>();
        for (Cliente cliente : this.lista) {
            if (cliente.getTipoCliente() == "estandar") {
                clientesEstandar.add(cliente);

            }
        }
        return clientesEstandar;
    }

    public ArrayList getPremiumClients() {
        ArrayList clientesEstandar = new ArrayList<>();
        for (Cliente cliente : this.lista) {
            if (cliente.getTipoCliente() == "premium") {
                clientesEstandar.add(cliente);

            }
        }
        return clientesEstandar;
    }

    public Cliente findClienteByNif(String nif) {
        Cliente clientefound = null;
        for (Cliente cliente : this.lista) {
            if (cliente.getNif() == nif) {
                clientefound = cliente;
            }
        }
        return clientefound;
    }

    @Override
    public String toString() {
        return "Listado de Clientes " + this.lista;
    }

}
