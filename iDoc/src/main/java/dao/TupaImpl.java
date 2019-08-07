package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Tupa;
import org.primefaces.model.StreamedContent;

public class TupaImpl extends Conexion implements ICrud<Tupa>, IReporte<Tupa> {

    @Override
    public void registrar(Tupa tupa) throws Exception {
        try {
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
        List<Tupa> listaTupa = new ArrayList<>();
        ResultSet rs;
        try {
//          String sql = "SELECT IDTUP,NOMTUP,PRETUP,FORMAT(FECTUP,'dd/MM/yyyy','en-gb') AS FECTUP,ARETUP FROM TUPA ";
            String sql = "SELECT *  FROM TraDoc.TUPA where IDTUP != 1 AND ESTTUP != 'I' ";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            rs = ps.executeQuery();
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
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            this.desconectar();
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
