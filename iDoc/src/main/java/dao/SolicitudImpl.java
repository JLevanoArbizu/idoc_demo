package dao;

import java.sql.PreparedStatement;
import java.util.List;
import modelo.Solicitud;

public class SolicitudImpl extends Conexion implements IGenerica<Solicitud> {

    @Override
    public void registrar(Solicitud modelo) throws Exception {
        try {
            String sql = "INSERT INTO RegCiv.SOLICITUD (CODSOL, IDLOG, IDACTA, ESTSOL)"
                    + " VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, Integer.valueOf(modelo.getCODSOL()));
            ps.setInt(2, Integer.valueOf(modelo.getLogin().getIDLOG()));
            ps.setInt(4, Integer.valueOf(modelo.getActa().getIDACTA()));
            ps.setString(5, "1");
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();

            sql = "UPDATE TRADOC.TRANSFERENCIA SET FECSALTRAN=?, ESTDOC=? WHERE IDDOC=?";
            ps = this.conectar().prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(System.currentTimeMillis()));
            ps.setString(2,"1");
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
        List<Solicitud> lista = null;
        try {
            //1 Atendida 2 Pendiente
            String sql = "SELECT IDSOL, IDLOG, IDPER, IDACTA, FECSOL, ESTSOL";
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.desconectar();
        }
        return lista;
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

    @Override
    public void generarReporte(Solicitud modelo) throws Exception{

    }

}
