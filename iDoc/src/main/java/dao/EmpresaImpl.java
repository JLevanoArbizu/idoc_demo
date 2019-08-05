package dao;

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

import modelo.Empresa;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.primefaces.model.StreamedContent;

public class EmpresaImpl extends Conexion implements ICrud<Empresa>, IReporte<Empresa> {

    @Override
    public void registrar(Empresa empresa) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO TraDoc.EMPRESA(RAZSOCEMP,RUCEMP,DIREMP) VALUES(?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, empresa.getRAZSOCEMP());
            ps.setString(2, empresa.getRUCEMP());
            ps.setString(3, empresa.getDIREMP());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void editar(Empresa empresa) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE TraDoc.EMPRESA SET RAZSOCEMP=?, RUCEMP=?, DIREMP=? WHERE IDEMP LIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);

            ps.setString(1, empresa.getRAZSOCEMP());
            ps.setString(2, empresa.getRUCEMP());
            ps.setString(3, empresa.getDIREMP());
            ps.setString(4, empresa.getIDEMP());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void eliminar(Empresa empresa) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE TraDoc.EMPRESA SET ESTEMP = 'I' WHERE IDEMP LIKE ?";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, empresa.getIDEMP());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public List<Empresa> listar() throws Exception {
        List<Empresa> listadoEmpresa;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT * FROM TraDoc.EMPRESA WHERE ESTEMP LIKE 'A' and IDEMP != '1' ORDER BY IDEMP DESC";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            rs = ps.executeQuery();
            listadoEmpresa = new ArrayList();
            Empresa empresa;
            while (rs.next()) {
                empresa = new Empresa();
                empresa.setIDEMP(rs.getString("IDEMP"));
                empresa.setRAZSOCEMP(rs.getString("RAZSOCEMP"));
                empresa.setRUCEMP(rs.getString("RUCEMP"));
                empresa.setDIREMP(rs.getString("DIREMP"));
                empresa.setESTEMP(rs.getString("ESTEMP"));
//                empresa.setUBIGEO(rs.getString("UBIGEO"));
//                empresa.setCODUBI(rs.getString("CODUBI"));
                listadoEmpresa.add(empresa);
            }
            return listadoEmpresa;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public boolean existe(List<Empresa> listaModelo, Empresa modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generarReporteIndividual(Empresa modelo) throws Exception {
        conectar();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes\\Empresa\\Empresa.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=ListaDeEmpresas.pdf");
        try (ServletOutputStream stream = response.getOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
        }
        FacesContext.getCurrentInstance().responseComplete();
    }

    @Override
    public List<Empresa> listar(Empresa modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Empresa obtenerModelo(List<Empresa> listaModelo, Empresa modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generarReporteGeneral(Empresa modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamedContent generarReporteIndividualPrev(Empresa modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamedContent generarReporteGeneralPrev(Empresa modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
