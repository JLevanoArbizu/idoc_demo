package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Login;
import modelo.Persona;
import modelo.Sugerencia;
import modelo.Trabajador;

public class SugerenciaImpl extends Conexion implements ICrud<Sugerencia> {

    @Override
    public void registrar(Sugerencia modelo) throws Exception {
        try {
            String sql = "INSERT INTO SUGERENCIA (SUG, IDLOG) VALUES (?, ?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getSUG());
            ps.setInt(2, modelo.getLogin().getIDLOG());
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
    public void editar(Sugerencia modelo) throws Exception {
        try {
            String sql = "UPDATE SUGERENCIA SET ESTSUG=? WHERE IDSUG=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getESTSUG());
            ps.setInt(2, modelo.getIDSUG());
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
    public void eliminar(Sugerencia modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sugerencia> listar() throws Exception {
        List<Sugerencia> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT sug.IDSUG, sug.SUG, sug.ESTSUG,\n"
                    + "           L.IDLOG,\n"
                    + "       P.APEPATPER, P.APEMATPER, P.NOMPER\n"
                    + "FROM SUGERENCIA sug\n"
                    + "INNER JOIN LOGIN L on sug.IDLOG = L.IDLOG\n"
                    + "INNER JOIN TRABAJADOR T on L.IDTRAB = T.IDTRAB\n"
                    + "INNER JOIN PERSONA P on T.IDPER = P.IDPER";
            ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Sugerencia sugerencia = new Sugerencia();
                Login login = new Login();
                Trabajador trabajador = new Trabajador();
                Persona persona = new Persona();
                
                sugerencia.setIDSUG(rs.getInt(1));
                sugerencia.setSUG(rs.getString(2));
                sugerencia.setESTSUG(rs.getString(3));
                
                login.setIDLOG(rs.getInt(4));
                
                persona.setAPEPATPER(rs.getString(5));
                persona.setAPEMATPER(rs.getString(6));
                persona.setNOMPER(rs.getString(7));

                trabajador.setPersona(persona);
                login.setTrabajador(trabajador);
                sugerencia.setLogin(login);
                lista.add(sugerencia);
            }
            ps.closeOnCompletion();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps.isClosed()) {
                ps.clearParameters();
                rs.close();
                this.desconectar();
            }
        }
        return lista;
    }

    @Override
    public List<Sugerencia> listar(Sugerencia modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sugerencia obtenerModelo(Sugerencia modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
