package controlador;

import dao.LoginImpl;
import dao.TrabajadorImpl;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import modelo.Login;
import modelo.Persona;
import modelo.Trabajador;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;

@Named(value = "trabajadorC")
@SessionScoped
public class TrabajadorC extends PersonaC implements Serializable {

    Trabajador trabajador;
    List<Trabajador> listaTrabajador;
    TrabajadorImpl daoTrabajador;

    Login login, loginT;
    LoginImpl daoLogin;

    private CartesianChartModel combinedModel;

    int contadorTI, contadorTA;

    @ManagedProperty("#{areaC}")
    AreaC areaC;

    public TrabajadorC() throws Exception {
        trabajador = new Trabajador();
        listaTrabajador = new ArrayList<>();
        daoTrabajador = new TrabajadorImpl();

        login = new Login();
        loginT = new Login();
        daoLogin = new LoginImpl();
        combinedModel = new BarChartModel();

    }

    @PostConstruct
    public void init() {
        try {
            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void iniciarSesion() throws Exception {
        try {
            loginT = daoLogin.obtenerLogin(loginT);
            if (loginT.getIDLOG() != null && loginT.getESTLOG() == "A") {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/iDoc/faces/Pages/Home.xhtml");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void seguridadSesion() throws IOException {
        if (loginT.getIDLOG() == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/iDoc/faces/Pages/Login.xhtml");
        }
    }

    public void volverHome() throws IOException {
        if (loginT.getIDLOG() != null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/iDoc/faces/Pages/Home.xhtml");
        }
    }

    public void cerrarSesion() throws IOException {
        loginT.clear();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/iDoc");
    }

    public void listar() throws Exception {
        try {
            listaTrabajador = daoTrabajador.listar();
            List<Trabajador> listaTemp = new ArrayList<>();
            for (Trabajador trabajador1 : listaTrabajador) {
                if (trabajador1.getESTTRAB().equals("A")){
                    System.out.println(trabajador1.toString());
                    contadorTA++;
                }else{
                    contadorTI++;
                }
                for (Persona persona1 : listaPersona) {
                    if (trabajador1.getPersona().getIDPER().equals(persona1.getIDPER())) {
                        trabajador1.setPersona(persona1);
                        listaTemp.add(trabajador1);
                    }
                }
            }
            listaTrabajador = listaTemp;
            createCombinedModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrar() throws Exception {
        try {
            trabajador.setFECINITRAB(new java.sql.Date(trabajador.getFECINITRAB_T().getTime()));
            trabajador.setPersona(obtenerCodigo());
            if (daoTrabajador.existe(listaTrabajador, trabajador)) {
                return;
            }
            daoTrabajador.registrar(trabajador);
            listar();
            login.setTrabajador(daoTrabajador.obtenerCodigo(listaTrabajador, trabajador));
            daoLogin.registrar(login);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Exitoso.", null));
            trabajador.clear();
            login.clear();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!.", null));
            e.printStackTrace();
        }
    }

    public void editar() throws Exception {
        try {
            trabajador.setFECINITRAB(new java.sql.Date(trabajador.getFECINITRAB_T().getTime()));
            trabajador.setFECFINTRAB(new java.sql.Date(trabajador.getFECFINTRAB_T().getTime()));
            daoTrabajador.editar(trabajador);
            listar();
            login.setTrabajador(daoTrabajador.obtenerCodigo(listaTrabajador, trabajador));
            daoLogin.eliminar(login);
            trabajador.clear();
            login.clear();
        } catch (Exception e) {
        }
    }

    public void resetearContra(Trabajador trab) throws Exception {
        try {
            login.setTrabajador(daoTrabajador.obtenerCodigo(listaTrabajador, trab));
            daoLogin.editar2(login);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Reseteo Exitoso.", null));
            login.clear();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Reseteo Fallido.", null));
            e.printStackTrace();
        }
    }

    public void editarLogin() throws Exception {
        try {
            daoLogin.editar(loginT);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Edición de contraseña Exitoso.", null));
            cerrarSesion();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Edición de contraseña Fallido.", null));
            e.printStackTrace();
        }
    }

    public void generarReporte(Trabajador IDTRAB)throws  Exception{
        TrabajadorImpl reportAre = new TrabajadorImpl();
        try {
            Map<String, Object> parameters = new HashMap(); // Libro de parametros
            parameters.put(null, IDTRAB); //Insertamos un parametro
            reportAre.generarReporte(parameters); //Pido exportar Reporte con los parametros
//            report.exportarPDF2(parameters);
        } catch (Exception e) {
            throw e;
        }
    }

    private void createCombinedModel() {

        BarChartSeries year = new BarChartSeries();
        year.setLabel("Años");

        year.set("2004", 20);
        year.set("2005", 30);
        year.set("2006", 40);
        year.set("2007", 50);
        year.set("2008", 60);

        LineChartSeries activos = new LineChartSeries();
        activos.setLabel("Activos");

        activos.set("2004", 15);
        activos.set("2005", 23);
        activos.set("2006", 25);
        activos.set("2007", 30);
        activos.set("2008", 40);


        LineChartSeries inactivos = new LineChartSeries();
        inactivos.setLabel("Inactivos");

        inactivos.set("2004", 4);
        inactivos.set("2005", 3);
        inactivos.set("2006", 2);
        inactivos.set("2007", 5);
        inactivos.set("2008", 10);


        combinedModel.addSeries(year);
        combinedModel.addSeries(inactivos);
        combinedModel.addSeries(activos);


        combinedModel.setTitle("Lista Trabajadores");
        combinedModel.setLegendPosition("ne");
        combinedModel.setMouseoverHighlight(false);
        combinedModel.setShowDatatip(false);
        combinedModel.setShowPointLabels(true);
        Axis yAxis = combinedModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(100);
    }

    public AreaC getAreaC() {
        return areaC;
    }

    public void setAreaC(AreaC areaC) {
        this.areaC = areaC;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public List<Trabajador> getListaTrabajador() {
        return listaTrabajador;
    }

    public void setListaTrabajador(List<Trabajador> listaTrabajador) {
        this.listaTrabajador = listaTrabajador;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Login getLoginT() {
        return loginT;
    }

    public void setLoginT(Login loginT) {
        this.loginT = loginT;
    }

    public CartesianChartModel getCombinedModel() {
        return combinedModel;
    }

    public void setCombinedModel(CartesianChartModel combinedModel) {
        this.combinedModel = combinedModel;
    }

}
