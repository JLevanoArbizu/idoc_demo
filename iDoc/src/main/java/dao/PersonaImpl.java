package dao;

import modelo.Persona;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import modelo.Ubigeo;

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
            ps.setString(5, modelo.getUbigeo().getCODUBI());
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
            ps.setInt(2, modelo.getIDPER());
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
    public HashSet<Persona> listar() throws Exception {
        HashSet<Persona> lista = new HashSet<>();
        try {
            String sql = "SELECT IDPER, APEPATPER, APEMATPER, NOMPER, DNIPER, CODUBI, "
                    + "DIRPER, NACPER, GENPER, ESTPER FROM General.PERSONA";
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);

            while (rs.next()) {
                Persona persona = new Persona();
                Ubigeo ubigeo = new Ubigeo();
                persona.setIDPER(rs.getInt(1));
                persona.setAPEPATPER(rs.getString(2));
                persona.setAPEMATPER(rs.getString(3));
                persona.setNOMPER(rs.getString(4));
                persona.setDNIPER(rs.getString(5));
                ubigeo.setCODUBI(rs.getString(6));
                persona.setDIRPER(rs.getString(7));
                persona.setNACPER(rs.getString(8));
                persona.setGENPER(rs.getString(9));
                persona.setESTPER(rs.getString(10));

                persona.setUbigeo(ubigeo);
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
    public Persona obtenerModelo(Persona modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generarReporteGeneral(Persona modelo) throws Exception {
//        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes\\Ciudadano\\Ciudadano.jasper"));
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
//        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        response.addHeader("Content-disposition", "attachment; filename=Ciudadano.pdf");
//        try (ServletOutputStream stream = response.getOutputStream()) {
//            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
//            stream.flush();
//        }
//        FacesContext.getCurrentInstance().responseComplete();
    }

    @Override
    public void generarReporteIndividual(Persona modelo) throws Exception {
//        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Ciudadano/CiudadanoIndividual.jasper"));
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
//        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        response.addHeader("Content-disposition", "attachment; filename=DatosPersonales.pdf");
//        try (ServletOutputStream stream = response.getOutputStream()) {
//            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
//            stream.flush();
//        }
//        FacesContext.getCurrentInstance().responseComplete();
    }

    @Override
    public HashSet<Persona> listar(Persona modelo) throws Exception {
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
