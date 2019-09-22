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
import org.primefaces.PrimeFaces;
import org.primefaces.model.StreamedContent;

@Named(value = "transferenciaC")
@SessionScoped
public class TransferenciaC implements Serializable {

    Transferencia transferencia;
    Transferencia selectedTransferencia;
    List<Transferencia> lstTransferencia, lstTransferenciaFiltrado, listaBandeja, listaBandejaFiltrada;
    TransferenciaImpl dao;

    StreamedContent reporte;

    public TransferenciaC() {
        transferencia = new Transferencia();
        lstTransferencia = new ArrayList<>();
        listaBandeja = new ArrayList<>();
        dao = new TransferenciaImpl();
        selectedTransferencia = new Transferencia();
    }

    @PostConstruct
    public void iniciar() {
        try {
            listarTransferencia();
            listarBandeja();
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
//            listarTransferencia();
            limpiarTransferencia();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado Correctamente", null));
            PrimeFaces.current().executeScript("enviar('" + "Transferencia" + "');");
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Registrar" + e, null));
        }
    }

    public void editarTransferencia() throws Exception {
        try {
            dao.editar(selectedTransferencia);
//            listarTransferencia();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Correctamente", null));
            PrimeFaces.current().executeScript("enviar('" + "Transferencia" + "');");
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Modificar" + e, null));
        }
    }

    public void eliminarTransferencia() throws Exception {
        try {
            dao.eliminar(selectedTransferencia);
//            listarTransferencia();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado Correctamente", null));
            PrimeFaces.current().executeScript("enviar('" + "Transferencia" + "');");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Eliminar" + e, null));

        }
    }

    public void listarBandeja() throws Exception {
        try {
            listaBandeja = dao.listar(selectedTransferencia);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void recepcionarTransferencia() throws Exception {
        try {
            dao.recibir(selectedTransferencia);
            PrimeFaces.current().executeScript("enviar('" + "Bandeja" + "');");
            PrimeFaces.current().executeScript("enviar('" + "Transferencia" + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void derivar() throws Exception {
        try {
            selectedTransferencia.setAreaEmisora(servicios.SesionS.getSesion().getTrabajador().getArea());
            dao.registrar(selectedTransferencia);
            PrimeFaces.current().executeScript("enviar('" + "Bandeja" + "');");
            PrimeFaces.current().executeScript("enviar('" + "Transferencia" + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarTransferencia() throws Exception {
        try {
            lstTransferencia = dao.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void generarReporte() throws Exception {
//        try {
//            reporte = dao.generarReporteIndividualPrev(transferencia);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public Transferencia getTransferencia() {
        return transferencia;
    }

    public void setTransferencia(Transferencia transferencia) {
        this.transferencia = transferencia;
    }

    public Transferencia getSelectedTransferencia() {
        return selectedTransferencia;
    }

    public void setSelectedTransferencia(Transferencia selectedTransferencia) {
        this.selectedTransferencia = selectedTransferencia;
    }

    public List<Transferencia> getLstTransferencia() {
        return lstTransferencia;
    }

    public void setLstTransferencia(List<Transferencia> lstTransferencia) {
        this.lstTransferencia = lstTransferencia;
    }

    public List<Transferencia> getLstTransferenciaFiltrado() {
        return lstTransferenciaFiltrado;
    }

    public void setLstTransferenciaFiltrado(List<Transferencia> lstTransferenciaFiltrado) {
        this.lstTransferenciaFiltrado = lstTransferenciaFiltrado;
    }

    public List<Transferencia> getListaBandeja() {
        return listaBandeja;
    }

    public void setListaBandeja(List<Transferencia> listaBandeja) {
        this.listaBandeja = listaBandeja;
    }

    public List<Transferencia> getListaBandejaFiltrada() {
        return listaBandejaFiltrada;
    }

    public void setListaBandejaFiltrada(List<Transferencia> listaBandejaFiltrada) {
        this.listaBandejaFiltrada = listaBandejaFiltrada;
    }

    public StreamedContent getReporte() {
        return reporte;
    }

    public void setReporte(StreamedContent reporte) {
        this.reporte = reporte;
    }

}
