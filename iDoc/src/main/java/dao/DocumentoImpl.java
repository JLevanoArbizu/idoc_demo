package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Documento;
import modelo.Empresa;
import modelo.Login;
import modelo.Persona;
import modelo.Tupa;
import org.primefaces.model.StreamedContent;
import servicios.EncriptarS;

public class DocumentoImpl extends Conexion implements ICrud<Documento>, IReporte<Documento> {

    @Override
    public void registrar(Documento documento) throws Exception {
        try {
            String sql = "INSERT INTO TraDoc.DOCUMENTO (CODDOC,NUMLIBDOC,NUMFOLDOC,TIPDOC,FECDOC,ASUDOC,OBSDOC,IDTUP,IDLOG,IDEMP,IDPER,KEYDOC) VALUES(?,?,?,?,CONVERT(DATE,?,103),?,?,?,?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, EncriptarS.encriptarDocumento(documento.getCODDOC()));
            ps.setString(2, documento.getNUMLIBDOC());
            ps.setString(3, documento.getNUMFOLDOC());
            ps.setString(4, documento.getTIPDOC());
            ps.setDate(5, new java.sql.Date(documento.getFECDOC().getTime()));
            ps.setString(6, documento.getASUDOC());
            ps.setString(7, documento.getOBSDOC());
            ps.setInt(8, documento.getTupa().getIDTUP());
            ps.setInt(9, documento.getLogin().getIDLOG());
            ps.setInt(10, documento.getEmpresa().getIDEMP());
            ps.setInt(11, documento.getPersona().getIDPER());
            ps.setString(12, documento.getKEYDOC());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }

    }

    @Override
    public void editar(Documento documento) throws Exception {

        try {
            String sql = "UPDATE TraDoc.DOCUMENTO SET CODDOC=?, NUMLIBDOC = ?, NUMFOLDOC=?, FECDOC=? , ASUDOC = ? , OBSDOC = ? , IDTUP = ? , IDEMP = ? , IDPER = ? , KEYDOC = ? WHERE IDDOC LIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);

            ps.setString(1, documento.getCODDOC());
            ps.setString(2, documento.getNUMLIBDOC());
            ps.setString(3, documento.getNUMFOLDOC());
            ps.setDate(4, new java.sql.Date(documento.getFECDOC().getTime()));
            ps.setString(5, documento.getASUDOC());
            ps.setString(6, documento.getOBSDOC());
            ps.setInt(7, documento.getTupa().getIDTUP());
            ps.setInt(8, documento.getEmpresa().getIDEMP());
            ps.setInt(9, documento.getPersona().getIDPER());
            ps.setString(10, documento.getKEYDOC());
            ps.setInt(11, documento.getIDDOC());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void eliminar(Documento documento) throws Exception {

        try {
            String sql = "UPDATE TraDoc.DOCUMENTO SET ESTDOC='I' WHERE IDDOC LIKE ?";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setInt(1, documento.getIDDOC());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
    }

    @Override
    public List<Documento> listar() throws Exception {
        List<Documento> listaDocumento = new ArrayList<>();
        ResultSet rs;
        try {
            String sql = "SELECT * FROM TRADOC.DOCUMENTO WHERE  ESTDOC != 'I' ORDER BY IDDOC DESC ";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            rs = ps.executeQuery();
            Documento documento;
            while (rs.next()) {
                documento = new Documento();
                Tupa tupa = new Tupa();
                Login login = new Login();
                Empresa empresa = new Empresa();
                Persona persona = new Persona();
                documento.setIDDOC(rs.getInt("IDDOC"));
                documento.setCODDOC(rs.getString("CODDOC"));
                documento.setNUMLIBDOC(rs.getString("NUMLIBDOC"));
                documento.setNUMFOLDOC(rs.getString("NUMFOLDOC"));
                documento.setTIPDOC(rs.getString("TIPDOC"));
                documento.setFECDOC(rs.getDate("FECDOC"));
                documento.setASUDOC(rs.getString("ASUDOC"));
                documento.setOBSDOC(rs.getString("OBSDOC"));
                documento.setESTDOC(rs.getString("ESTDOC"));
                tupa.setIDTUP(rs.getInt("IDTUP"));
                login.setIDLOG(rs.getInt("IDLOG"));
                empresa.setIDEMP(rs.getInt("IDEMP"));
                persona.setIDPER(rs.getInt("IDPER"));
                documento.setKEYDOC(rs.getString("KEYDOC"));

                documento.setEmpresa(empresa);
                documento.setTupa(tupa);
                documento.setPersona(persona);
                documento.setLogin(login);
                listaDocumento.add(documento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return listaDocumento;
    }

    @Override
    public void generarReporteIndividual(Documento modelo) throws Exception {
//        conectar();
//        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Documento/Documento.jasper"));
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
//        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        response.addHeader("Content-disposition", "attachment; filename=Documento.pdf");
//        try (ServletOutputStream stream = response.getOutputStream()) {
//            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
//            stream.flush();
//        }
//        FacesContext.getCurrentInstance().responseComplete();
    }

    @Override
    public void generarReporteGeneral(Documento modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamedContent generarReporteIndividualPrev(Documento modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamedContent generarReporteGeneralPrev(Documento modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Documento obtenerModelo(Documento modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Documento> listar(Documento modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
