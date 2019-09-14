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
            System.out.println("errrrorrr iniciar"+e);
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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado Correctamente", null));
            listarDocTipo();

        } catch (SQLException e) {
            System.out.println("errrrorrr registra"+e);
            throw e;
        }

    }

    public void editarDocTipo() {
        try {
            dao.editar(selecteddocumentoTipo);
            listarDocTipo();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Correctamente", null));
        } catch (Exception e) {
            System.out.println("errrrorrr edita"+e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Modificar" + e, null));
        }

    }

    public void eliminarDocTipo() {
        try {
            dao.eliminar(selecteddocumentoTipo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado Correctamente", null));
        } catch (Exception e) {
            System.out.println("errrrorrr elimin"+e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Eliminar" + e, null));
        }
    }
    
    public void listarDocTipo() throws Exception{
        try {
            lstdocumentoTipo = dao.listar();
        } catch (Exception e) {
            System.out.println("errrrorrr list"+e);
            throw e;
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
