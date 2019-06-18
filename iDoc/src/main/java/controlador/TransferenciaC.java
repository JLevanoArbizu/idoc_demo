package controlador;

import dao.ImplTransferenciaD;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.TransferenciaM;

@Named(value = "TransferenciaC")
@SessionScoped
public class TransferenciaC implements Serializable {

    TransferenciaM transferencia = new TransferenciaM();
    private TransferenciaM selectedTransferencia;
    List<TransferenciaM> lstTransferencia;
    
    
    @PostConstruct
    public void iniciar() {
        try {
            listarTransferencia();
        } catch (Exception e) {
        }
    }

    public void limpiarTransferencia() throws Exception {
        transferencia = new TransferenciaM();
    }


    public void registrarTransferencia() throws Exception {
        ImplTransferenciaD dao;
        try {
            dao = new ImplTransferenciaD();
            dao.registrar(transferencia);
            listarTransferencia();
            limpiarTransferencia();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Registrar", null));
        }
    }

    public void editarTransferencia() throws Exception {
        ImplTransferenciaD dao;
        try {
            dao = new ImplTransferenciaD();
            dao.editar(selectedTransferencia);
            listarTransferencia();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Modificar", null));
        }
    }

    public void eliminarTransferencia() throws Exception {
        ImplTransferenciaD dao;
        try {
            dao = new ImplTransferenciaD();
            dao.eliminar(selectedTransferencia);
            listarTransferencia();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Eliminar", null));
        }
    }

    public void listarTransferencia() throws Exception {
        ImplTransferenciaD dao;
        try {
            dao = new ImplTransferenciaD();
            lstTransferencia = dao.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public TransferenciaM getTransferencia() {
        return transferencia;
    }

    public void setTransferencia(TransferenciaM transferencia) {
        this.transferencia = transferencia;
    }

    public TransferenciaM getSelectedTransferncia() {
        return selectedTransferencia;
    }

    public void setSelectedTransferncia(TransferenciaM selectedTransferncia) {
        this.selectedTransferencia = selectedTransferncia;
    }

    public List<TransferenciaM> getLstTransferencia() {
        return lstTransferencia;
    }

    public void setLstPersona(List<TransferenciaM> lstTransferencia) {
        this.lstTransferencia = lstTransferencia;
    }

    
    
}
