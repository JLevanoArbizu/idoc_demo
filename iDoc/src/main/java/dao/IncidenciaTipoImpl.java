package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import modelo.IncidenciaTipo;

public class IncidenciaTipoImpl extends Conexion implements IGenerica<IncidenciaTipo> {

    @Override
    public void registrar(IncidenciaTipo modelo) throws Exception {
        try {
            String sql = "INSERT INTO REGCIV.INCIDENCIA_TIPO (NOMINCTIP,TIPINCTIP,LEYINCTIP,ESTINCTIP) VALUES (?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getNOMINCTIP());
            ps.setString(2, modelo.getTIPINCTIP());
            ps.setString(3, modelo.getLEYINCTIP());
            ps.setString(4, "A");
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
    public void editar(IncidenciaTipo modelo) throws Exception {
        try {
            String sql = "UPDATE REGCIV.INCIDENCIA_TIPO SET NOMINCTIP=?, TIPINCTIP=?,LEYINCTIP=?,ESTINCTIP=? WHERE IDINCTIP=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getNOMINCTIP());
            ps.setString(2, modelo.getTIPINCTIP());
            ps.setString(3, modelo.getLEYINCTIP());
            ps.setString(4, modelo.getESTINCTIP());
            ps.setInt(5, Integer.valueOf(modelo.getIDINCTIP()));
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
    public void eliminar(IncidenciaTipo modelo) throws Exception {
        try {
            String sql = "UPDATE REGCIV.INCIDENCIA_TIPO SET ESTINCTIP=? WHERE IDINCTIP=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, Integer.valueOf(modelo.getIDINCTIP()));
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
    public List<IncidenciaTipo> listar() throws Exception {
        List<IncidenciaTipo> lista = null;
        try {
            String sql = "SELECT * FROM REGCIV.INCIDENCIA_TIPO ORDER BY IDINCTIP DESC";
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            IncidenciaTipo incidenciatipo;
            lista = new ArrayList<>();
            while (rs.next()) {
                incidenciatipo = new IncidenciaTipo();
                incidenciatipo.setIDINCTIP(String.valueOf(rs.getInt("IDINCTIP")));
                incidenciatipo.setNOMINCTIP(rs.getString("NOMINCTIP"));
                incidenciatipo.setTIPINCTIP(String.valueOf(rs.getString("TIPINCTIP").charAt(0)));
                incidenciatipo.setLEYINCTIP(rs.getString("LEYINCTIP"));
                incidenciatipo.setESTINCTIP(String.valueOf(rs.getString("ESTINCTIP").charAt(0)));
                lista.add(incidenciatipo);

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
    public List<String> buscar(String campo, List<IncidenciaTipo> listaModelo) throws Exception {
        List<String> lista = new ArrayList<>();
        campo = campo.toUpperCase();
        for (Iterator<IncidenciaTipo> iterator = listaModelo.iterator(); iterator.hasNext();) {
            IncidenciaTipo next = iterator.next();
            if (next.getNOMINCTIP().startsWith(campo)) {
                lista.add(next.getNOMINCTIP());
            }

        }
        return lista;
    }

    @Override
    public IncidenciaTipo obtenerCodigo(List<IncidenciaTipo> listaModelo, IncidenciaTipo modelo) throws Exception {
        for (IncidenciaTipo next : listaModelo) {
            if (next.getNOMINCTIP().equals(modelo.getNOMINCTIP())) {
                modelo.setIDINCTIP(next.getIDINCTIP());
                return modelo;
            }
        }
        return null;
    }

    @Override
    public boolean existe(List<IncidenciaTipo> listaModelo, IncidenciaTipo modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generarReporte(Map parameters) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
