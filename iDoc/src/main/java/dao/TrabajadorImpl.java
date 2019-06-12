package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Area;
import modelo.Persona;
import modelo.Trabajador;

public class TrabajadorImpl extends Conexion implements IGenerica<Trabajador> {

    @Override
    public void registrar(Trabajador modelo) throws Exception {
        try {
            String sql = "INSERT INTO GENERAL.TRABAJADOR (IDARE, IDPER, ESTTRAB, FECINITRAB) VALUES (?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
//            ps.setInt(1, Integer.valueOf(modelo.getArea().getIDARE()));
            ps.setInt(1, 2);
            ps.setInt(2, Integer.valueOf(modelo.getPersona().getIDPER()));
            ps.setString(3, "A");
            ps.setDate(4, modelo.getFECINITRAB());
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
    public void editar(Trabajador modelo) throws Exception {
        try {
            String sql = "UPDATE GENERAL.TRABAJADOR SET FECINITRAB=?, FECFINTRAB=?, ESTTRAB=? "
                    + "WHERE IDTRAB=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
//            ps.setInt(1, Integer.valueOf(modelo.getArea().getIDARE()));
//            ps.setInt(1, 2);
//            ps.setInt(2, Integer.valueOf(modelo.getPersona().getIDPER()));
//            ps.setString(3, modelo.getESTTRAB());
            ps.setDate(1, modelo.getFECINITRAB());
            if (modelo.getFECFINTRAB() != null) {
                ps.setDate(2, modelo.getFECFINTRAB());
                ps.setString(3, "I");
            } else {
                ps.setNull(2, java.sql.Types.NULL);
                ps.setString(3, "A");
            }
            ps.setInt(4, Integer.valueOf(modelo.getIDTRAB()));
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (Exception e) {
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void eliminar(Trabajador modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Trabajador> listar() throws Exception {
        List<Trabajador> listaTrabajador = null;
        try {
            String sql = "SELECT IDTRAB, IDPER, IDARE, FECINITRAB, FECFINTRAB, ESTTRAB FROM General.TRABAJADOR ";
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            listaTrabajador = new ArrayList<>();
            Trabajador trabajador;
            Persona persona;
            Area area;
            while (rs.next()) {
                trabajador = new Trabajador();
                persona = new Persona();
                area = new Area();
                trabajador.setIDTRAB(String.valueOf(rs.getInt(1)));
                persona.setIDPER(String.valueOf(rs.getInt(2)));
                area.setIDARE(String.valueOf(rs.getInt(3)));
                trabajador.setFECINITRAB_T(rs.getDate(4));
                trabajador.setFECFINTRAB_T(rs.getDate(5));
                trabajador.setESTTRAB(rs.getString(6));
                trabajador.setArea(area);
                trabajador.setPersona(persona);
                listaTrabajador.add(trabajador);

            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
        for (Trabajador trabajador : listaModelo) {
            if (trabajador.getFECINITRAB_T().equals(modelo.getFECINITRAB_T())
                    && trabajador.getPersona().getIDPER().equals(modelo.getPersona().getIDPER())) {
                return trabajador;
            }
        }
        return null;
    }

    @Override
    public boolean existe(List<Trabajador> listaModelo, Trabajador modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
