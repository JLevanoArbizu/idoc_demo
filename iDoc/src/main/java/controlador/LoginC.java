package controlador;

import dao.LoginImpl;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Login;

@Named(value = "LoginC")
@SessionScoped

public class LoginC implements Serializable {

    Login Login = new Login();
    private Login selectedLogin;
    private List<Login> lstLogin;

    @PostConstruct
    public void init() {
        try {
            listarLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void limpiarLogin() throws Exception {
        Login = new Login();
    }

    public void registrarLogin() throws Exception {
        LoginImpl dao;
        try {
            dao = new LoginImpl();
            dao.registrar(Login);
            listarLogin();
            limpiarLogin();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado Correctamente", null));
            listarLogin();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Registrar" + e, null));
        }
    }

    public void editarTupa() throws Exception {
        LoginImpl dao;
        try {
            dao = new LoginImpl();
            dao.editar(selectedLogin);
            listarLogin();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Modificar" + e, null));
        }
    }

    public void eliminarLogin() throws Exception {
        LoginImpl dao;
        try {
            dao = new LoginImpl();
            dao.eliminar(selectedLogin);
            listarLogin();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Eliminar" + e, null));
        }
    }

    public void listarLogin() throws Exception {
        LoginImpl dao;
        try {
            dao = new LoginImpl();
            lstLogin = dao.listar();
        } catch (Exception e) {
            throw e;

        }
    }

    public Login getLogin() {
        return Login;
    }

    public void setLogin(Login Login) {
        this.Login = Login;
    }

    public Login getSelectedLogin() {
        return selectedLogin;
    }

    public void setSelectedLogin(Login selectedLogin) {
        this.selectedLogin = selectedLogin;
    }


    public List<Login> getLstLogin() {
        return lstLogin;
    }

    public void setLstLogin(List<Login> lstLogin) {
        this.lstLogin = lstLogin;
    }

}
