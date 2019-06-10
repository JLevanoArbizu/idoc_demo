package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Municipalidad;
import org.apache.commons.lang3.text.WordUtils;

public class MunicipalidadImpl extends Conexion implements IGenerica<Municipalidad> {

    @Override
    public void registrar(Municipalidad modelo) throws Exception {
        try {
            String sql = "INSERT INTO GENERAL.MUNICIPALIDAD (CODUBI, DIRMUN, NOMMUN, ESTMUN, TLFMUN) VALUES (?,?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getCODUBI());
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
            String sql = "UPDATE GENERAL.MUNICIPALIDAD SET CODUBI=?, DIRMUN=?, NOMMUN=?, ESTMUN=?, TLFMUN=? "
                    + "WHERE IDMUN=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getCODUBI());
            ps.setString(2, modelo.getDIRMUN());
            ps.setString(3, modelo.getNOMMUN());
            ps.setString(4, String.valueOf(modelo.getESTMUN().charAt(0)));
            ps.setString(5, modelo.getTLFMUN());
            ps.setInt(6, Integer.valueOf(modelo.getIDMUN()));
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
            String sql = "UPDATE GENERAL.MUNICIPALIDAD SET ESTMUN=? "
                    + "WHERE IDMUN=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, Integer.valueOf(modelo.getIDMUN()));
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
        List<Municipalidad> lista = null;
        try {
            String sql = "SELECT * FROM GENERAL.MUNICIPALIDAD ORDER BY IDMUN DESC";
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            Municipalidad municipalidad;
            lista = new ArrayList<>();
            while (rs.next()) {
                municipalidad = new Municipalidad();
                municipalidad.setIDMUN(String.valueOf(rs.getInt("IDMUN")));
                municipalidad.setCODUBI(rs.getString("CODUBI"));
                municipalidad.setDIRMUN(rs.getString("DIRMUN"));
                municipalidad.setNOMMUN(rs.getString("NOMMUN"));
                municipalidad.setTLFMUN(rs.getString("TLFMUN"));
                municipalidad.setESTMUN(rs.getString("ESTMUN"));
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
    public List<String> buscar(String campo, List<Municipalidad> listaModelo) throws Exception {
        List<String> lista = new ArrayList<>();
        campo = campo.toUpperCase();
        for (Municipalidad next : listaModelo) {
            if (next.getNOMMUN().startsWith(campo)) {
                lista.add(next.getNOMMUN());
            }
        }
        return lista;
    }

    @Override
    public Municipalidad obtenerCodigo(List<Municipalidad> listaModelo, Municipalidad modelo) throws Exception {
        for (Municipalidad next : listaModelo) {
            if (next.getNOMMUN().equals(modelo.getNOMMUN())) {
                modelo.setIDMUN(next.getIDMUN());
                return modelo;
            }
        }
        return null;
    }

    @Override
    public boolean existe(List<Municipalidad> listaModelo, Municipalidad modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
