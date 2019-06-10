package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import modelo.Trabajador;

public class TrabajadorImpl extends Conexion implements IGenerica<Trabajador> {

    @Override
    public void registrar(Trabajador modelo) throws Exception {
        try {
            String sql = "INSERT INTO GENERAL.TRABAJADOR (IDARE, IDPER, ESTTRAB, FECINITRAB, FECFINTRAB) VALUES (?,?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, Integer.valueOf(modelo.getArea().getIDARE()));
            ps.setInt(2, Integer.valueOf(modelo.getPersona().getIDPER()));
            ps.setString(3, "A");
            ps.setDate(4, modelo.getFECINITRAB());
            if (modelo.getFECFINTRAB() != null) {
                ps.setDate(5, modelo.getFECFINTRAB());
            } else {
                ps.setNull(5, java.sql.Types.NULL);
            }
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
    public void editar(Trabajador modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Trabajador modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Trabajador> listar() throws Exception {
        List<Trabajador> listaTrabajador = null;
        try {
            String sql = "SELECT IDTRAB, IDPER, IDARE, FECINITRAB, FECFINTRAB, ESTTRAB FROM General.TRABAJADOR";
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            Trabajador trabajador;
            while (rs.next()) {                
                trabajador = new Trabajador();
                
            }
        } catch (Exception e) {
        }finally{
            this.desconectar();
        }
        return listaTrabajador;
    }

    @Override
    public List<String> buscar(String campo, List<Trabajador> listaModelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Trabajador obtenerCodigo(List<Trabajador> listaModelo, Trabajador modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existe(List<Trabajador> listaModelo, Trabajador modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
