package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import modelo.Area;
import modelo.Persona;
import modelo.Trabajador;
import modelo.Ubigeo;

import org.primefaces.model.StreamedContent;

public class TrabajadorImpl extends Conexion implements ICrud<Trabajador>, IReporte<Trabajador> {

    @Override
    public void registrar(Trabajador modelo) throws Exception {
        try {
            String sql = "INSERT INTO GENERAL.TRABAJADOR (IDARE, IDPER, ESTTRAB, FECINITRAB) VALUES (?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
//            ps.setInt(1, Integer.valueOf(modelo.getArea().getIDARE()));
            ps.setInt(1, 2);
            ps.setInt(2, modelo.getPersona().getIDPER());
            ps.setString(3, "A");
            ps.setDate(4, new java.sql.Date(modelo.getFECINITRAB().getTime()));
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
            ps.setDate(1, new java.sql.Date(modelo.getFECINITRAB().getTime()));
            if (modelo.getFECFINTRAB() != null) {
                ps.setDate(2, new java.sql.Date(modelo.getFECFINTRAB().getTime()));
                ps.setString(3, "I");
            } else {
                ps.setNull(2, java.sql.Types.NULL);
                ps.setString(3, "A");
            }
            ps.setInt(4, modelo.getIDTRAB());
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
        List<Trabajador> listaTrabajador = new ArrayList<>();
        try {
            String sql = "SELECT trabajador.IDTRAB, trabajador.IDPER, trabajador.IDARE, trabajador.FECINITRAB, \n"
                    + "trabajador.FECFINTRAB, trabajador.ESTTRAB,\n"
                    + "persona.APEPATPER, persona.APEMATPER, persona.NOMPER, persona.DNIPER, persona.GENPER, persona.DIRPER,\n"
                    + "area.NOMARE, ubigeo.DEPUBI, ubigeo.PROVUBI, ubigeo.DISTUBI\n"
                    + "FROM General.TRABAJADOR trabajador\n"
                    + "INNER JOIN General.PERSONA persona\n"
                    + "ON persona.IDPER = trabajador.IDPER\n"
                    + "INNER JOIN General.UBIGEO ubigeo\n"
                    + "ON ubigeo.CODUBI = persona.CODUBI\n"
                    + "INNER JOIN General.AREA area\n"
                    + "ON area.IDARE = trabajador.IDARE";
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            while (rs.next()) {
                Trabajador trabajador = new Trabajador();
                Ubigeo ubigeo = new Ubigeo();
                Persona persona = new Persona();
                Area area = new Area();
                trabajador.setIDTRAB(rs.getInt(1));
                persona.setIDPER(rs.getInt(2));
                area.setIDARE(rs.getInt(3));
                trabajador.setFECINITRAB(rs.getDate(4));
                trabajador.setFECFINTRAB(rs.getDate(5));
                trabajador.setESTTRAB(rs.getString(6));
                persona.setAPEPATPER(rs.getString(7));
                persona.setAPEMATPER(rs.getString(8));
                persona.setNOMPER(rs.getString(9));
                persona.setDNIPER(rs.getString(10));
                persona.setGENPER(rs.getString(11));
                persona.setDIRPER(rs.getString(12));
                area.setNOMARE(rs.getString(13));
                ubigeo.setDEPUBI(rs.getString(14));
                ubigeo.setPROVUBI(rs.getString(15));
                ubigeo.setDISTUBI(rs.getString(16));
                trabajador.setArea(area);
                persona.setUbigeo(ubigeo);
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
    public void generarReporteIndividual(Trabajador modelo) throws Exception {
//        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Trabajador/Trabajador.jasper"));
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
//        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        response.addHeader("Content-disposition", "attachment; filename=Trabajador.pdf");
//        try (ServletOutputStream stream = response.getOutputStream()) {
//            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
//            stream.flush();
//            stream.close();
//        }
//        FacesContext.getCurrentInstance().responseComplete();
    }

    @Override
    public void generarReporteGeneral(Trabajador modelo) throws Exception {
//        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Trabajador/TrabajadorIndividual.jasper"));
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
//        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        response.reset();
//        response.addHeader("Content-disposition", "attachment; filename=DatosPersonalesTrabajador.pdf");
//        response.setContentType("application/pdf");
//        try (ServletOutputStream stream = response.getOutputStream()) {
//            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
//            stream.flush();
//            stream.close();
//        }
//        FacesContext.getCurrentInstance().responseComplete();
    }

    @Override
    public Trabajador obtenerModelo(Trabajador modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamedContent generarReporteIndividualPrev(Trabajador modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamedContent generarReporteGeneralPrev(Trabajador modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Trabajador> listar(Trabajador modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
