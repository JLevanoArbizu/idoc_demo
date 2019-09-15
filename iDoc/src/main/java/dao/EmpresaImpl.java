package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Empresa;
import org.primefaces.model.StreamedContent;

public class EmpresaImpl extends Conexion implements ICrud<Empresa>, IReporte<Empresa> {

    @Override
    public void registrar(Empresa empresa) throws Exception {
        try {
            String sql = "INSERT INTO EMPRESA(RAZSOCEMP,RUCEMP,DIREMP) VALUES(?,?,?)";
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
            String sql = "UPDATE EMPRESA SET RAZSOCEMP=?, RUCEMP=?, DIREMP=? WHERE IDEMP LIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);

            ps.setString(1, empresa.getRAZSOCEMP());
            ps.setString(2, empresa.getRUCEMP());
            ps.setString(3, empresa.getDIREMP());
            ps.setInt(4, empresa.getIDEMP());
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
            String sql = "UPDATE EMPRESA SET ESTEMP = 'I' WHERE IDEMP LIKE ?";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setInt(1, empresa.getIDEMP());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public List<Empresa> listar() throws Exception {
        List<Empresa> listadoEmpresa = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM EMPRESA WHERE ESTEMP != 'I' and IDEMP != '1' ORDER BY IDEMP DESC";
            ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            Empresa empresa;
            while (rs.next()) {
                empresa = new Empresa();
                empresa.setIDEMP(rs.getInt("IDEMP"));
                empresa.setRAZSOCEMP(rs.getString("RAZSOCEMP"));
                empresa.setRUCEMP(rs.getString("RUCEMP"));
                empresa.setDIREMP(rs.getString("DIREMP"));
                empresa.setESTEMP(rs.getString("ESTEMP"));
                listadoEmpresa.add(empresa);
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
        return listadoEmpresa;
    }

    @Override
    public void generarReporteIndividual(Empresa modelo) throws Exception {
//        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes\\Empresa\\Empresa.jasper"));
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
//        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        response.addHeader("Content-disposition", "attachment; filename=ListaDeEmpresas.pdf");
//        try (ServletOutputStream stream = response.getOutputStream()) {
//            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
//            stream.flush();
//        }
//        FacesContext.getCurrentInstance().responseComplete();
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

    @Override
    public Empresa obtenerModelo(Empresa modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Empresa> listar(Empresa modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
