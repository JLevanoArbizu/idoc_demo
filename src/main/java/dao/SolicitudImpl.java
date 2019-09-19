package dao;

import java.sql.PreparedStatement;
import java.util.List;
import modelo.Solicitud;

public class SolicitudImpl extends Conexion implements ICrud<Solicitud> {

    @Override
    public void registrar(Solicitud modelo) throws Exception {
        try {
            String sql = "INSERT INTO SOLICITUD (CODSOL, IDLOG, IDACTA, ESTSOL)"
                    + " VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, Integer.valueOf(modelo.getCODSOL()));
            ps.setInt(2, modelo.getLogin().getIDLOG());
            ps.setInt(4, modelo.getActa().getIDACTA());
            ps.setString(5, "1");
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();

            sql = "UPDATE TRADOC.TRANSFERENCIA SET FECSALTRAN=?, ESTDOC=? WHERE IDDOC=?";
            ps = this.conectar().prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(System.currentTimeMillis()));
            ps.setString(2, "1");
            ps.setInt(3, Integer.valueOf(modelo.getCODSOL()));
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
    public List<Solicitud> listar(Solicitud modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Solicitud obtenerModelo(Solicitud modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
