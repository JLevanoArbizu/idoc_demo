package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Municipalidad;
import modelo.Ubigeo;
import org.apache.commons.lang3.text.WordUtils;

public class MunicipalidadImpl extends Conexion implements ICrud<Municipalidad> {
    
    @Override
    public void registrar(Municipalidad modelo) throws Exception {
        try {
            String sql = "INSERT INTO MUNICIPALIDAD (CODUBI, DIRMUN, NOMMUN, ESTMUN, TLFMUN) VALUES (?,?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getUbigeo().getCODUBI());
            ps.setString(2, WordUtils.capitalize(modelo.getDIRMUN()));
            ps.setString(3, WordUtils.capitalize(modelo.getNOMMUN()));
            ps.setString(4, "A");
            ps.setString(5, modelo.getTLFMUN());
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
    }
    
    @Override
    public void editar(Municipalidad modelo) throws Exception {
        try {
            String sql = "UPDATE MUNICIPALIDAD SET CODUBI=?, DIRMUN=?, NOMMUN=?, ESTMUN=?, TLFMUN=? "
                    + "WHERE IDMUN=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getUbigeo().getCODUBI());
            ps.setString(2, modelo.getDIRMUN());
            ps.setString(3, modelo.getNOMMUN());
            ps.setString(4, String.valueOf(modelo.getESTMUN().charAt(0)));
            ps.setString(5, modelo.getTLFMUN());
            ps.setInt(6, modelo.getIDMUN());
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
    }
    
    @Override
    public void eliminar(Municipalidad modelo) throws Exception {
        try {
            String sql = "UPDATE MUNICIPALIDAD SET ESTMUN=? "
                    + "WHERE IDMUN=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, modelo.getIDMUN());
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
    }
    
    @Override
    public List<Municipalidad> listar() throws Exception {
        List<Municipalidad> lista = new ArrayList<>();
        try {
            String sql = "SELECT IDMUN, CODUBI, DIRMUN, NOMMUN, TLFMUN, ESTMUN FROM MUNICIPALIDAD";
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            while (rs.next()) {
                Municipalidad municipalidad = new Municipalidad();
                Ubigeo ubigeo = new Ubigeo();
                
                municipalidad.setIDMUN(rs.getInt(1));
                ubigeo.setCODUBI(rs.getString(2));
                municipalidad.setDIRMUN(rs.getString(3));
                municipalidad.setNOMMUN(rs.getString(4));
                municipalidad.setTLFMUN(rs.getString(5));
                municipalidad.setESTMUN(rs.getString(6));
                
                municipalidad.setUbigeo(ubigeo);
                lista.add(municipalidad);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return lista;
    }
    
    @Override
    public Municipalidad obtenerModelo(Municipalidad modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Municipalidad> listar(Municipalidad modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
