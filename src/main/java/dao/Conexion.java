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
                    "Demo123456789");

//            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
//            conexion = DriverManager.getConnection(
//                    "jdbc:oracle:thin:@40.117.85.170:1521:XE",
//                    "IDOC",
//                    "IDOC");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conexion;
    }

    public static void desconectar() throws SQLException {
        try {
            if (conexion.isClosed() == false) {
                conexion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
