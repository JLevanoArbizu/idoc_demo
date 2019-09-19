package dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;

import modelo.Acta;
import modelo.Persona;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import org.apache.commons.lang3.text.WordUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class ActaImpl extends Conexion implements ICrud<Acta>, IReporte<Acta> {

    @Override
    public void registrar(Acta modelo) throws Exception {
        try {
            String sql = "INSERT INTO ACTA "
                    + "(IDMUN, IDLOG, IDPER, NUMLIBACTA, NUMFOLACTA, FECREGACTA, OBSACTA, CODUBI, DIRACT, FECACT, TIPACTA, ESTACTA) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, 1);
            ps.setInt(2, modelo.getLogin().getIDLOG());
            ps.setInt(3, modelo.getTitular().getIDPER());
            ps.setString(4, modelo.getNUMLIBACTA());
            ps.setString(5, modelo.getNUMFOLACTA());
            ps.setObject(6, modelo.getFECREGACTA(), java.sql.Types.DATE);
            ps.setString(7, WordUtils.capitalize(modelo.getOBSACTA()));
            ps.setString(8, modelo.getUbigeo().getCODUBI());
            ps.setString(9, WordUtils.capitalize(modelo.getDIRACT()));
            ps.setObject(10, modelo.getFECACT(), java.sql.Types.DATE);
            ps.setString(11, modelo.getTIPACTA());
            ps.setString(12, "A");
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
    public void editar(Acta modelo) throws Exception {
        try {
            eliminar(modelo);
            registrar(modelo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Acta modelo) throws Exception {
        try {
            String sql = "UPDATE ACTA SET "
                    + "ESTACTA=? "
                    + "WHERE IDACTA=? ";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, modelo.getIDACTA());
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
    public List<Acta> listar() throws Exception {
        List<Acta> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT\n"
                    + "titular.IDPER,\n"
                    + "titular.APEPATPER,\n"
                    + "titular.APEMATPER,\n"
                    + "titular.NOMPER,\n"
                    + "titular.DNIPER,\n"
                    + "\n"
                    + "acta.IDACTA,\n"
                    + "acta.FECREGACTA,\n"
                    + "acta.FECACT,\n"
                    + "acta.TIPACTA,"
                    + "acta.NUMLIBACTA, "
                    + "acta.NUMFOLACTA\n"
                    + "FROM ACTA acta\n"
                    + "INNER JOIN PERSONA titular\n"
                    + "ON acta.IDPER = titular.IDPER\n"
                    + "WHERE acta.ESTACTA = 'A' "
                    + "ORDER BY acta.IDACTA";
            ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Acta acta = new Acta();
                Persona titular = new Persona();

                titular.setIDPER(rs.getInt(1));
                titular.setAPEPATPER(rs.getString(2));
                titular.setAPEMATPER(rs.getString(3));
                titular.setNOMPER(rs.getString(4));
                titular.setDNIPER(rs.getString(5));

                acta.setIDACTA(rs.getInt(6));
                acta.setFECREGACTA(rs.getDate(7));
                acta.setFECACT(rs.getDate(8));
                acta.setTIPACTA(rs.getString(9).trim());
                acta.setNUMLIBACTA(rs.getString(10).trim());
                acta.setNUMFOLACTA(rs.getString(11).trim());

                acta.setTitular(titular);
                lista.add(acta);
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
    public void generarReporteIndividual(Acta modelo) throws Exception {
//        conectar();
//        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Acta/ActaN.jasper"));
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
//        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        response.addHeader("Content-disposition", "attachment; filename=ActaDeNacimiento.pdf");
//        try (ServletOutputStream stream = response.getOutputStream()) {
//            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
//            stream.flush();
//        }
//        FacesContext.getCurrentInstance().responseComplete();
    }

    @Override
    public void generarReporteGeneral(Acta modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamedContent generarReporteIndividualPrev(Acta modelo) throws Exception {
        InputStream inputStream = null;

        Map parameters = new HashMap();

        parameters.put("IDACTA", modelo.getIDACTA());

        try {

            ByteArrayOutputStream salida = new ByteArrayOutputStream();
            File jasperReport = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Acta/Acta".
                    concat(modelo.getTIPACTA().equals("1") ? "N" : modelo.getTIPACTA().equals("2") ? "M" : "D"
                    )
                    + ".jasper"));

            JasperPrint jPrint = JasperFillManager.fillReport(jasperReport.getPath(), parameters, this.conectar());

            JRExporter exporter = new net.sf.jasperreports.engine.export.JRPdfExporter();

            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, salida);

            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jPrint);

            exporter.setParameter(JRPdfExporterParameter.PDF_JAVASCRIPT, "this.print();");

            exporter.exportReport();

            inputStream = new ByteArrayInputStream(salida.toByteArray());

        } catch (JRException e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }

        return new DefaultStreamedContent(inputStream, "application/pdf", "Acta_" + modelo.getTIPACTA() + String.valueOf(modelo.getFECREGACTA()));
    }

    @Override
    public StreamedContent generarReporteGeneralPrev(Acta modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Acta> listar(Acta modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Acta obtenerModelo(Acta modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
