package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import modelo.Area;
import modelo.Login;
import modelo.Persona;
import modelo.Trabajador;
import servicios.EncriptarS;

public class LoginImpl extends Conexion implements ICrud<Login> {

    @Override
    public void registrar(Login modelo) throws Exception {
        try {
            System.out.println("Registrar Login:"+modelo.getTrabajador().getPersona().getDNIPER());
            String sql = "INSERT INTO LOGIN (IDTRAB, USRLOG, PSSWLOG, ESTLOG, TIPLOG) VALUES (?,?,?,?,?)";
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
            //Acordarme hacer join con trabajador para editar mediante el
            String sql = "UPDATE LOGIN SET USRLOG=?, PSSWLOG=? WHERE IDLOG=?";
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
    
    public void resetearContra(Trabajador modelo) throws Exception{
        try {
            String sql = "UPDATE LOGIN SET USRLOG=?, PSSWLOG=? WHERE IDTRAB=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getPersona().getDNIPER());
            ps.setString(2, EncriptarS.encriptarPssw(modelo.getPersona().getDNIPER()));
            ps.setInt(3, modelo.getIDTRAB());
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            this.desconectar();
        }
    }

    public void editarMio(Login modelo) throws Exception {
        try {
            String sql = "UPDATE LOGIN SET PSSWLOG=? WHERE USRLOG=? AND ESTLOG='A'";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, EncriptarS.encriptarPssw(modelo.getPSSWLOG()));
            ps.setString(2, modelo.getUSRLOG());
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
            String sql = "UPDATE LOGIN SET ESTLOG=? WHERE USRLOG=?";
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
    
    public void eliminar(Trabajador modelo) throws Exception {
        try {
            String sql = "UPDATE LOGIN SET ESTLOG=? WHERE IDTRAB=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, modelo.getIDTRAB());
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
    public List<Login> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Login obtenerModelo(Login modelo) throws Exception {
        try {
            String sql = "SELECT login.IDLOG,\n"
                    + "login.IDTRAB,\n"
                    + "login.TIPLOG,\n"
                    + "persona.NOMPER,\n"
                    + "persona.APEPATPER,\n"
                    + "persona.APEMATPER,\n"
                    + "persona.DNIPER,\n"
                    + "area.IDARE,\n"
                    + "area.NOMARE\n"
                    + "FROM LOGIN login\n"
                    + "    INNER JOIN TRABAJADOR trab\n"
                    + "        ON login.IDTRAB = trab.IDTRAB\n"
                    + "	INNER JOIN AREA area\n"
                    + "	ON trab.IDARE = area.IDARE\n"
                    + "    INNER JOIN PERSONA persona\n"
                    + "        ON trab.IDPER = persona.IDPER\n"
                    + "WHERE \n"
                    + "login.USRLOG = ? "
                    + "AND login.PSSWLOG = ? "
                    + "AND  login.ESTLOG = 'A'";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getUSRLOG());
            ps.setString(2, EncriptarS.encriptarPssw(modelo.getPSSWLOG()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Trabajador trabajador = new Trabajador();
                Area area = new Area();
                Persona persona = new Persona();

                modelo.setIDLOG(rs.getInt(1));
                trabajador.setIDTRAB(rs.getInt(2));
                modelo.setTIPLOG(rs.getString(3));
                persona.setNOMPER(rs.getString(4));
                persona.setAPEPATPER(rs.getString(5));
                persona.setAPEMATPER(rs.getString(6));
                persona.setDNIPER(rs.getString(7));
                area.setIDARE(rs.getInt(8));
                area.setNOMARE(rs.getString(9));

                trabajador.setArea(area);
                trabajador.setPersona(persona);
                modelo.setTrabajador(trabajador);
            }
            rs.clearWarnings();
            rs.close();
            ps.clearParameters();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return modelo;
    }

    @Override
    public List<Login> listar(Login modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
