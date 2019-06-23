package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Login;
import modelo.Trabajador;

public class LoginImpl extends Conexion implements IGenerica<Login> {

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
            ps.setString(2, modelo.getPSSWLOG());
            ps.setInt(3, Integer.valueOf(modelo.getIDLOG()));
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.desconectar();
        }
    }
    public void editar2(Login modelo) throws Exception {
        try {
            System.out.println("Editar2 "+modelo.toString());
            String sql = "UPDATE GENERAL.LOGIN SET PSSWLOG=? WHERE USRLOG=? AND ESTLOG='A'";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getTrabajador().getPersona().getDNIPER());
            ps.setString(2, modelo.getTrabajador().getPersona().getDNIPER());
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
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
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.desconectar();
        }
    }

    @Override
    public List<Login> listar() throws Exception {
       List<Login> listaLogin;
        ResultSet rs;
        try {
            this.conectar();
//          String sql = "SELECT IDTUP,NOMTUP,PRETUP,FORMAT(FECTUP,'dd/MM/yyyy','en-gb') AS FECTUP,ARETUP FROM TUPA ";
            String sql = "SELECT *  FROM TraDoc.TUPA ";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            rs = ps.executeQuery();
            listaLogin = new ArrayList();
            Login tupa;
            while (rs.next()) {
                tupa = new Login();
                tupa.setIDLOG(rs.getString("IDLOG"));
                tupa.setIDTRAB(rs.getString("IDTRAB"));
                tupa.setUSRLOG(rs.getString("USRLOG"));
                tupa.setPSSWLOG(rs.getString("PSSWLOG"));
                tupa.setESTLOG(rs.getString("ESTLOG"));
                tupa.setTIPLOG(rs.getString("TIPLOG"));
                listaLogin.add(tupa);
            }
            return listaLogin;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }}

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



    public Login obtenerLogin(Login login) throws Exception {
        try {
            String sql = "SELECT IDLOG, IDTRAB, TIPLOG FROM GENERAL.LOGIN "
                    + "WHERE USRLOG='" + login.getUSRLOG() + "' AND PSSWLOG='" + login.getPSSWLOG() + "' AND ESTLOG = 'A'";
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            while (rs.next()) {
                Trabajador t = new Trabajador();
                login.setIDLOG(String.valueOf(rs.getInt(1)));
                t.setIDTRAB(String.valueOf(rs.getInt(2)));
                login.setTrabajador(t);
                login.setTIPLOG(rs.getString(3));
            }
            rs.close();
            if (login.getIDLOG() != null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito" + login.getIDLOG(), null));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario y/o contraseña incorrectos.", null));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return login;
    }

    @Override
    public void generarReporte(Map parameters) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generarReporteIndividual(Map parameters) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
