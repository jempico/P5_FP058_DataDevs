package modelo;

public class ListaArticulos  extends Lista<Articulo>{
    
    @Override
    public String toString() {
        return "Listado de Articulos " + this.lista;
    }


    public Articulo findById(Integer id) {
        Articulo articuloFound = null;
        for (Articulo articulo : this.lista) {
            if (articulo.getId() == id) {
                articuloFound = articulo;
            }
        }
        return articuloFound;
    }
}
