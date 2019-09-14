package dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.faces.context.FacesContext;
import modelo.Area;
import modelo.Documento;
import modelo.Transferencia;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class TransferenciaImpl extends Conexion implements ICrud<Transferencia>, IReporte<Transferencia> {

    @Override
    public void registrar(Transferencia trans) throws Exception {
        try {
            String sql = "INSERT INTO TRANSFERENCIA (FECSALTRAN,FECRECTRAN,OBSTRAN,IDDOC,IDARE_EMI,IDARE_REC) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setTimestamp(1, new java.sql.Timestamp(new Date().getTime()));
            ps.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
            ps.setString(3, trans.getOBSTRAN());
            ps.setInt(4, trans.getDocumento().getIDDOC());
            ps.setInt(5, trans.getAreaEmisora().getIDARE());
            ps.setInt(6, trans.getAreaReceptora().getIDARE());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void editar(Transferencia trans) throws Exception {

        try {
            String sql = "UPDATE TRANSFERENCIA SET  FECSALTRAN=?, FECRECTRAN=?, OBSTRAN=?, IDDOC=?, IDARE_EMI=?, IDARE_REC=? WHERE IDTRAN LIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(trans.getFECSALTRAN().getTime()));
            ps.setDate(2, new java.sql.Date(trans.getFECRECTRAN().getTime()));
            ps.setString(3, trans.getOBSTRAN());
            ps.setInt(4, trans.getDocumento().getIDDOC());
            ps.setInt(5, trans.getAreaEmisora().getIDARE());
            ps.setInt(6, trans.getAreaReceptora().getIDARE());
            ps.setInt(7, trans.getIDTRAN());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void eliminar(Transferencia trans) throws Exception {
        try {
            String sql = "UPDATE TRANSFERENCIA SET ESTTRA='I' WHERE IDTRAN LIKE ?";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setInt(1, trans.getIDTRAN());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
    }

    @Override
    public List<Transferencia> listar() throws Exception {
        List<Transferencia> listaTransferencia = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT\n"
                    + "       IDTRAN,\n"
                    + "       FECRECTRAN,\n"
                    + "       FECSALTRAN,\n"
                    + "       OBSTRAN,\n"
                    + "       ESTTRA,\n"
                    + "       D.ASUDOC AS IDDOC,\n"
                    + "       A2.NOMARE AS IDARE_EMI,\n"
                    + "       A.NOMARE AS IDARE_REC\n"
                    + "FROM TRANSFERENCIA T\n"
                    + "INNER JOIN  DOCUMENTO D on T.IDDOC = D.IDDOC\n"
                    + "INNER JOIN AREA A ON T.IDARE_REC = A.IDARE\n"
                    + "INNER JOIN AREA A2 on T.IDARE_EMI = A2.IDARE\n"
                    + "WHERE ESTTRA != 'I' ORDER BY IDTRAN DESC";
            ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            Transferencia trans;
            while (rs.next()) {
                trans = new Transferencia();
                Area areaEmisora = new Area();
                Area areaReceptora = new Area();
                Documento documento = new Documento();
                trans.setIDTRAN(rs.getInt("IDTRAN"));
                trans.setFECRECTRAN(rs.getTimestamp("FECRECTRAN", Calendar.getInstance(TimeZone.getTimeZone("UTC"))));
                trans.setFECSALTRAN(rs.getTimestamp("FECSALTRAN", Calendar.getInstance(TimeZone.getTimeZone("UTC"))));
                trans.setOBSTRAN(rs.getString("OBSTRAN"));
                trans.setESTTRA(rs.getString("ESTTRA"));
                documento.setASUDOC(rs.getString("IDDOC"));
                areaEmisora.setNOMARE(rs.getString("IDARE_EMI"));
                areaReceptora.setNOMARE(rs.getString("IDARE_REC"));

                trans.setAreaEmisora(areaEmisora);
                trans.setAreaReceptora(areaReceptora);
                trans.setDocumento(documento);

                listaTransferencia.add(trans);
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
        return listaTransferencia;

    }

    @Override
    public void generarReporteIndividual(Transferencia modelo) throws Exception {
//        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Transferencia/Transferencia.jasper"));
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
//        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        response.addHeader("Content-disposition", "attachment; filename=Transferencia.pdf");
//        try (ServletOutputStream stream = response.getOutputStream()) {
//            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
//            stream.flush();
//        }
//        FacesContext.getCurrentInstance().responseComplete();
    }

    public void generarReporteTRANS(Map parameters) throws Exception {
//        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Transferencia/Transferencia.jasper"));
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
//        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        response.addHeader("Content-disposition", "attachment; filename=Transferencia.pdf");
//        try (ServletOutputStream stream = response.getOutputStream()) {
//            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
//            stream.flush();
//        }
//        FacesContext.getCurrentInstance().responseComplete();
    }

    @Override
    public List<Transferencia> listar(Transferencia modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Transferencia obtenerModelo(Transferencia modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generarReporteGeneral(Transferencia modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamedContent generarReporteIndividualPrev(Transferencia modelo) throws Exception {
        InputStream inputStream = null;

        Map parameters = new HashMap();

        parameters.put("IDTRAN", modelo.getIDTRAN());

        try {

            ByteArrayOutputStream salida = new ByteArrayOutputStream();
            File jasperReport = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Transferencia/Transferencia".
                    concat(modelo.getESTTRA().equals("1") ? "A" : "R"
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

        return new DefaultStreamedContent(inputStream, "application/pdf", "Transferencia_" + modelo.getESTTRA());
    }

    @Override
    public StreamedContent generarReporteGeneralPrev(Transferencia modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
