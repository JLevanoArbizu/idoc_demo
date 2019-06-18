package servicios;

import dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AutoCompleteS extends Conexion{
    
    public List<String> queryAutoCompleteUbi(String a) throws Exception {
        this.conectar();
        ResultSet rs;
        List<String> listaUbi;
        try {
            String sql = "SELECT CODUBI,CONCAT(DEPUBI,', ',PROUBI,', ',DISUBI) AS UBIGEO FROM UBIGEO WHERE DEPUBI LIKE ? OR PROUBI LIKE ? OR DISUBI LIKE ?";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, "%" + a + "%");
            ps.setString(2, "%" + a + "%");
            ps.setString(3, "%" + a + "%");
            rs = ps.executeQuery();
            listaUbi = new ArrayList();
            while (rs.next()) {
                listaUbi.add(rs.getString("UBIGEO"));
            }
            return listaUbi;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }
    
    
    public String leerUbi(String a) throws Exception {
        this.conectar();
        ResultSet rs;
        try {
            String sql = "SELECT CODUBI FROM UBIGEO WHERE CONCAT(DEPUBI,', ',PROUBI,', ',DISUBI) = ?";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, a);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("CODUBI");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }
    
    public List<String> queryAutoCompletePersona(String a) throws Exception {
        this.conectar();
        ResultSet rs;
        List<String> listaUbi;
        try {
            String sql = "SELECT CODPER, CONCAT(NOMPER,' ',APEPATPER,' ',APEMATPER) AS PERSONA FROM PERSONA WHERE (NOMPER LIKE ? OR APEPATPER LIKE ? OR APEMATPER LIKE ?) AND ESTPER LIKE 'A'";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, "%" + a + "%");
            ps.setString(2, "%" + a + "%");
            ps.setString(3, "%" + a + "%");
            rs = ps.executeQuery();
            listaUbi = new ArrayList();
            while (rs.next()) {
                listaUbi.add(rs.getString("PERSONA"));
            }
            return listaUbi;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }
    
    public String leerPersona(String a) throws Exception {
        this.conectar();
        ResultSet rs;
        try {
            String sql = "SELECT CODPER FROM PERSONA WHERE CONCAT(NOMPER,' ',APEPATPER,' ',APEMATPER) = ?";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, a);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("CODPER");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }
    
}
