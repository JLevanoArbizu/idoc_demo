package dao;

import java.sql.PreparedStatement;
import java.util.List;
import modelo.Solicitud;

public class SolicitudImpl extends Conexion implements IGenerica<Solicitud> {

    @Override
    public void registrar(Solicitud modelo) throws Exception {
        try {
            String sql = "INSERT INTO RegCiv.SOLICITUD (IDSOL, IDLOG, IDPER, IDACTA, FECSOL, ESTSOL)"
                    + " VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, 1);
            ps.setInt(2, Integer.valueOf(modelo.getLogin().getIDLOG()));
            ps.setInt(3, Integer.valueOf(modelo.getPersona().getIDPER()));
            ps.setInt(4, Integer.valueOf(modelo.getActa().getIDACTA()));
            ps.setDate(5, modelo.getFECSOL());
            ps.setString(6, "A");
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (Exception e) {
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void editar(Solicitud modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Solicitud modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Solicitud> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> buscar(String campo, List<Solicitud> listaModelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Solicitud obtenerCodigo(List<Solicitud> listaModelo, Solicitud modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existe(List<Solicitud> listaModelo, Solicitud modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
