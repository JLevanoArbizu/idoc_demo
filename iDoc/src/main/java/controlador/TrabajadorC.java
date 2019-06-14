package controlador;

import dao.LoginImpl;
import dao.TrabajadorImpl;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import modelo.Login;
import modelo.Persona;
import modelo.Trabajador;

@Named(value = "trabajadorC")
@SessionScoped
public class TrabajadorC extends PersonaC implements Serializable {

    Trabajador trabajador;
    List<Trabajador> listaTrabajador;
    TrabajadorImpl daoTrabajador;

    Login login, loginT;
    LoginImpl daoLogin;

    @ManagedProperty("#{areaC}")
    AreaC areaC;

    public TrabajadorC() throws Exception {
        trabajador = new Trabajador();
        listaTrabajador = new ArrayList<>();
        daoTrabajador = new TrabajadorImpl();

        login = new Login();
        loginT = new Login();
        daoLogin = new LoginImpl();

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
            if (loginT.getIDLOG() != null) {
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
                for (Persona persona1 : listaPersona) {
                    if (trabajador1.getPersona().getIDPER().equals(persona1.getIDPER())) {
                        trabajador1.setPersona(persona1);
                        listaTemp.add(trabajador1);
                    }
                }
            }
            listaTrabajador = listaTemp;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrar() throws Exception {
        try {
            trabajador.setFECINITRAB(new java.sql.Date(trabajador.getFECINITRAB_T().getTime()));
            trabajador.setPersona(obtenerCodigo());
            daoTrabajador.registrar(trabajador);
            listar();
            login.setTrabajador(daoTrabajador.obtenerCodigo(listaTrabajador, trabajador));
            daoLogin.registrar(login);
            trabajador.clear();
            login.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editar() throws Exception {
        try {
            trabajador.setFECINITRAB(new java.sql.Date(trabajador.getFECINITRAB_T().getTime()));
            trabajador.setFECFINTRAB(new java.sql.Date(trabajador.getFECFINTRAB_T().getTime()));
            daoTrabajador.editar(trabajador);
            listar();
            trabajador.clear();
            login.clear();
        } catch (Exception e) {
        }
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

}
