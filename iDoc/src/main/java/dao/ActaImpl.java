package dao;

import static dao.Conexion.conectar;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import modelo.Acta;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.commons.lang3.text.WordUtils;

public class ActaImpl extends Conexion implements IGenerica<Acta> {

    @Override
    public void registrar(Acta modelo) throws Exception {
        try {
            String sql = "INSERT INTO REGCIV.ACTA "
                    + "(IDMUN, IDLOG, IDPER, NUMLIBACTA, NUMFOLACTA, FECREGACTA, OBSACTA, CODUBI, DIRACT, FECACT, TIPACTA, ESTACTA) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, 1);
            ps.setInt(2, Integer.valueOf(modelo.getIDLOG()));
            ps.setInt(3, Integer.valueOf(modelo.getIDPER()));
            ps.setString(4, modelo.getNUMLIBACTA());
            ps.setString(5, modelo.getNUMFOLACTA());
            ps.setObject(6, modelo.getFECREGACTA(), java.sql.Types.DATE);
            ps.setString(7, WordUtils.capitalize(modelo.getOBSACTA()));
            ps.setString(8, modelo.getCODUBI());
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
            String sql = "UPDATE REGCIV.ACTA SET "
                    + "ESTACTA=? "
                    + "WHERE IDACTA=? ";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, Integer.valueOf(modelo.getIDACTA()));
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
        List<Acta> lista = null;
        try {
            String sql = "SELECT "
                    + "D.IDACTA, "
                    + "D.IDMUN, "
                    + "D.IDPER, "
                    + "CONCAT(personaDocumento.APEPATPER, ' ',personaDocumento.APEMATPER, ', ',personaDocumento.NOMPER ) AS datosTitular, "
                    + "D.NUMLIBACTA, "
                    + "D.NUMFOLACTA, "
                    + "D.FECREGACTA, "
                    + "D.OBSACTA, "
                    + "D.CODUBI, "
                    + "D.DIRACT, "
                    + "D.FECACT, "
                    + "D.TIPACTA, "
                    + "D.ESTACTA, "
                    + "A.IDACT, "
                    + "A.IDACTA,	"
                    + "A.IDPER,	"
                    + "CONCAT(personaActor.APEPATPER, ' ',personaActor.APEMATPER, ', ',personaActor.NOMPER ) AS datosActor, "
                    + "A.TIPACT "
                    + "FROM RegCiv.ACTOR AS A "
                    + "INNER JOIN General.PERSONA AS personaActor "
                    + "ON A.IDPER = personaActor.IDPER "
                    + "FULL OUTER JOIN RegCiv.ACTA AS D "
                    + "ON A.IDACTA = D.IDACTA "
                    + "INNER JOIN General.PERSONA AS personaDocumento "
                    + "ON D.IDPER = personaDocumento.IDPER ";
            Acta documento;
            lista = new ArrayList<>();
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);

            while (rs.next()) {
                documento = new Acta();

                documento.setIDACTA(String.valueOf(rs.getInt(1)));
                documento.setIDMUN(String.valueOf(rs.getInt(2)));
                documento.setIDPER(String.valueOf(rs.getInt(3)));
                documento.setTitular(rs.getString(4));
                documento.setNUMLIBACTA(rs.getString(5));
                documento.setNUMFOLACTA(rs.getString(6));
                documento.setFECREGACTA((rs.getDate(7)));
                documento.setOBSACTA(rs.getString(8));
                documento.setCODUBI(rs.getString(9));
                documento.setDIRACT(rs.getString(10));
                documento.setFECACT((rs.getDate(11)));
                documento.setTIPACTA(rs.getString(12));
                documento.setESTACTA(rs.getString(13));
                String tipoActor = rs.getString(18);
                if (tipoActor != null) {
                    switch (tipoActor) {
                        case "1":
                            documento.setEsposa(rs.getString(17));
                            break;
                        case "2":
                            documento.setDeclarante(rs.getString(17));
                            break;
                        case "3":
                            documento.setPapa(rs.getString(17));
                            break;
                        case "4":
                            documento.setMama(rs.getString(17));
                            break;
                        case "5":
                            documento.setCelebrante(rs.getString(17));
                            break;
                        case "6":
                            documento.setMedico(rs.getString(17));
                            break;
                        default:
                            break;
                    }
                }
                lista.add(documento);
            }
            rs.clearWarnings();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return lista;
    }

    @Override
    public List<String> buscar(String campo, List<Acta> listaModelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Acta obtenerCodigo(List<Acta> listaModelo, Acta modelo) throws Exception {
        modelo.setFECREGACTA(modelo.getFECREGACTA());
        for (Acta documento1 : listaModelo) {
            if (modelo.getFECREGACTA().equals(documento1.getFECREGACTA())
                    && modelo.getIDPER().equals(documento1.getIDPER())
                    && modelo.getTIPACTA().equals(documento1.getTIPACTA())) {
                modelo.setIDACTA(documento1.getIDACTA());
            }
        }
        return modelo;

    }

    @Override
    public boolean existe(List<Acta> listaModelo, Acta modelo) throws Exception {
        for (Acta documento : listaModelo) {
            if (documento.getTitular().equalsIgnoreCase(modelo.getTitular())) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ya existe un titular en este Tipo de Acta.", null));
                return true;
            } else if (documento.getNUMLIBACTA().equals(modelo.getNUMLIBACTA())
                    && documento.getNUMFOLACTA().equals(modelo.getNUMFOLACTA())) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ya existe un Libro y Folio con los mismos números.", null));
                return true;
            }
        }
        return false;
    }

    @Override
    public void generarReporte(Map parameters) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public void generarReporteIndividual(Map parameters) throws Exception {
        conectar();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes\\Acta\\ActaN.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=ActaDeNacimiento.pdf");
        try (ServletOutputStream stream = response.getOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
        }
        FacesContext.getCurrentInstance().responseComplete();
    }

}
