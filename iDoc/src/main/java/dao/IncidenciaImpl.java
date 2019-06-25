package dao;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import modelo.Incidencia;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class IncidenciaImpl extends Conexion implements IGenerica<Incidencia> {

    @Override
    public void registrar(Incidencia modelo) throws Exception {
        try {
            String sql = "INSERT INTO REGCIV.INCIDENCIA (IDACTA, IDINCTIP, FECINC, MOTINC, ESTINC) VALUES (?,?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getIDDOC());
            ps.setString(2, modelo.getIDINCTIP());
            ps.setDate(3, modelo.getFECINC());
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
            String sql = "UPDATE REGCIV.INCIDENCIA SET IDACTA =?, IDINCTIP =?, FECINC=?, MOTINC=?, ESTINC=? WHERE IDINC=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getIDDOC());
            ps.setString(2, modelo.getIDINCTIP());
            ps.setDate(3, modelo.getFECINC());
            ps.setString(4, modelo.getMOTINC());
            ps.setString(5, modelo.getESTINC());
            ps.setInt(6, Integer.valueOf(modelo.getIDINC()));
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
            String sql = "UPDATE REGCIV.INCIDENCIA SET ESTINC=? WHERE IDINC=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, Integer.valueOf(modelo.getIDINC()));
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
        List<Incidencia> lista = null;
        try {
            String sql = "SELECT IDINC,IDACTA,IDINCTIP,MOTINC, FECINC, ESTINC FROM REGCIV.INCIDENCIA ORDER BY IDINC DESC";
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            Incidencia incidencia;
            lista = new ArrayList<>();
            while (rs.next()) {
                incidencia = new Incidencia();
                incidencia.setIDINC(String.valueOf(rs.getInt(1)));
                incidencia.setIDDOC(String.valueOf(rs.getInt(2)));
                incidencia.setIDINCTIP(String.valueOf(rs.getInt(3)));
                incidencia.setMOTINC(rs.getString(4));
                incidencia.setFechaTemporal(rs.getDate(5));
                incidencia.setESTINC(rs.getString(6));
                lista.add(incidencia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return lista;
    }

    @Override
    public List<String> buscar(String campo, List<Incidencia> listaModelo) throws Exception {
        List<String> lista = new ArrayList<>();
        campo = campo.toUpperCase();
        for (Incidencia next : listaModelo) {
            if (next.getIDDOC().startsWith(campo)) {
                lista.add(next.getIDDOC());
            }
        }
        return lista;
    }

    @Override
    public Incidencia obtenerCodigo(List<Incidencia> listaModelo, Incidencia modelo) throws Exception {
        for (Incidencia next : listaModelo) {
            if (next.getIDDOC().equals(modelo.getIDDOC())) {
                modelo.setIDINC(next.getIDINC());
                return modelo;

            }

        }
        return null;
    }

    @Override
    public boolean existe(List<Incidencia> listaModelo, Incidencia modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generarReporte(Map parameters) throws Exception {
        conectar();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Incidencia/Incidencia.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=ListaDeIncidencias.pdf");
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
