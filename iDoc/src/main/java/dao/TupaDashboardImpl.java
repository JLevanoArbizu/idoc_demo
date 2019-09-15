package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Dashboard;
import modelo.Tupa;
import org.primefaces.model.chart.BarChartModel;

public class TupaDashboardImpl extends Conexion implements IDashboard<Dashboard>{

    @Override
    public BarChartModel generarBar(Dashboard modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public List<Dashboard> listarCantidades() throws Exception {
            List<Dashboard> lista = new ArrayList<>();
        try {
            String sql = "SELECT COUNT(tupa.IDTUP) AS cantidadTU\n" +
"                    FROM TUPA tupa\n" +
"                    WHERE tupa.ESTTUP != 'I'";
            ResultSet rs = this.conectar().prepareStatement(sql).executeQuery();
            while (rs.next()) {
                Dashboard dashboard = new Dashboard();
                Tupa tupa = new Tupa();
                dashboard.setCantidadTU(rs.getInt(1));
                dashboard.setTupa(tupa);
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
