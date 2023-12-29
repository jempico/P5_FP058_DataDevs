package mysql;

import controlador.Util;
import dao.ArticuloDAO;
import dao.DaoException;
import modelo.Articulo;
import modelo.Cliente;
import modelo.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlArticuloDAO implements ArticuloDAO {
    private Connection conn;
    final String INSERT = "INSERT INTO articulos(id_articulo, descripcion, pvp, gastosenvio, preparacion) VALUES (?, ?, ?, ?, ?)";
    final String DELETE = "DELETE FROM articulos WHERE id_articulo=?";
    final String GETALL = "SELECT id_articulo, descripcion, pvp, gastosenvio, preparacion FROM articulos";
    final String GETONE = "SELECT id_articulo, descripcion, pvp, gastosenvio, preparacion FROM articulos WHERE id_articulo = ?";

    Util util = new Util();

    public MysqlArticuloDAO(Connection connection) {
            conn = connection;
    }

    @Override
    public void insertar(Articulo a) throws DaoException {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(INSERT);
            stat.setInt(1, a.getId());
            stat.setString(2, a.getDescripcion());
            stat.setFloat(3, a.getPvp());
            stat.setFloat(4, a.getGastos());
            stat.setInt(5, a.getPreparacion());
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

    private Articulo convertir(ResultSet rs) throws SQLException {
        Integer id_articulo = rs.getInt("id_articulo");
        String descripcion = rs.getString("descripcion");
        Float pvp = rs.getFloat("pvp");
        Float gastosenvio = rs.getFloat("gastosenvio");
        Integer preparacion = rs.getInt("preparacion");
        Articulo articulo = new Articulo(id_articulo, descripcion, pvp, gastosenvio, preparacion);
        return articulo;
    }

    @Override
    public Articulo obtener(Integer id) throws DaoException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Articulo a = null;
        try {
            stat = conn.prepareStatement(GETONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                a = convertir(rs);
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
        return a;
    }

    @Override
    public ArrayList<Articulo> obtenerTodos() throws DaoException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        ArrayList<Articulo> articulos = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                articulos.add(convertir(rs));
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
        return articulos;
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
