package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.IncidenciaTipo;
import org.primefaces.model.StreamedContent;

public class IncidenciaTipoImpl extends Conexion implements ICrud<IncidenciaTipo>, IReporte<IncidenciaTipo> {

    @Override
    public void registrar(IncidenciaTipo modelo) throws Exception {
        try {
            String sql = "INSERT INTO INCIDENCIA_TIPO (NOMINCTIP,TIPINCTIP,LEYINCTIP,ESTINCTIP) VALUES (?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getNOMINCTIP());
            ps.setString(2, modelo.getTIPINCTIP());
            ps.setString(3, modelo.getLEYINCTIP());
            ps.setString(4, "A");
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
    public void editar(IncidenciaTipo modelo) throws Exception {
        try {
            String sql = "UPDATE INCIDENCIA_TIPO SET NOMINCTIP=?, TIPINCTIP=?,LEYINCTIP=?,ESTINCTIP=? WHERE IDINCTIP=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getNOMINCTIP());
            ps.setString(2, modelo.getTIPINCTIP());
            ps.setString(3, modelo.getLEYINCTIP());
            ps.setString(4, modelo.getESTINCTIP());
            ps.setInt(5, modelo.getIDINCTIP());
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
    public void eliminar(IncidenciaTipo modelo) throws Exception {
        try {
            String sql = "UPDATE INCIDENCIA_TIPO SET ESTINCTIP=? WHERE IDINCTIP=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, modelo.getIDINCTIP());
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
    public List<IncidenciaTipo> listar() throws Exception {
        List<IncidenciaTipo> lista = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String sql = "SELECT IDINCTIP, NOMINCTIP, TIPINCTIP, LEYINCTIP, ESTINCTIP FROM INCIDENCIA_TIPO";
            ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            IncidenciaTipo incidenciatipo;
            while (rs.next()) {
                incidenciatipo = new IncidenciaTipo();
                incidenciatipo.setIDINCTIP(rs.getInt(1));
                incidenciatipo.setNOMINCTIP(rs.getString(2));
                incidenciatipo.setTIPINCTIP(rs.getString(3));
                incidenciatipo.setLEYINCTIP(rs.getString(4));
                incidenciatipo.setESTINCTIP(rs.getString(5));

                lista.add(incidenciatipo);
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
    public void generarReporteIndividual(IncidenciaTipo modelo) throws Exception {
//        conectar();
//        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/TipoDeIncidencia/TipoDeIncidencia.jasper"));
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
//        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        response.addHeader("Content-disposition", "attachment; filename=TipoDeIncidencia.pdf");
//        try (ServletOutputStream stream = response.getOutputStream()) {
//            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
//            stream.flush();
//        }
//        FacesContext.getCurrentInstance().responseComplete();
    }

    @Override
    public void generarReporteGeneral(IncidenciaTipo modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamedContent generarReporteIndividualPrev(IncidenciaTipo modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamedContent generarReporteGeneralPrev(IncidenciaTipo modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<IncidenciaTipo> listar(IncidenciaTipo modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IncidenciaTipo obtenerModelo(IncidenciaTipo modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
