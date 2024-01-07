package main.java.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NamedQuery(name="getallClientes", query="select p from Cliente p")
@Table(name = "clientes")
public class Cliente {
    @Basic
    @Column(name = "nif")
    private String nif;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_cliente")
    private int idCliente;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "domicilio")
    private String domicilio;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "tipoCliente")
    private String tipoCliente;
    @Basic
    @Column(name = "calcAnual")
    private Double calcAnual;
    @Basic
    @Column(name = "descuentoEnv")
    private Double descuentoEnv;

    @Basic
    @Column(name = "tipo")
    private Integer tipo;

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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

    public String getTipoCliente() {
        return this.tipoCliente;
    }


    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Integer getTipo() {
        return this.tipo;
    }

    public void setTipo(Integer num) {
        this.tipo = num;
    }

    public Double getCalcAnual() {
        return calcAnual;
    }

    public void setCalcAnual() {

        if (this.getTipo() == 1) {
            this.calcAnual = 30.00;
        } else {
            this.calcAnual = 0.00;
        }
    }

    public Double getDescuentoEnv() {
        return descuentoEnv;
    }

    public void setDescuentoEnv() {
        if (this.getTipo() == 1) {
            this.descuentoEnv = 20.00;
        } else {
            this.descuentoEnv = 0.00;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente clientes = (Cliente) o;
        return idCliente == clientes.idCliente && Objects.equals(nif, clientes.nif) && Objects.equals(nombre, clientes.nombre) && Objects.equals(domicilio, clientes.domicilio) && Objects.equals(email, clientes.email) && Objects.equals(tipoCliente, clientes.tipoCliente) && Objects.equals(calcAnual, clientes.calcAnual) && Objects.equals(descuentoEnv, clientes.descuentoEnv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nif, idCliente, nombre, domicilio, email, tipoCliente, calcAnual, descuentoEnv);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id_cliente='" + idCliente + '\'' +
                ", nif='" + nif + '\'' +
                ", nombre='" + nombre + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", email='" + email + '\'' +
                ", tipoCliente='" + tipoCliente + '\'' +
                ", calcAnual=" + calcAnual +
                ", descuentoEnv=" + descuentoEnv +
                '}';
    }
}
