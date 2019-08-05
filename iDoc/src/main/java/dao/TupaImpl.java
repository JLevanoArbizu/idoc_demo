package dao;

//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
import static dao.Conexion.conectar;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import modelo.Tupa;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.primefaces.model.StreamedContent;

public class TupaImpl extends Conexion implements ICrud<Tupa>, IReporte<Tupa> {
    
    @Override
    public void registrar(Tupa tupa) throws Exception {
        try {
//            this.conectar();
            String sql = "INSERT INTO TraDoc.TUPA(NUMTUP,NOMTUP,PRETUP,PLATUP,ARETUP) VALUES(?,?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, tupa.getNUMTUP());
            ps.setString(2, tupa.getNOMTUP());
            ps.setDouble(3, tupa.getPRETUP());
            ps.setString(4, tupa.getPLATUP());
            ps.setString(5, tupa.getARETUP());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }
    
    @Override
    public void editar(Tupa tupa) throws Exception {
        try {
//            this.conectar();
            String sql = "UPDATE TraDoc.TUPA SET  NUMTUP=?, NOMTUP=?, PRETUP=?, PLATUP=?, ARETUP=? WHERE IDTUP LIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, tupa.getNUMTUP());
            ps.setString(2, tupa.getNOMTUP());
            ps.setDouble(3, tupa.getPRETUP());
            ps.setString(4, tupa.getPLATUP());
            ps.setString(5, tupa.getARETUP());
            ps.setString(6, tupa.getIDTUP());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
            
        } finally {
            this.desconectar();
        }
    }
    
    @Override
    public void eliminar(Tupa tupa) throws Exception {
        
        try {
            this.conectar();
            String sql = "UPDATE TraDoc.TUPA SET ESTTUP='I' WHERE NUMTUP LIKE ?";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, tupa.getNUMTUP());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }
    
    @Override
    public List<Tupa> listar() throws Exception {
        List<Tupa> listaTupa;
        ResultSet rs;
        try {
            this.conectar();
//          String sql = "SELECT IDTUP,NOMTUP,PRETUP,FORMAT(FECTUP,'dd/MM/yyyy','en-gb') AS FECTUP,ARETUP FROM TUPA ";
            String sql = "SELECT *  FROM TraDoc.TUPA where IDTUP != 1 AND ESTTUP != 'I' ";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            rs = ps.executeQuery();
            listaTupa = new ArrayList();
            Tupa tupa;
            while (rs.next()) {
                tupa = new Tupa();
                tupa.setIDTUP(rs.getString("IDTUP"));
                tupa.setNUMTUP(rs.getString("NUMTUP"));
                tupa.setNOMTUP(rs.getString("NOMTUP"));
                tupa.setPRETUP(rs.getDouble("PRETUP"));
                tupa.setPLATUP(rs.getString("PLATUP"));
                tupa.setARETUP(rs.getString("ARETUP"));
                listaTupa.add(tupa);
            }
            return listaTupa;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            this.desconectar();
        }
    }
    
    @Override
    public boolean existe(List<Tupa> listaModelo, Tupa modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void generarReporteIndividual(Tupa modelo) throws Exception {
        conectar();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Tupa/Tupa.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=ListaDeTupa.pdf");
        try (ServletOutputStream stream = response.getOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
        }
        FacesContext.getCurrentInstance().responseComplete();
    }
    
    @Override
    public List<Tupa> listar(Tupa modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Tupa obtenerModelo(List<Tupa> listaModelo, Tupa modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generarReporteGeneral(Tupa modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamedContent generarReporteIndividualPrev(Tupa modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamedContent generarReporteGeneralPrev(Tupa modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
