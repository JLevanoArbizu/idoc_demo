package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Acta;

import modelo.Incidencia;
import modelo.IncidenciaTipo;
import org.primefaces.model.StreamedContent;

public class IncidenciaImpl extends Conexion implements ICrud<Incidencia>, IReporte<Incidencia> {

    @Override
    public void registrar(Incidencia modelo) throws Exception {
        try {
            String sql = "INSERT INTO INCIDENCIA (IDACTA, IDINCTIP, FECINC, MOTINC, ESTINC) VALUES (?,?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getActa().getIDACTA());
            ps.setInt(2, modelo.getTipoIncidencia().getIDINCTIP());
            ps.setDate(3, new java.sql.Date(modelo.getFECINC().getTime()));
            ps.setString(4, modelo.getMOTINC());
            ps.setString(5, "A");
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
    public void editar(Incidencia modelo) throws Exception {
        try {
            String sql = "UPDATE INCIDENCIA SET IDACTA =?, IDINCTIP =?, FECINC=?, MOTINC=?, ESTINC=? WHERE IDINC=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getActa().getIDACTA());
            ps.setInt(2, modelo.getTipoIncidencia().getIDINCTIP());
            ps.setDate(3, new java.sql.Date(modelo.getFECINC().getTime()));
            ps.setString(4, modelo.getMOTINC());
            ps.setString(5, modelo.getESTINC());
            ps.setInt(6, modelo.getIDINC());
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
    public void eliminar(Incidencia modelo) throws Exception {
        try {
            String sql = "UPDATE INCIDENCIA SET ESTINC=? WHERE IDINC=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, modelo.getIDINC());
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
    public List<Incidencia> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generarReporteIndividual(Incidencia modelo) throws Exception {
//        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Incidencia/Incidencia.jasper"));
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
//        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        response.addHeader("Content-disposition", "attachment; filename=ListaDeIncidencias.pdf");
//        try (ServletOutputStream stream = response.getOutputStream()) {
//            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
//            stream.flush();
//        }
//        FacesContext.getCurrentInstance().responseComplete();
    }

    @Override
    public List<Incidencia> listar(Incidencia modelo) throws Exception {
        List<Incidencia> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT incidencia.IDINC, "
                    + "incidencia.IDACTA, "
                    + "incidencia.IDINCTIP, "
                    + "incidencia.MOTINC, "
                    + "incidencia.FECINC, "
                    + "incidencia.ESTINC, "
                    + "tipo.NOMINCTIP, "
                    + "tipo.LEYINCTIP, "
                    + "tipo.TIPINCTIP "
                    + "FROM INCIDENCIA incidencia "
                    + "INNER JOIN INCIDENCIA_TIPO tipo "
                    + "ON incidencia.IDINCTIP = tipo.IDINCTIP "
                    + "WHERE incidencia.IDACTA=?";
            ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getActa().getIDACTA());
            rs = ps.executeQuery();
            while (rs.next()) {
                Incidencia incidencia = new Incidencia();
                Acta acta = new Acta();
                IncidenciaTipo tipoIncidencia = new IncidenciaTipo();

                incidencia.setIDINC(rs.getInt(1));
                acta.setIDACTA(rs.getInt(2));
                tipoIncidencia.setIDINCTIP(rs.getInt(3));
                incidencia.setMOTINC(rs.getString(4));
                incidencia.setFECINC(rs.getDate(5));
                incidencia.setESTINC(rs.getString(6));
                tipoIncidencia.setNOMINCTIP(rs.getString(7));
                tipoIncidencia.setLEYINCTIP(rs.getString(8));
                tipoIncidencia.setTIPINCTIP(rs.getString(9));

                incidencia.setActa(acta);
                incidencia.setTipoIncidencia(tipoIncidencia);
                lista.add(incidencia);
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
    public Incidencia obtenerModelo(Incidencia modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generarReporteGeneral(Incidencia modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamedContent generarReporteIndividualPrev(Incidencia modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamedContent generarReporteGeneralPrev(Incidencia modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
