package controlador;

import dao.TupaImpl;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Tupa;

@Named(value = "tupaC")
@SessionScoped
public class TupaC implements Serializable {

    Tupa tupa = new Tupa();
    private Tupa selectedTupa;
    private List<Tupa> lstTupa;
    private List<Tupa> lstTupa2;
    TupaImpl daotupa;


    @PostConstruct
    public void iniciar() {
        try {
            listarTupa();
        } catch (Exception e) {
        }
    }

    public void limpiarTupa() throws Exception {
        tupa = new Tupa();
    }

    public void registrarTupa() throws Exception {
        TupaImpl dao;
        try {
            dao = new TupaImpl();
            dao.registrar(tupa);
            listarTupa();
            limpiarTupa();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado Correctamente", null));
            listarTupa();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Registrar" + e, null));
        }
    }

    public void editarTupa() throws Exception {
        TupaImpl dao;
        try {
            dao = new TupaImpl();
            dao.editar(selectedTupa);
            listarTupa();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Modificar" + e, null));
        }
    }

    public void eliminarTupa() throws Exception {
        TupaImpl dao;
        try {
            dao = new TupaImpl();
            dao.eliminar(selectedTupa);
            listarTupa();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Eliminar" + e, null));
        }
    }

    public void generarReporte(Tupa tupa) throws  Exception{

        try {
         //   daotupa.generarReporte(tupa);
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    public void listarTupa() throws Exception {
        TupaImpl dao;
        try {
            dao = new TupaImpl();
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

    public List<Tupa> getLstTupa2() {
        return lstTupa2;
    }

    public void setLstTupa2(List<Tupa> lstTupa2) {
        this.lstTupa2 = lstTupa2;
    }

}
