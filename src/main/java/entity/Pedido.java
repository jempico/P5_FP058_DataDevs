package main.java.entity;

import javax.persistence.*;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

@Entity
@NamedQuery(name="getallPedidos", query="select p from Pedido p")
@Table(name = "pedidos")
public class Pedido {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_pedido")
    private int idPedido;
    @Basic
    @Column(name = "cantidad")
    private Integer cantidad;
    @Basic
    @Column(name = "fecha")
    private Date fecha;
    @Basic
    @Column(name = "id_articulo")
    private Integer idArticulo;
    @Basic
    @Column(name = "id_cliente")
    private Integer idCliente;

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public LocalDateTime getFechaInLocalDateTime() {
        Date fecha = this.getFecha();
        return fecha.toLocalDate().atStartOfDay();
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public long calcDiferencia(LocalDateTime datePedido, LocalDateTime dateNow) {
        Duration duration = Duration.between(datePedido, dateNow);
        return Math.abs(duration.toMinutes());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedidos = (Pedido) o;
        return idPedido == pedidos.idPedido && Objects.equals(cantidad, pedidos.cantidad) && Objects.equals(fecha, pedidos.fecha) && Objects.equals(idArticulo, pedidos.idArticulo) && Objects.equals(idCliente, pedidos.idCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPedido, cantidad, fecha, idArticulo, idCliente);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", cantidad=" + cantidad +
                ", fecha=" + fecha +
                ", idArticulo=" + idArticulo +
                ", idCliente=" + idCliente +
                '}';
    }
}
