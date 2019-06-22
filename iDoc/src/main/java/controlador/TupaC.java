package controlador;

import dao.ImplTupaD;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.TupaM;

@Named(value = "tupaC")
@SessionScoped
public class TupaC implements Serializable {

    TupaM tupa = new TupaM();
    private TupaM selectedTupa;
    private List<TupaM> lstTupa;
    private List<TupaM> lstTupa2;
    ImplTupaD daotupa;


    @PostConstruct
    public void iniciar() {
        try {
            listarTupa();
        } catch (Exception e) {
        }
    }

    public void limpiarTupa() throws Exception {
        tupa = new TupaM();
    }

    public void registrarTupa() throws Exception {
        ImplTupaD dao;
        try {
            dao = new ImplTupaD();
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
        ImplTupaD dao;
        try {
            dao = new ImplTupaD();
            dao.editar(selectedTupa);
            listarTupa();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Modificar" + e, null));
        }
    }

    public void eliminarTupa() throws Exception {
        ImplTupaD dao;
        try {
            dao = new ImplTupaD();
            dao.eliminar(selectedTupa);
            listarTupa();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Eliminar" + e, null));
        }
    }

    public void generarReporte(TupaM tupa) throws  Exception{

        try {
            daotupa.generarReporte(tupa);
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    public void listarTupa() throws Exception {
        ImplTupaD dao;
        try {
            dao = new ImplTupaD();
            lstTupa = dao.listar();
        } catch (Exception e) {
            throw e;

        }
    }

    public TupaM getTupa() {
        return tupa;
    }

    public void setTupa(TupaM tupa) {
        this.tupa = tupa;
    }

    public TupaM getSelectedTupa() {
        return selectedTupa;
    }

    public void setSelectedTupa(TupaM selectedTupa) {
        this.selectedTupa = selectedTupa;
    }

    public List<TupaM> getLstTupa() {
        return lstTupa;
    }

    public void setLstTupa(List<TupaM> lstTupa) {
        this.lstTupa = lstTupa;
    }

    public List<TupaM> getLstTupa2() {
        return lstTupa2;
    }

    public void setLstTupa2(List<TupaM> lstTupa2) {
        this.lstTupa2 = lstTupa2;
    }

}
