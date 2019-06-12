package dao;

import java.sql.PreparedStatement;
import java.util.List;
import modelo.Login;

public class LoginImpl extends Conexion implements IGenerica<Login>{
    
    @Override
    public void registrar(Login modelo) throws Exception {
        try {
            String sql = "INSERT INTO GENERAL.LOGIN (IDTRAB, USRLOG, PSSWLOG, ESTLOG, TIPLOG) VALUES (?,?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, Integer.valueOf(modelo.getTrabajador().getIDTRAB()));
            ps.setString(2, modelo.getTrabajador().getPersona().getDNIPER());
            ps.setString(3, modelo.getTrabajador().getPersona().getDNIPER());
            ps.setString(4, "A");
            ps.setString(5, modelo.getTIPLOG());
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            this.desconectar();
        }
    }

    @Override
    public void editar(Login modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Login modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Login> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> buscar(String campo, List<Login> listaModelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Login obtenerCodigo(List<Login> listaModelo, Login modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existe(List<Login> listaModelo, Login modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
