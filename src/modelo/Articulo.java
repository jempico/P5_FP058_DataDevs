package modelo;

import javafx.beans.binding.FloatExpression;
import javafx.beans.binding.IntegerExpression;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Articulo {
    private SimpleIntegerProperty id;
    private StringProperty descripcion;
    private SimpleFloatProperty pvp;
    private SimpleFloatProperty gastosenvio;
    private SimpleIntegerProperty preparacion;

    public Articulo(Integer id, String descripcion, float pvp, float gastosenvio, Integer preparacion) {
        this.id = new SimpleIntegerProperty(id);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.pvp = new SimpleFloatProperty(pvp);
        this.gastosenvio = new SimpleFloatProperty(gastosenvio);
        this.preparacion = new SimpleIntegerProperty(preparacion);
    }

    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public float getPvp() {
        return pvp.get();
    }

    public void setPvp(float pvp) {
        this.pvp.set(pvp);
    }

    public float getGastosEnvio() {
        return gastosenvio.get();
    }

    public void setGastosEnvio(float gastosenvio) {
        this.gastosenvio.set(gastosenvio);
    }

    public Integer getPreparacion() {
        return preparacion.get();
    }

    public void setPreparacion(Integer preparacion) {
        this.preparacion.set(preparacion);
    }

    @Override
    public String toString() {
        return "Articulo{" +
                "id=" + id +
                ", descripción='" + descripcion + '\'' +
                ", pvp=" + pvp +
                ", gastos envio=" + gastosenvio +
                ", preparación=" + preparacion +
                '}';
    }

    public IntegerExpression id_articuloProperty() {
        return id;
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }

    public FloatExpression pvpProperty() {
        return pvp;
    }

    public FloatExpression gastosEnvioProperty() {
        return gastosenvio;
    }

    public IntegerExpression preparacionProperty() {
        return preparacion;
    }
}
