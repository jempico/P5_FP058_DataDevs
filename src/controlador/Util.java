package controlador;
import dao.DaoFactory;

import java.sql.*;

public class Util {

    public static Connection conectar() {
        String jdbc = "jdbc:mysql://localhost:3306/onlinestore";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(jdbc,"root", "root");
            System.out.println("BBDD Correctamente conectada");
        } catch(Exception e) {
            System.out.println("Error en la conexión con BD");
            e.printStackTrace();
        }
    return conn;
    }

}
