package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import modelo.Login;
import modelo.Persona;
import modelo.Trabajador;
import servicios.EncriptarS;

public class LoginImpl extends Conexion implements ICrud<Login> {

    @Override
    public void registrar(Login modelo) throws Exception {
        try {
            String sql = "INSERT INTO GENERAL.LOGIN (IDTRAB, USRLOG, PSSWLOG, ESTLOG, TIPLOG) VALUES (?,?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getTrabajador().getIDTRAB());
            ps.setString(2, modelo.getTrabajador().getPersona().getDNIPER());
            ps.setString(3, EncriptarS.encriptarPssw(modelo.getTrabajador().getPersona().getDNIPER()));
            ps.setString(4, "A");
            ps.setString(5, modelo.getTIPLOG());
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
    public void editar(Login modelo) throws Exception {
        try {
            String sql = "UPDATE GENERAL.LOGIN SET USRLOG=?, PSSWLOG=? WHERE IDLOG=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getUSRLOG());
            ps.setString(2, EncriptarS.encriptarPssw(modelo.getPSSWLOG()));
            ps.setInt(3, modelo.getIDLOG());
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
    }

    public void editar2(Login modelo) throws Exception {
        try {
            String sql = "UPDATE GENERAL.LOGIN SET PSSWLOG=? WHERE USRLOG=? AND ESTLOG='A'";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getTrabajador().getPersona().getDNIPER());
            ps.setString(2, EncriptarS.encriptarPssw(modelo.getTrabajador().getPersona().getDNIPER()));
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
    public void eliminar(Login modelo) throws Exception {
        try {
            String sql = "UPDATE GENERAL.LOGIN SET ESTLOG=? WHERE USRLOG=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setString(2, modelo.getTrabajador().getPersona().getDNIPER());
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
    public HashSet<Login> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Login obtenerModelo(Login modelo) throws Exception {
        try {
            String sql = "SELECT GENERAL.LOGIN.IDLOG, GENERAL.LOGIN.IDTRAB, GENERAL.LOGIN.TIPLOG, per.NOMPER FROM GENERAL.LOGIN "
                    + "INNER JOIN GENERAL.TRABAJADOR trab "
                    + "ON GENERAL.LOGIN.IDTRAB = trab.IDTRAB "
                    + "INNER JOIN GENERAL.PERSONA per "
                    + "ON trab.IDPER = per.IDPER "
                    + "WHERE GENERAL.LOGIN.USRLOG='" + modelo.getUSRLOG() + "' "
                    + "AND GENERAL.LOGIN.PSSWLOG='" + EncriptarS.encriptarPssw(modelo.getPSSWLOG()) + "' "
                    + "AND GENERAL.LOGIN.ESTLOG = 'A'";
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            while (rs.next()) {
                Trabajador t = new Trabajador();
                Persona p = new Persona();

                modelo.setIDLOG(rs.getInt(1));
                t.setIDTRAB(rs.getInt(2));
                modelo.setTIPLOG(rs.getString(3));
                p.setNOMPER(rs.getString(4));

                t.setPersona(p);
                modelo.setTrabajador(t);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return modelo;
    }

    @Override
    public HashSet<Login> listar(Login modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
