package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Acta;
import modelo.Dashboard;
import org.primefaces.model.chart.BarChartModel;

public class ActaDashboardImpl extends Conexion implements IDashboard<Dashboard> {

    @Override
    public BarChartModel generarBar(Dashboard modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Dashboard> listarCantidades() throws Exception {
        List<Dashboard> lista = new ArrayList<>();
        try {
            String sql = "SELECT COUNT(acta.IDACTA) AS cantidad, acta.TIPACTA FROM RegCiv.ACTA acta\n"
                    + "WHERE acta.ESTACTA = 'A'\n"
                    + "GROUP BY acta.TIPACTA";
            ResultSet rs = this.conectar().prepareStatement(sql).executeQuery();
            while (rs.next()) {
                Dashboard dashboard = new Dashboard();
                Acta acta = new Acta();
                dashboard.setCantidad(rs.getInt(1));
                acta.setTIPACTA(rs.getString(2));
                dashboard.setActa(acta);
                lista.add(dashboard);
            }
            rs.clearWarnings();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
