package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import modelo.Area;
import modelo.Documento;
import modelo.Transferencia;
import org.primefaces.model.StreamedContent;

public class TransferenciaImpl extends Conexion implements ICrud<Transferencia>, IReporte<Transferencia> {
    
    @Override
    public void registrar(Transferencia trans) throws Exception {
        try {
            String sql = "INSERT INTO TraDoc.TRANSFERENCIA (FECSALTRAN,FECRECTRAN,OBSTRAN,IDDOC,IDARE_EMI,IDARE_REC) VALUES(CONVERT(DATE,?,105),CONVERT(DATE,?,105),?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(trans.getFECSALTRAN().getTime()));
            ps.setDate(2, new java.sql.Date(trans.getFECRECTRAN().getTime()));
            ps.setString(3, trans.getOBSTRAN());
            ps.setInt(4, trans.getDocumento().getIDDOC());
            ps.setInt(5, trans.getAreaEmisora().getIDARE());
            ps.setInt(6, trans.getAreaReceptora().getIDARE());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }
    
    @Override
    public void editar(Transferencia trans) throws Exception {
        
        try {
            String sql = "UPDATE TraDoc.TRANSFERENCIA SET  FECSALTRAN=?, FECRECTRAN=?, OBSTRAN=?, IDDOC=?, IDARE_EMI=?, IDARE_REC=? WHERE IDTRAN LIKE ?";
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
            throw e;
        } finally {
            this.desconectar();
        }
    }
    
    @Override
    public void eliminar(Transferencia trans) throws Exception {
        try {
            String sql = "UPDATE TraDoc.TRANSFERENCIA SET ESTTRA='I' WHERE IDTRAN LIKE ?";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setInt(1, trans.getIDTRAN());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }
    
    @Override
    public List<Transferencia> listar() throws Exception {
        List<Transferencia> listaTransferencia = new ArrayList<>();
        try {
            String sql = "SELECT * FROM VW_TRANSFERENCIA WHERE ESTTRA != 'I' ORDER BY IDTRAN DESC";
            
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            Transferencia trans;
            while (rs.next()) {
                trans = new Transferencia();
                Area areaEmisora = new Area();
                Area areaReceptora = new Area();
                Documento documento = new Documento();
                trans.setIDTRAN(rs.getInt("IDTRAN"));
                trans.setFECRECTRAN(rs.getDate("FECRECTRAN"));
                trans.setFECSALTRAN(rs.getDate("FECSALTRAN"));
                trans.setOBSTRAN(rs.getString("OBSTRAN"));
                trans.setESTTRA(rs.getString("ESTTRA"));
                documento.setIDDOC(rs.getInt("IDDOC"));
                areaEmisora.setIDARE(rs.getInt("IDARE_EMI"));
                areaReceptora.setIDARE(rs.getInt("IDARE_REC"));
                trans.setAreaEmisora(areaEmisora);
                trans.setAreaReceptora(areaReceptora);
                trans.setDocumento(documento);
                listaTransferencia.add(trans);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamedContent generarReporteGeneralPrev(Transferencia modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
