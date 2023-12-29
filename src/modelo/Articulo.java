package modelo;

public class Articulo {
    private Integer id;
    private String descripcion;
    private float pvp;
    private float gastosenvio;
    private Integer preparacion;

    public Articulo(Integer id, String descripcion, float pvp, float gastosenvio, Integer preparacion) {
        this.id = id;
        this.descripcion = descripcion;
        this.pvp = pvp;
        this.gastosenvio = gastosenvio;
        this.preparacion = preparacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPvp() {
        return pvp;
    }

    public void setPvp(float pvp) {
        this.pvp = pvp;
    }

    public float getGastos() {
        return gastosenvio;
    }

    public void setGastos(float gastos) {
        this.gastosenvio = gastosenvio;
    }


    public Integer getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(Integer preparacion) {
        this.preparacion = preparacion;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Articulo{" +
                "id=" + id +
                ", descripción='" + descripcion + '\'' +
                ", pvp=" + pvp +
                ", gastos envio=" + gastosenvio +
                ", preparación=" + preparacion +
                '}';
    }
}
