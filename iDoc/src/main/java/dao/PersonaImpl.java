package dao;

import java.io.File;
import modelo.Persona;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.lang3.text.WordUtils;
import org.primefaces.model.StreamedContent;

public class PersonaImpl extends Conexion implements ICrud<Persona>, IReporte<Persona> {

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
            ps.setString(7, "P");
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
    public Persona obtenerModelo(List<Persona> listaModelo, Persona modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existe(List<Persona> listaModelo, Persona modelo) throws Exception {
        if (modelo.getDNIPER() != null) {
            for (Persona next : listaModelo) {
                if (next.getDNIPER().equals(modelo.getDNIPER())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void generarReporteGeneral(Persona modelo) throws Exception {
        conectar();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes\\Ciudadano\\Ciudadano.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Ciudadano.pdf");
        try (ServletOutputStream stream = response.getOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
        }
        FacesContext.getCurrentInstance().responseComplete();
    }

    @Override
    public void generarReporteIndividual(Persona modelo) throws Exception {
        conectar();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Ciudadano/CiudadanoIndividual.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=DatosPersonales.pdf");
        try (ServletOutputStream stream = response.getOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
        }
        FacesContext.getCurrentInstance().responseComplete();
    }

    @Override
    public List<Persona> listar(Persona modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamedContent generarReporteIndividualPrev(Persona modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamedContent generarReporteGeneralPrev(Persona modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
