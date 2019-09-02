package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Dashboard;
import modelo.Documento;
import org.primefaces.model.chart.BarChartModel;

public class DocumentosDashboardImpl extends Conexion implements IDashboard<Dashboard> {

    @Override
    public BarChartModel generarBar(Dashboard modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<Dashboard> listarCantidades() throws Exception {
        List<Dashboard> lista = new ArrayList<>();
        try {
            String sql = "SELECT\n"
                    + "       COUNT(documento.IDDOC) AS cantidadD\n"
                    + "FROM DOCUMENTO documento\n"
                    + "WHERE documento.ESTDOC != 'I'";
            ResultSet rs = this.conectar().prepareStatement(sql).executeQuery();
            while (rs.next()) {
                Dashboard dashboard = new Dashboard();
                Documento documento = new Documento();
                dashboard.setCantidadD(rs.getInt(1));
                dashboard.setDocumento(documento);
                lista.add(dashboard);
            }
            rs.clearWarnings();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("CAMTIDADDDD"+1);
            this.desconectar();
        }
        return lista;
    }
    
    
    @Override
    public BarChartModel generarBar(List<Dashboard> modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Dashboard> listarCantidades(Dashboard modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
