package controlador;

import dao.ActaDashboardImpl;
import dao.DocumentosDashboardImpl;
import dao.TupaDashboardImpl;
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
//    ===================
    List<Dashboard> listaCantidadesActa;
    ActaDashboardImpl daoDashboard;
// =====DOC=====
    List<Dashboard> listaCantidadesDocumento;
    DocumentosDashboardImpl daoDashboardD;
//=====TUPA=======
    List<Dashboard> listaCantidadesTupa;
    TupaDashboardImpl daoDashboardTU;

    public DashboardC() {
        dashboard = new Dashboard();
        daoDashboard = new ActaDashboardImpl();
        listaCantidadesActa = new ArrayList<>();

        daoDashboardD = new DocumentosDashboardImpl();
        listaCantidadesDocumento = new ArrayList<>();

        daoDashboardTU = new TupaDashboardImpl();
        listaCantidadesTupa = new ArrayList<>();

    }

    @PostConstruct
    public void onInit() {
        try {
            listarCantidadesActa();
            listarCantidadesDocumento();
            listarCantidadesTupa();
        } catch (Exception e) {
        }
    }

    public void listarCantidadesTupa() throws Exception {
        try {
            listaCantidadesTupa = daoDashboardTU.listarCantidades();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarCantidadesActa() throws Exception {
        try {
            listaCantidadesActa = daoDashboard.listarCantidades();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarCantidadesDocumento() throws Exception {
        try {
            listaCantidadesDocumento = daoDashboardD.listarCantidades();
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

    public List<Dashboard> getListaCantidadesDocumento() {
        return listaCantidadesDocumento;
    }

    public void setListaCantidadesDocumento(List<Dashboard> listaCantidadesDocumento) {
        this.listaCantidadesDocumento = listaCantidadesDocumento;
    }

    public DocumentosDashboardImpl getDaoDashboardD() {
        return daoDashboardD;
    }

    public void setDaoDashboardD(DocumentosDashboardImpl daoDashboardD) {
        this.daoDashboardD = daoDashboardD;
    }

    public List<Dashboard> getListaCantidadesTupa() {
        return listaCantidadesTupa;
    }

    public void setListaCantidadesTupa(List<Dashboard> listaCantidadesTupa) {
        this.listaCantidadesTupa = listaCantidadesTupa;
    }

    public TupaDashboardImpl getDaoDashboardTU() {
        return daoDashboardTU;
    }

    public void setDaoDashboardTU(TupaDashboardImpl daoDashboardTU) {
        this.daoDashboardTU = daoDashboardTU;
    }

    
}
