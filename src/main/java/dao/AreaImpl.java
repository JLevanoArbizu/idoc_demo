package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import modelo.Area;
import modelo.Municipalidad;
import org.primefaces.model.StreamedContent;

public class AreaImpl extends Conexion implements ICrud<Area>, IReporte<Area> {

    @Override
    public void registrar(Area modelo) throws Exception {
        try {
            String sql = "INSERT INTO AREA (NOMARE, IDMUN, IDARE_PADR, ESTARE) VALUES (?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getNOMARE());
            ps.setInt(2, 6);
            if (modelo.getAreaPadre().getIDARE() == 0) {
                ps.setNull(3, java.sql.Types.NULL);
            } else {
                ps.setInt(3, modelo.getAreaPadre().getIDARE());
            }
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
    public void editar(Area modelo) throws Exception {
        try {
            String sql = "UPDATE AREA SET NOMARE=?, IDMUN=?, IDARE_PADR=?, ESTARE=? WHERE IDARE=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getNOMARE());
            ps.setInt(2, 6);
            if (modelo.getAreaPadre().getIDARE() == 0) {
                ps.setNull(3, java.sql.Types.NULL);
            } else {
                ps.setInt(3, modelo.getAreaPadre().getIDARE());
            }
            ps.setString(4, String.valueOf(modelo.getESTARE().charAt(0)));
            ps.setInt(5, modelo.getIDARE());
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
    public void eliminar(Area modelo) throws Exception {
        try {
            String sql = "UPDATE AREA SET ESTARE=? WHERE IDARE=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, modelo.getIDARE());
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
    public List<Area> listar() throws Exception {
        List<Area> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT area.IDARE, area.IDARE_PADR, area.IDMUN, area.NOMARE, area.ESTARE, subarea.NOMARE, muni.NOMMUN "
                    + "FROM AREA area "
                    + "LEFT JOIN AREA subarea ON area.IDARE = subarea.IDARE_PADR "
                    + "INNER JOIN MUNICIPALIDAD muni ON area.IDMUN = muni.IDMUN "
                    + "WHERE area.IDARE_PADR IS NULL OR subarea.IDARE IS NOT NULL "
                    + "ORDER BY area.IDARE";
            ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Area area = new Area();
                Municipalidad municipalidad = new Municipalidad();

                area.setIDARE(rs.getInt(1));
                area.areaPadre.setIDARE(rs.getInt(2));
                municipalidad.setIDMUN(rs.getInt(3));
                area.setNOMARE(rs.getString(4));
                area.setESTARE(rs.getString(5));
                area.areaPadre.setNOMARE(rs.getString(6));
                municipalidad.setNOMMUN(rs.getString(7));

                area.setMunicipalidad(municipalidad);
                lista.add(area);
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
    public void generarReporteIndividual(Area modelo) throws Exception {
//        conectar();
//        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Area/Area.jasper"));
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
//        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        response.addHeader("Content-disposition", "attachment; filename=Area.pdf");
//        try (ServletOutputStream stream = response.getOutputStream()) {
//            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
//            stream.flush();
//        }
//        FacesContext.getCurrentInstance().responseComplete();
    }

    @Override
    public void generarReporteGeneral(Area modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamedContent generarReporteIndividualPrev(Area modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamedContent generarReporteGeneralPrev(Area modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Area obtenerModelo(Area modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Area> listar(Area modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
