package controlador;

import dao.LoginImpl;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Login;
import modelo.Trabajador;

@Named(value = "LoginC")
@SessionScoped

public class LoginC implements Serializable {
    
    Login loginSesion, login;
    LoginImpl daoLogin;
    
    public LoginC() {
        loginSesion = new Login();
        login = new Login();
        daoLogin = new LoginImpl();
    }
    
    public void registrar(Trabajador trabajador) throws Exception {
        try {
            login.setTrabajador(trabajador);
            login.setTIPLOG(login.getTIPLOG() + loginSesion.getTIPLOG().charAt(1));
            daoLogin.registrar(login);
            login.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void editarOtro(Trabajador trabajador) throws Exception {
        try {
            login.setTrabajador(trabajador);
            daoLogin.editar(login);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void editarMio() throws Exception {
        try {
            daoLogin.editarMio(loginSesion);
            cerrarSesion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Sesiones
    public void iniciarSesion() throws Exception {
        try {
            loginSesion = daoLogin.obtenerModelo(loginSesion);
            if (loginSesion.getIDLOG() != 0 && "A".equals(loginSesion.getESTLOG())) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/iDoc/faces/Pages/Home.xhtml");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void seguridadSesion() throws IOException {
        if (loginSesion.getIDLOG() == 0) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/iDoc/faces/Pages/Login.xhtml");
        }
    }
    
    public void volverHome() throws IOException {
        if (loginSesion.getIDLOG() != 0) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/iDoc/faces/Pages/Home.xhtml");
        }
    }
    
    public void cerrarSesion() throws IOException {
        loginSesion.clear();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/iDoc");
    }

    public Login getLoginSesion() {
        return loginSesion;
    }

    public void setLoginSesion(Login loginSesion) {
        this.loginSesion = loginSesion;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
    
    
}
