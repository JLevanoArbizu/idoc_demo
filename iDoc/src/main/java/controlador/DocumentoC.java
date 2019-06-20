package controlador;

import dao.ImplDocumentoD;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.DocumentoM;
import modelo.Persona;  //PersonaM -tradoc

@Named(value = "documentoC")
@SessionScoped
public class DocumentoC implements Serializable {

    DocumentoM documento = new DocumentoM();
    private DocumentoM selectedDocumento;
    private List<DocumentoM> lstdocumento;
    private List<Persona> lstPersona;

    @PostConstruct
    public void iniciar() {
        try {
            listarDocumento();
        } catch (Exception e) {
        }

    }

    public void registrarDocumento() throws Exception {
        ImplDocumentoD dao;
        try {
            dao = new ImplDocumentoD();
            dao.registrar(documento);
            listarDocumento();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado Correctamente", null));
            documento.clear();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Registrar" + e, null));
            throw e;
        }
    }

    public void modificarDocumento() throws Exception {
        ImplDocumentoD dao;
        try {
            dao = new ImplDocumentoD();
            dao.editar(selectedDocumento);
            listarDocumento();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Modificar" + e, null));
        }
    }

    public void eliminarDocumento() throws Exception {
        ImplDocumentoD dao;
        try {
            dao = new ImplDocumentoD();
            dao.eliminar(selectedDocumento);
            listarDocumento();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Eliminar", null));
        }
    }

    public void listarDocumento() {
        ImplDocumentoD dao;
        try {

            dao = new ImplDocumentoD();
            lstdocumento = dao.listar();
            lstPersona = getLstPersona();
//            lstPersona = getLstPersona();

        } catch (Exception e) {
        }
    }

    public DocumentoM getDocumento() {
        return documento;
    }

    public void setDocumento(DocumentoM documento) {
        this.documento = documento;
    }

    public DocumentoM getSelectedDocumento() {
        return selectedDocumento;
    }

    public void setSelectedDocumento(DocumentoM selectedDocumento) {
        this.selectedDocumento = selectedDocumento;
    }

    public List<DocumentoM> getLstdocumento() {
        return lstdocumento;
    }

    public void setLstdocumento(List<DocumentoM> lstdocumento) {
        this.lstdocumento = lstdocumento;
    }
//=====================================================================

    public List<Persona> getLstPersona() {
        return lstPersona;
    }

    public void setLstPersona(List<Persona> lstPersona) {
        this.lstPersona = lstPersona;
    }
//=====================================================================
}
