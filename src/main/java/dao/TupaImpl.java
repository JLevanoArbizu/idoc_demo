package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Area;

import modelo.Tupa;
import org.primefaces.model.StreamedContent;

public class TupaImpl extends Conexion implements ICrud<Tupa>, IReporte<Tupa> {

    @Override
    public void registrar(Tupa tupa) throws Exception {
        try {
            String sql = "INSERT INTO TUPA(NUMTUP,NOMTUP,PRETUP,PLATUP,IDARE) VALUES(?,?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, tupa.getNUMTUP());
            ps.setString(2, tupa.getNOMTUP());
            ps.setDouble(3, tupa.getPRETUP());
            ps.setString(4, tupa.getPLATUP());
            ps.setInt(5, tupa.getArea().getIDARE());
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
            String sql = "UPDATE TUPA SET  NUMTUP=?, NOMTUP=?, PRETUP=?, PLATUP=?, IDARE=? WHERE IDTUP LIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, tupa.getNUMTUP());
            ps.setString(2, tupa.getNOMTUP());
            ps.setDouble(3, tupa.getPRETUP());
            ps.setString(4, tupa.getPLATUP());
            ps.setInt(5, tupa.getArea().getIDARE());
            ps.setInt(6, tupa.getIDTUP());

            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void eliminar(Tupa tupa) throws Exception {

        try {
            String sql = "UPDATE TUPA SET ESTTUP='I' WHERE NUMTUP LIKE ?";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, tupa.getNUMTUP());
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
    }

    @Override
    public List<Tupa> listar() throws Exception {
        List<Tupa> listaTupa = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT tupa.IDTUP, tupa.NUMTUP, tupa.NOMTUP, tupa.PRETUP, tupa.PLATUP, tupa.IDARE, area.NOMARE\n"
                    + "FROM TUPA tupa\n"
                    + "INNER JOIN AREA\n"
                    + "ON tupa.IDARE = area.IDARE\n"
                    + "where IDTUP != 1 AND ESTTUP != 'I'";
            ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            Tupa tupa;
            while (rs.next()) {
                tupa = new Tupa();
                Area area = new Area();
                tupa.setIDTUP(rs.getInt(1));
                tupa.setNUMTUP(rs.getString(2));
                tupa.setNOMTUP(rs.getString(3));
                tupa.setPRETUP(rs.getDouble(4));
                tupa.setPLATUP(rs.getString(5));
                area.setIDARE(rs.getInt(6));
                area.setNOMARE(rs.getString(7));
                tupa.setArea(area);
                listaTupa.add(tupa);
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
        return listaTupa;
    }

    @Override
    public void generarReporteIndividual(Tupa modelo) throws Exception {
//        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Tupa/Tupa.jasper"));
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
//        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        response.addHeader("Content-disposition", "attachment; filename=ListaDeTupa.pdf");
//        try (ServletOutputStream stream = response.getOutputStream()) {
//            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
//            stream.flush();
//        }
//        FacesContext.getCurrentInstance().responseComplete();
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

    @Override
    public Tupa obtenerModelo(Tupa modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tupa> listar(Tupa modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
