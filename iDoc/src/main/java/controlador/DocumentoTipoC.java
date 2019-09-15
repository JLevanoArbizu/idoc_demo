package controlador;

import dao.DocumentoTipoImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.DocumentoTipo;
import org.primefaces.PrimeFaces;

@Named(value = "documentoTipoC")
@SessionScoped
public class DocumentoTipoC implements Serializable {

    DocumentoTipo documentoTipo;
    DocumentoTipo selecteddocumentoTipo;
    List<DocumentoTipo> lstdocumentoTipo, filtrado;
    DocumentoTipoImpl dao;

    public DocumentoTipoC() {

        documentoTipo = new DocumentoTipo();
        lstdocumentoTipo = new ArrayList<>();
        filtrado = new ArrayList<>();
        selecteddocumentoTipo = new DocumentoTipo();
        dao = new DocumentoTipoImpl();
    }

    @PostConstruct
    public void iniciar() {
        try {
            listarDocTipo();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void limpiarDocumentotipo() {
        documentoTipo = new DocumentoTipo();
    }

    public void registrarDocTipo() throws SQLException, Exception {
        try {
            dao.registrar(documentoTipo);
            limpiarDocumentotipo();
            PrimeFaces.current().executeScript("enviar('" + "DocumentoTipo" + "');");
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado Correctamente", null));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void editarDocTipo() {
        try {
            dao.editar(selecteddocumentoTipo);
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Correctamente", null));
            PrimeFaces.current().executeScript("enviar('" + "DocumentoTipo" + "');");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Modificar" + e, null));
            e.printStackTrace();
        }

    }

    public void eliminarDocTipo() {
        try {
            dao.eliminar(selecteddocumentoTipo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado Correctamente", null));
            PrimeFaces.current().executeScript("enviar('" + "DocumentoTipo" + "');");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Eliminar" + e, null));
            e.printStackTrace();
        }
    }

    public void listarDocTipo() throws Exception {
        try {
            lstdocumentoTipo = dao.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DocumentoTipo getDocumentoTipo() {
        return documentoTipo;
    }

    public void setDocumentoTipo(DocumentoTipo documentoTipo) {
        this.documentoTipo = documentoTipo;
    }

    public DocumentoTipo getSelecteddocumentoTipo() {
        return selecteddocumentoTipo;
    }

    public void setSelecteddocumentoTipo(DocumentoTipo selecteddocumentoTipo) {
        this.selecteddocumentoTipo = selecteddocumentoTipo;
    }

    public List<DocumentoTipo> getLstdocumentoTipo() {
        return lstdocumentoTipo;
    }

    public void setLstdocumentoTipo(List<DocumentoTipo> lstdocumentoTipo) {
        this.lstdocumentoTipo = lstdocumentoTipo;
    }

    public List<DocumentoTipo> getFiltrado() {
        return filtrado;
    }

    public void setFiltrado(List<DocumentoTipo> filtrado) {
        this.filtrado = filtrado;
    }

}
