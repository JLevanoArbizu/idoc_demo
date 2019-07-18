package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    protected static Connection conexion = null;

    public static Connection conectar() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conexion = DriverManager.getConnection(
                    "jdbc:sqlserver://34.73.201.76;database=Team04",
                    "User04",
                    "TramiteDocumentario");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conexion;
    }

    public static boolean estado() throws Exception {
        boolean est = false;
        try {
            if (!conexion.isClosed()) {
                est = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
                System.out.println("dessssconectado");
        }
        return est;
    }

    public static void desconectar() throws SQLException {
        try {
            if (estado()) {
                conexion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
