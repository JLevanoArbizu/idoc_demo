package controlador;

import dao.TransferenciaImpl;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Transferencia;

@Named(value = "transferenciaC")
@SessionScoped
public class TransferenciaC implements Serializable {

    Transferencia transferencia = new Transferencia();
    private Transferencia selectedTransferencia;
    List<Transferencia> lstTransferencia;
    TransferenciaImpl daoTransferenciaImpl;

    @PostConstruct
    public void iniciar() {
        try {
            listarTransferencia();
        } catch (Exception e) {
        }
    }

    public void limpiarTransferencia() throws Exception {
        transferencia = new Transferencia();
    }

    public void registrarTransferencia() throws Exception {
        TransferenciaImpl dao;
        try {
            dao = new TransferenciaImpl();
            dao.registrar(transferencia);
            listarTransferencia();
            limpiarTransferencia();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Registrar" + e, null));
        }
    }

    public void editarTransferencia() throws Exception {
        TransferenciaImpl dao;
        try {
            dao = new TransferenciaImpl();
            dao.editar(selectedTransferencia);
            listarTransferencia();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Modificar", null));
        }
    }

    public void generarReporteTRANS(String IDTRAN) throws Exception {
        TransferenciaImpl Tranferen = new TransferenciaImpl();
        try {
            Map<String, Object> parameters = new HashMap(); // Libro de parametros
            parameters.put("IDTRAN", IDTRAN); //Insertamos un parametro
            Tranferen.generarReporteTRANS(parameters); //Pido exportar Reporte con los parametros
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarTransferencia() throws Exception {
        TransferenciaImpl dao;
        try {
            dao = new TransferenciaImpl();
            dao.eliminar(selectedTransferencia);
            listarTransferencia();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Eliminar", null));
        }
    }

    public void listarTransferencia() throws Exception {
        TransferenciaImpl dao;
        try {
            dao = new TransferenciaImpl();
            lstTransferencia = dao.listar();
        } catch (Exception e) {
            throw e;
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
