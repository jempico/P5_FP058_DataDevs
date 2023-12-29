package mysql;

import dao.ArticuloDAO;
import dao.DaoException;
import dao.PedidoDAO;
import modelo.Articulo;
import modelo.Cliente;
import modelo.Datos;
import modelo.Pedido;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MysqlPedidoDAO implements PedidoDAO {
    private Connection conn;
    MysqlArticuloDAO mysqlArticuloDAO;
    MysqlClienteDAO mysqlClienteDAO;

    final String INSERT = "INSERT INTO pedidos(id_pedido, cantidad, fecha, id_cliente, id_articulo) VALUES (?, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE pedidos SET id_pedido = ?, cantidad = ?, fecha = ?, id_cliente = ?, id_articulo = ? WHERE id_pedido = ?";
    final String DELETE = "DELETE FROM pedidos WHERE id_pedido = ?";
    final String GETALL = "SELECT id_pedido, cantidad, fecha, id_cliente, id_articulo FROM pedidos";
    final String GETONE = "SELECT id_pedido, cantidad, fecha, id_cliente, id_articulo FROM pedidos WHERE id_pedido = ?";

    String jdbc = "jdbc:mysql://localhost:3306/onlinestore";

    public MysqlPedidoDAO(Connection connection) {
        conn = connection;
        mysqlArticuloDAO = new MysqlArticuloDAO(conn);
        mysqlClienteDAO = new MysqlClienteDAO(conn);
    }

    @Override
    public void insertar(Pedido a) throws DaoException {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(INSERT);
            stat.setInt(1, a.getNumeroPedido());
            //stat.setObject(2, mysqlClienteDAO.obtener(a.getCliente().getNif()));
            //stat.setObject(3, mysqlArticuloDAO.obtener(a.getArticulo().getId()));
            stat.setInt(2, a.getCantidad());
            stat.setDate(3,  a.getFechaSqlDate());
            stat.setString(4, a.getCliente().getNif());
            stat.setInt(5, a.getArticulo().getId());
            stat.executeUpdate();
        }catch(SQLException ex) {
            throw new DaoException("Error en SQL", ex);
        }
        finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    throw new DaoException("Error en SQL", ex);
                }
            }
        }
    }

    private String parseSqlDateToString(Date date) throws SQLException  {
        // Convert java.sql.Date to LocalDateTime
        LocalDateTime localDateTime = date.toLocalDate().atStartOfDay();
         // Format pattern "yyyy-MM-dd hh:mm"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        // Format LocalDateTime as a string
        String formattedDate = localDateTime.format(formatter);

        return formattedDate;
    }
    private Pedido convertir(ResultSet rs) throws SQLException {

        try {
            Integer id_pedido = rs.getInt("id_pedido");
            Integer cantidad = rs.getInt("cantidad");
            Date fecha = rs.getDate("fecha");
            String id_cliente = rs.getString("id_cliente");
            Integer id_articulo = rs.getInt("id_articulo");
            Cliente cliente = mysqlClienteDAO.obtener(id_cliente);
            Articulo articulo = mysqlArticuloDAO.obtener(id_articulo);
            String fechaString = parseSqlDateToString(fecha);
            Pedido pedido = new Pedido(id_pedido, cliente, articulo, cantidad, fechaString);
            return pedido;
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Pedido obtener(Integer id) throws DaoException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Pedido p = null;
        try {
            stat = conn.prepareStatement(GETONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                p = convertir(rs);
            } else {
                throw new DaoException("No se ha encontrado ese registro");
            }
        } catch (SQLException ex) {
            throw new DaoException("Error en SQL", ex);
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch(SQLException ex) {
                    new DaoException("Error en SQL", ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    new DaoException("Error en SQL", ex);
                }
            }
        }
        return p;
    }

    @Override
    public ArrayList<Pedido> obtenerTodos() throws DaoException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                pedidos.add(convertir(rs));
            }
        } catch (SQLException ex) {
            throw new DaoException("Error en SQL", ex);
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch(SQLException ex) {
                    new DaoException("Error en SQL", ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    new DaoException("Error en SQL", ex);
                }
            }
        }
        return pedidos;
    }

    @Override
    public void eliminar(Integer id) throws DaoException {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(DELETE);
            stat.setInt(1, id);
            if (stat.executeUpdate() == 0) {
                throw new DaoException("Error de SQL");
            }
        } catch (SQLException ex) {
            throw new DaoException("Error de SQL", ex);
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    throw new DaoException("Error de SQL", ex);
                }
            }
        }
    }
}
