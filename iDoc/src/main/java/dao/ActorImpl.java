package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Acta;
import modelo.Actor;
import modelo.Login;
import modelo.Persona;
import modelo.Trabajador;
import modelo.Ubigeo;

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
    public List<Actor> listar() throws Exception {
        List<Actor> listaActor = new ArrayList<>();
        try {
            String sql = "SELECT  "
                    + "acta.IDACTA AS ACTA_ID, "
                    + "acta.TIPACTA AS ACTA_TIP, "
                    + "acta.FECREGACTA AS ACTA_FECREG, "
                    + "acta.FECACT AS ACTA_FECACT, "
                    + "acta.OBSACTA AS ACTA_OBS, "
                    + "acta.ESTACTA AS ACTA_EST, "
                    + "acta.DIRACT AS ACTA_DIRACTA, "
                    + "ubigeoActa.CODUBI AS ACTA_UBIGEO_CODUBI, "
                    + "ubigeoActa.DEPUBI AS ACTA_UBIGEO_DEPUBI, "
                    + "ubigeoActa.PROVUBI AS ACTA_UBIGEO_PROVUBI, "
                    + "ubigeoActa.DISTUBI AS ACTA_UBIGEO_DISTUBI,"
                    + "registrador.IDPER AS ACTA_REGISTRADOR_IDPER,"
                    + "registrador.APEPATPER AS ACTA_REGISTRADOR_APEPATPER, "
                    + "registrador.APEMATPER AS ACTA_REGISTRADOR_APEMATPER, "
                    + "registrador.NOMPER AS ACTA_REGISTRADOR_NOMPER,"
                    + "registrador.DNIPER AS ACTA_REGISTRADOR_DNIPER, "
                    + "titular.IDPER AS ACTA_TITULAR_IDPER, "
                    + "titular.APEPATPER AS ACTA_TITULAR_APEPATPER, "
                    + "titular.APEMATPER AS ACTA_TITULAR_APEMATPER, "
                    + "titular.NOMPER AS ACTA_TITULAR_NOMPER,"
                    + "titular.DNIPER AS ACTA_TITULAR_DNIPER,"
                    + "titular.GENPER AS ACTA_TITULAR_GENPER,"
                    + "personaActor.IDPER AS ACTOR_IDPER, "
                    + "personaActor.APEPATPER AS ACTOR_APEPATPER, "
                    + "personaActor.APEMATPER AS ACTOR_APEMATPER, "
                    + "personaActor.NOMPER AS ACTOR_NOMPER, "
                    + "personaActor.DNIPER AS ACTOR_DNIPER, "
                    + "actor.TIPACT AS ACTOR_TIPACT, "
                    + "actor.IDACT AS ACTOR_IDACT "
                    + "FROM RegCiv.ACTOR actor  "
                    
                    + "INNER JOIN General.PERSONA personaActor \n"
                    + "ON actor.IDPER = personaActor.IDPER \n"
                    + "\n"
                    + "INNER JOIN RegCiv.ACTA acta\n"
                    + "ON actor.IDACTA = acta.IDACTA \n"
                    + "\n"
                    + "INNER JOIN General.PERSONA titular \n"
                    + "ON acta.IDPER = titular.IDPER \n"
                    + "\n"
                    + "INNER JOIN General.UBIGEO ubigeoActa\n"
                    + "ON acta.CODUBI = ubigeoActa.CODUBI\n"
                    + "\n"
                    + "INNER JOIN General.LOGIN login\n"
                    + "ON acta.IDLOG = login.IDLOG \n"
                    + "\n"
                    + "INNER JOIN General.TRABAJADOR trabajador \n"
                    + "ON login.IDTRAB = trabajador.IDTRAB \n"
                    + "\n"
                    + "INNER JOIN General.PERSONA registrador \n"
                    + "ON trabajador.IDPER = registrador.IDPER";
            ResultSet rs = this.conectar().prepareStatement(sql).executeQuery();
            while (rs.next()) {
                Acta acta = new Acta();
                Actor actor = new Actor();
                Ubigeo ubigeo = new Ubigeo();
                Persona registrador = new Persona();
                Login login = new Login();
                Trabajador trabajador = new Trabajador();
                Persona titular = new Persona();
                Persona personaActor = new Persona();
                
                acta.setIDACTA(rs.getInt(1));
                acta.setTIPACTA(rs.getString(2));
                acta.setFECREGACTA(rs.getDate(3));
                acta.setFECACT(rs.getDate(4));
                acta.setOBSACTA(rs.getString(5));
                acta.setESTACTA(rs.getString(6));
                acta.setDIRACT(rs.getString(7));
                
                ubigeo.setCODUBI(rs.getString(8));
                ubigeo.setDEPUBI(rs.getString(9));
                ubigeo.setPROVUBI(rs.getString(10));
                ubigeo.setDISTUBI(rs.getString(11));
                
                registrador.setIDPER(rs.getInt(12));
                registrador.setAPEPATPER(rs.getString(13));
                registrador.setAPEMATPER(rs.getString(14));
                registrador.setNOMPER(rs.getString(15));
                registrador.setDNIPER(rs.getString(16));
                
                titular.setIDPER(rs.getInt(17));
                titular.setAPEPATPER(rs.getString(18));
                titular.setAPEMATPER(rs.getString(19));
                titular.setNOMPER(rs.getString(20));
                titular.setDNIPER(rs.getString(21));
                titular.setGENPER(rs.getString(22));
                
                personaActor.setIDPER(rs.getInt(23));
                personaActor.setAPEPATPER(rs.getString(24));
                personaActor.setAPEMATPER(rs.getString(25));
                personaActor.setNOMPER(rs.getString(26));
                personaActor.setDNIPER(rs.getString(27));
                actor.setTIPACT(rs.getString(28));
                actor.setIDACT(rs.getInt(29));
                
                trabajador.setPersona(registrador);
                login.setTrabajador(trabajador);
                acta.setLogin(login);
                acta.setTitular(titular);
                acta.setUbigeo(ubigeo);
                actor.setActor(personaActor);
                actor.setActa(acta);
                listaActor.add(actor);
            }
            rs.clearWarnings();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return listaActor;
    }
    
    @Override
    public List<Actor> listar(Actor modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Actor obtenerModelo(Actor modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
