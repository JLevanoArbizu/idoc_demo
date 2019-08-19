package controlador;

import dao.TransferenciaImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Transferencia;

@Named(value = "transferenciaC")
@SessionScoped
public class TransferenciaC implements Serializable {

    Transferencia transferencia;
    Transferencia selectedTransferencia;
    List<Transferencia> lstTransferencia;
    TransferenciaImpl dao;

    public TransferenciaC() {
        transferencia = new Transferencia();
        lstTransferencia = new ArrayList<>();
        dao = new TransferenciaImpl();
    }

    @PostConstruct
    public void iniciar() {
        try {
            listarTransferencia();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void limpiarTransferencia() throws Exception {
        transferencia = new Transferencia();
    }

    public void registrarTransferencia() throws Exception {
        try {
            dao.registrar(transferencia);
            listarTransferencia();
            limpiarTransferencia();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Registrar" + e, null));
        }
    }

    public void editarTransferencia() throws Exception {
        try {
            dao.editar(selectedTransferencia);
            listarTransferencia();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Modificar"+e, null));
        }
    }

    public void eliminarTransferencia() throws Exception {
        try {
            dao.eliminar(selectedTransferencia);
            listarTransferencia();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Eliminar"+e, null));
        }
    }

    public void listarTransferencia() throws Exception {
        try {
            lstTransferencia = dao.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Transferencia getTransferencia() {
        return transferencia;
    }

    public void setTransferencia(Transferencia transferencia) {
        this.transferencia = transferencia;
    }

    public Transferencia getSelectedTransferncia() {
        return selectedTransferencia;
    }

    public void setSelectedTransferncia(Transferencia selectedTransferncia) {
        this.selectedTransferencia = selectedTransferncia;
    }

    public List<Transferencia> getLstTransferencia() {
        return lstTransferencia;
    }

    public void setLstPersona(List<Transferencia> lstTransferencia) {
        this.lstTransferencia = lstTransferencia;
    }

}
