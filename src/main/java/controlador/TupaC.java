package controlador;

import dao.TupaImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Tupa;
import org.primefaces.PrimeFaces;

@Named(value = "tupaC")
@SessionScoped
public class TupaC implements Serializable {

    Tupa tupa;
    Tupa selectedTupa;
    List<Tupa> lstTupa, filtrado;
    TupaImpl dao;

    public TupaC() {
        tupa = new Tupa();
        lstTupa = new ArrayList<>();
        filtrado = new ArrayList<>();
        selectedTupa = new Tupa();
        dao = new TupaImpl();
    }

    @PostConstruct
    public void iniciar() {
        try {
            listarTupa();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void limpiarTupa() throws Exception {
        tupa = new Tupa();
    }

    public void registrarTupa() throws Exception {
        try {
            dao.registrar(tupa);
//            listarTupa();
            limpiarTupa();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado Correctamente", null));
            PrimeFaces.current().executeScript("enviar('" + "Tupa" + "')");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Registrar" + e, null));
        }
    }

    public void editarTupa() throws Exception {
        try {
            dao.editar(selectedTupa);
//            listarTupa();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Correctamente", null));
            PrimeFaces.current().executeScript("enviar('" + "Tupa" + "')");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Modificar" + e, null));
        }
    }

    public void eliminarTupa() throws Exception {
        try {
            dao.eliminar(selectedTupa);
//            listarTupa();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado Correctamente", null));
            PrimeFaces.current().executeScript("enviar('" + "Tupa" + "')");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Eliminar" + e, null));
        }
    }

    public void listarTupa() throws Exception {
        try {
            lstTupa = dao.listar();
        } catch (Exception e) {
            throw e;

        }
    }

    public Tupa getTupa() {
        return tupa;
    }

    public void setTupa(Tupa tupa) {
        this.tupa = tupa;
    }

    public Tupa getSelectedTupa() {
        return selectedTupa;
    }

    public void setSelectedTupa(Tupa selectedTupa) {
        this.selectedTupa = selectedTupa;
    }

    public List<Tupa> getLstTupa() {
        return lstTupa;
    }

    public void setLstTupa(List<Tupa> lstTupa) {
        this.lstTupa = lstTupa;
    }

    public List<Tupa> getFiltrado() {
        return filtrado;
    }

    public void setFiltrado(List<Tupa> filtrado) {
        this.filtrado = filtrado;
    }

}
