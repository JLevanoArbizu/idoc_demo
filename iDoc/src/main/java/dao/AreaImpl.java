package dao;

import static dao.Conexion.conectar;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import modelo.Area;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class AreaImpl extends Conexion implements IGenerica<Area> {

    @Override
    public void registrar(Area modelo) throws Exception {
        try {
            String sql = "INSERT INTO GENERAL.AREA (NOMARE, IDMUN, IDARE_PADR, ESTARE) VALUES (?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getNOMARE());
            ps.setInt(2, 6);
            if (modelo.getIDARE_PADR() == null) {
                ps.setNull(3, java.sql.Types.NULL);
            } else {
                ps.setInt(3, Integer.valueOf(modelo.getIDARE_PADR()));
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
            System.out.println(modelo.toString());
            String sql = "UPDATE GENERAL.AREA SET NOMARE=?, IDMUN=?, IDARE_PADR=?, ESTARE=? WHERE IDARE=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getNOMARE());
            ps.setInt(2, 6);
            if (modelo.getIDARE_PADR() == null) {
                ps.setNull(3, java.sql.Types.NULL);
            } else {
                ps.setInt(3, Integer.valueOf(modelo.getIDARE_PADR()));
            }
            ps.setString(4, String.valueOf(modelo.getESTARE().charAt(0)));
            ps.setInt(5, Integer.valueOf(modelo.getIDARE()));
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
            String sql = "UPDATE GENERAL.AREA SET ESTARE=? WHERE IDARE=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, Integer.valueOf(modelo.getIDARE()));
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
        List<Area> lista = null;
        try {
            String sql = "SELECT area.IDARE, area.IDARE_PADR, area.IDMUN, area.NOMARE, area.ESTARE, subarea.NOMARE, muni.NOMMUN "
                    + "FROM General.AREA area "
                    + "LEFT JOIN General.AREA subarea ON area.IDARE = subarea.IDARE_PADR "
                    + "INNER JOIN General.MUNICIPALIDAD muni ON area.IDMUN = muni.IDMUN "
                    + "WHERE area.IDARE_PADR IS NULL OR subarea.IDARE IS NOT NULL "
                    + "ORDER BY area.IDARE";
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            Area area;
            lista = new ArrayList<>();
            while (rs.next()) {
                area = new Area();
                area.setIDARE(String.valueOf(rs.getInt(1)));
                area.setIDARE_PADR(String.valueOf(rs.getInt(2)));
                area.setIDMUN(String.valueOf(rs.getInt(3)));
                area.setNOMARE(rs.getString(4));
                area.setESTARE(rs.getString(5).equals("A") ? "Activo" : "Inactivo");
                area.setNOMARE_PADR(rs.getString(6));
                area.setNOMMUN(rs.getString(7));
                lista.add(area);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return lista;
    }

    @Override
    public List<String> buscar(String campo, List<Area> listaModelo) throws Exception {
        List<String> lista = new ArrayList<>();
        campo = campo.toUpperCase();
        String campoTemp = null;
        for (Area next : listaModelo) {
            if (!next.getNOMARE().equals(campoTemp)) {
                if (next.getNOMARE().startsWith(campo)) {
                    lista.add(next.getNOMARE());
                    campoTemp = next.getNOMARE();
                }
            }
        }
        return lista;
    }

    @Override
    public Area obtenerCodigo(List<Area> listaModelo, Area modelo) throws Exception {
        for (Area area1 : listaModelo) {
            if (area1.getNOMARE().equals(modelo.getNOMARE())) {
                modelo.setIDARE_PADR(area1.getIDARE());
                return modelo;
            }
        }
        return null;
    }

    @Override
    public boolean existe(List<Area> listaModelo, Area modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generarReporte(Map parameters) throws Exception {
        conectar();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes\\Area\\Area.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Area.pdf");
        try (ServletOutputStream stream = response.getOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
        }
        FacesContext.getCurrentInstance().responseComplete();
    }

    @Override
    public void generarReporteIndividual(Map parameters) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

