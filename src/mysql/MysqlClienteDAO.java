package mysql;

import dao.ClienteDAO;
import dao.DaoException;
import modelo.Cliente;
import modelo.ClienteEstandard;
import modelo.ClientePremium;
import modelo.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlClienteDAO implements ClienteDAO {
    private Connection conn;


    final String INSERT = "INSERT INTO clientes(nif, nombre, domicilio, email, tipoCliente, calcAnual, descuentoEnv) VALUES (?, ?, ?, ?, ?, ?, ?)";
    final String DELETE = "DELETE FROM clientes WHERE nif = ?";

    final String GETALL = "SELECT nif, nombre, domicilio, email, tipoCliente, calcAnual, descuentoEnv FROM clientes";
    final String GETONE = "SELECT nif, nombre, domicilio, email, tipoCliente, calcAnual, descuentoEnv FROM clientes WHERE nif = ?";

    String jdbc = "jdbc:mysql://localhost:3306/onlinestore";

    public MysqlClienteDAO(Connection connection) {
        conn = connection;

        //try {
        //    conn = DriverManager.getConnection(jdbc,"root", "root");
        //    System.out.println("BBDD Correctamente conectada");
//
  //      } catch (SQLException ex) {
    //        ex.printStackTrace();
      //  }


    }

    @Override
    public void insertar(Cliente c) throws DaoException {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(INSERT);
            stat.setString(1, c.getNif());
            stat.setString(2, c.getNombre());
            stat.setString(3,  c.getDomicilio());
            stat.setString(4, c.getEmail());
            stat.setString(5, c.getTipoCliente());
            stat.setFloat(6, c.calcAnual());
            stat.setFloat(7, c.descuentoEnv());
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

    private Cliente convertir(ResultSet rs) throws SQLException {

        String nif = rs.getString("nif");
        String nombre = rs.getString("nombre");
        String domicilio = rs.getString("domicilio");
        String email = rs.getString("email");
        String tipoCliente = rs.getString("tipoCliente");
        Float calcAnual = rs.getFloat("calcAnual");
        Float descuentoEnv = rs.getFloat("descuentoEnv");
        Cliente cliente;
        if (tipoCliente.equalsIgnoreCase("estandar")) {
            cliente = new ClienteEstandard(nombre, domicilio, email, nif);
        } else if (tipoCliente.equalsIgnoreCase("premium")) {
            cliente = new ClientePremium(nombre, domicilio, email, nif);
        } else {
            cliente = new ClienteEstandard(nombre, domicilio, email, nif);
        }
        return cliente;

    }

    @Override
    public Cliente obtener(String id) throws DaoException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Cliente c = null;
        try {
            stat = conn.prepareStatement(GETONE);
            stat.setString(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                c = convertir(rs);
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
        return c;
    }

    @Override
    public ArrayList<Cliente> obtenerTodos() throws DaoException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                clientes.add(convertir(rs));
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
        return clientes;
    }

    @Override
    public void eliminar(String id) throws DaoException {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(DELETE);
            stat.setString(1, id);
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
