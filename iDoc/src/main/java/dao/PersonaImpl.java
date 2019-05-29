package dao;

import modelo.Persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.text.WordUtils;

public class PersonaImpl extends Conexion implements IGenerica<Persona> {

    @Override
    public void registrar(Persona modelo) throws Exception {
        try {
            String sql = "INSERT INTO GENERAL.PERSONA (APEPATPER, APEMATPER, NOMPER, DNIPER, CODUBI, DIRPER, NACPER, GENPER, ESTPER) "
                    + "VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getAPEPATPER().toUpperCase());
            ps.setString(2, modelo.getAPEMATPER().toUpperCase());
            ps.setString(3, WordUtils.capitalize(modelo.getNOMPER()));
            ps.setString(4, modelo.getDNIPER());
            ps.setString(5, modelo.getCODUBI());
            ps.setString(6, WordUtils.capitalize(modelo.getDIRPER()));
            ps.setString(7, String.valueOf(modelo.getNACPER().charAt(0)));
            ps.setString(8, String.valueOf(modelo.getGENPER().charAt(0)));
            ps.setString(9, "A");
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
    public void editar(Persona modelo) throws Exception {
        try {
            eliminar(modelo);
            registrar(modelo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Persona modelo) throws Exception {
        try {
            String sql = "UPDATE GENERAL.PERSONA SET ESTPER=? WHERE IDPER=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, Integer.valueOf(modelo.getIDPER()));
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
    public List<Persona> listar() throws Exception {
        List<Persona> lista = null;
        try {
            String sql = "SELECT * FROM General.PERSONA ORDER BY IDPER DESC";
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);

            lista = new ArrayList<>();
            Persona persona;

            while (rs.next()) {
                persona = new Persona();
                persona.setIDPER(String.valueOf(rs.getInt("IDPER")));
                persona.setAPEPATPER(rs.getString("APEPATPER"));
                persona.setAPEMATPER(rs.getString("APEMATPER"));
                persona.setNOMPER(rs.getString("NOMPER"));
                persona.setDNIPER(rs.getString("DNIPER"));
                persona.setCODUBI(rs.getString("CODUBI"));
                persona.setDIRPER(rs.getString("DIRPER"));
                persona.setNACPER(rs.getString("NACPER").equals("P") ? "Peruano" : "Extranjero");
                persona.setGENPER(rs.getString("GENPER").equals("M") ? "Masculino" : "Femenino");
                persona.setESTPER(rs.getString("ESTPER"));
                persona.setCOMPLETO(
                        persona.getAPEPATPER() + " "
                        + persona.getAPEMATPER() + ", "
                        + persona.getNOMPER()
                );
                lista.add(persona);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return lista;
    }

    @Override
    public List<String> buscar(String campo, List<Persona> listaModelo) throws Exception {
        List<String> lista = new ArrayList<>();
        campo = campo.toUpperCase();
        for (Persona next : listaModelo) {
            if (next.getCOMPLETO().startsWith(campo)) {
                lista.add(next.getCOMPLETO());
            }
        }
        return lista;
    }

    @Override
    public Persona obtenerCodigo(List<Persona> listaModelo, Persona modelo) throws Exception {
        for (Persona persona1 : listaModelo) {
            if (modelo.getCOMPLETO().equals(persona1.getCOMPLETO()) && persona1.getESTPER().equals("A")) {
                modelo.setIDPER(persona1.getIDPER());
                return modelo;
            }
        }
        return null;
    }

    @Override
    public boolean existe(List<Persona> listaModelo, Persona modelo) throws Exception {
        if (modelo.getDNIPER() != null) {
            for (Persona next : listaModelo) {
                if (next.getDNIPER().equals(modelo.getDNIPER()) && next.getESTPER().equals("A")) {
                    return true;
                }
            }
        }
        return false;
    }

}
