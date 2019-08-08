package controlador;

import dao.ActaDashboardImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import modelo.Dashboard;

@Named(value = "dashboardC")
@SessionScoped
public class DashboardC implements Serializable {

    Dashboard dashboard;
    List<Dashboard> listaCantidadesActa;
    ActaDashboardImpl daoDashboard;

    public DashboardC() {
        dashboard = new Dashboard();
        daoDashboard = new ActaDashboardImpl();
        listaCantidadesActa = new ArrayList<>();
    }
    
    @PostConstruct
    public void onInit(){
        try {
            listarCantidadesActa();
        } catch (Exception e) {
        }
    }

    public void listarCantidadesActa() throws Exception {
        try {
            listaCantidadesActa = daoDashboard.listarCantidades();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    public List<Dashboard> getListaCantidadesActa() {
        return listaCantidadesActa;
    }

    public void setListaCantidadesActa(List<Dashboard> listaCantidadesActa) {
        this.listaCantidadesActa = listaCantidadesActa;
    }
    
}
