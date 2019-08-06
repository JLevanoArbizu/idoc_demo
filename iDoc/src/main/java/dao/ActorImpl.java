package dao;

import java.sql.PreparedStatement;
import java.util.HashSet;
import modelo.Actor;

public class ActorImpl extends Conexion implements ICrud<Actor> {

    @Override
    public void registrar(Actor modelo) throws Exception {
        try {
            String sql = "INSERT INTO REGCIV.ACTOR (IDACTA, IDPER, TIPACT) VALUES "
                    + "(?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getActa().getIDACTA());
            ps.setInt(2, modelo.getActor().getIDPER());
            ps.setString(3, modelo.getTIPACT());
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
    public void editar(Actor modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Actor modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    

    @Override
    public HashSet<Actor> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashSet<Actor> listar(Actor modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Actor obtenerModelo(Actor modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
