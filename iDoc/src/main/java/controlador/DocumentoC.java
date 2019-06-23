package controlador;

import dao.DocumentoImpl;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Documento;
import modelo.Persona;  //PersonaM -tradoc

@Named(value = "documentoC")
@SessionScoped
public class DocumentoC implements Serializable {

    Documento documento = new Documento();
    private Documento selectedDocumento;
    private List<Documento> lstdocumento;
    private List<Persona> lstPersona;

    @PostConstruct
    public void iniciar() {
        try {
            listarDocumento();
        } catch (Exception e) {
        }

    }

    public void registrarDocumento() throws Exception {
        DocumentoImpl dao;
        try {
            dao = new DocumentoImpl();
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
        DocumentoImpl dao;
        try {
            dao = new DocumentoImpl();
            dao.editar(selectedDocumento);
            listarDocumento();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Modificar" + e, null));
        }
    }

    public void eliminarDocumento() throws Exception {
        DocumentoImpl dao;
        try {
            dao = new DocumentoImpl();
            dao.eliminar(selectedDocumento);
            listarDocumento();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Eliminar", null));
        }
    }

    public void listarDocumento() {
        DocumentoImpl dao;
        try {

            dao = new DocumentoImpl();
            lstdocumento = dao.listar();
            lstPersona = getLstPersona();
//            lstPersona = getLstPersona();

        } catch (Exception e) {
        }
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Documento getSelectedDocumento() {
        return selectedDocumento;
    }

    public void setSelectedDocumento(Documento selectedDocumento) {
        this.selectedDocumento = selectedDocumento;
    }

    public List<Documento> getLstdocumento() {
        return lstdocumento;
    }

    public void setLstdocumento(List<Documento> lstdocumento) {
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
