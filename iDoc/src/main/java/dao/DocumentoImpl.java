package dao;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import modelo.Documento;
import modelo.Empresa;
import modelo.Login;
import modelo.Persona;
import modelo.Trabajador;
import modelo.Tupa;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.primefaces.model.StreamedContent;
import servicios.EncriptarS;

public class DocumentoImpl extends Conexion implements ICrud<Documento>, IReporte<Documento> {

    @Override
    public void registrar(Documento documento) throws Exception {
        try {
            String sql = "INSERT INTO DOCUMENTO (CODDOC,NUMLIBDOC,NUMFOLDOC,TIPDOC,FECDOC,ASUDOC,OBSDOC,IDTUP,IDLOG,IDEMP,IDPER,KEYDOC) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, documento.getCODDOC());
            ps.setString(2, documento.getNUMLIBDOC());
            ps.setString(3, documento.getNUMFOLDOC());
            ps.setString(4, documento.getTIPDOC());
            ps.setTimestamp(5, new java.sql.Timestamp(new Date().getTime()));
            ps.setString(6, documento.getASUDOC());
            ps.setString(7, documento.getOBSDOC());
            ps.setInt(8, documento.getTupa().getIDTUP());
            ps.setInt(9, documento.getLogin().getIDLOG());
            ps.setInt(10, documento.getEmpresa().getIDEMP());
            ps.setInt(11, documento.getPersona().getIDPER());
            ps.setString(12, EncriptarS.encriptarDocumento(String.valueOf(documento.getCODDOC())));
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
            String sql = "UPDATE DOCUMENTO SET CODDOC=?, NUMLIBDOC = ?, NUMFOLDOC=?, ASUDOC = ? , OBSDOC = ? , IDTUP = ? , IDEMP = ? , IDPER = ? , KEYDOC = ? WHERE IDDOC LIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, documento.getCODDOC());
            ps.setString(2, documento.getNUMLIBDOC());
            ps.setString(3, documento.getNUMFOLDOC());
            ps.setString(4, documento.getASUDOC());
            ps.setString(5, documento.getOBSDOC());
            ps.setInt(6, documento.getTupa().getIDTUP());
            ps.setInt(7, documento.getEmpresa().getIDEMP());
            ps.setInt(8, documento.getPersona().getIDPER());
            ps.setString(9, documento.getKEYDOC());
            ps.setInt(10, documento.getIDDOC());
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
            String sql = "UPDATE DOCUMENTO SET ESTDOC='I' WHERE IDDOC LIKE ?";
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
            String sql = "select\n"
                    + "IDDOC,\n"
                    + "CODDOC,\n"
                    + "NUMLIBDOC,\n"
                    + "NUMFOLDOC,\n"
                    + "TIPDOC,\n"
                    + "FECDOC,\n"
                    + "ASUDOC,\n"
                    + "OBSDOC,\n"
                    + "ESTDOC,\n"
                    + "TU.NOMTUP AS IDTUP,\n"
                    + "PER.NOMPER AS IDLOG,\n"
                    + "EMP.RAZSOCEMP AS IDEMP,\n"
                    + "PER2.NOMPER AS IDPER,\n"
                    + "KEYDOC\n"
                    + "\n"
                    + "from DOCUMENTO D\n"
                    + "INNER JOIN TUPA TU ON D.IDTUP = TU.IDTUP\n"
                    + "INNER JOIN LOGIN L ON D.IDLOG = L.IDLOG\n"
                    + "INNER JOIN TRABAJADOR TR ON L.IDTRAB = TR.IDTRAB\n"
                    + "INNER JOIN PERSONA PER ON TR.IDPER = PER.IDPER\n"
                    + "INNER JOIN EMPRESA EMP ON D.IDEMP = EMP.IDEMP\n"
                    + "INNER JOIN PERSONA PER2 ON D.IDPER = PER2.IDPER\n"
                    + "WHERE  ESTDOC != 'I' ORDER BY IDDOC DESC ";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            rs = ps.executeQuery();
            Documento documento;
            while (rs.next()) {
                documento = new Documento();
                Tupa tupa = new Tupa();
                Login login = new Login();
                Trabajador trabajador = new Trabajador();
                Empresa empresa = new Empresa();
                Persona persona = new Persona();
                Persona persona2 = new Persona();
                documento.setIDDOC(rs.getInt("IDDOC"));
                documento.setCODDOC(rs.getInt("CODDOC"));
                documento.setNUMLIBDOC(rs.getString("NUMLIBDOC"));
                documento.setNUMFOLDOC(rs.getString("NUMFOLDOC"));
                documento.setTIPDOC(rs.getString("TIPDOC"));
                documento.setFECDOC(rs.getTimestamp("FECDOC", Calendar.getInstance(TimeZone.getTimeZone("UTC"))));
                documento.setASUDOC(rs.getString("ASUDOC"));
                documento.setOBSDOC(rs.getString("OBSDOC"));
                documento.setESTDOC(rs.getString("ESTDOC"));
                tupa.setNOMTUP(rs.getString("IDTUP"));
                persona2.setNOMPER(rs.getString("IDLOG"));
                empresa.setRAZSOCEMP(rs.getString("IDEMP"));
                persona.setNOMPER(rs.getString("IDPER"));
                documento.setKEYDOC(rs.getString("KEYDOC"));

                documento.setEmpresa(empresa);
                documento.setTupa(tupa);
                documento.setPersona(persona);
                trabajador.setPersona(persona2);
                login.setTrabajador(trabajador);
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

    public void generarReporteIndividual(Map parameters) throws Exception {
        conectar();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Documento/Documento.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Documento.pdf");
        try (ServletOutputStream stream = response.getOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
        }
        FacesContext.getCurrentInstance().responseComplete();
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

    @Override
    public void generarReporteIndividual(Documento modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
