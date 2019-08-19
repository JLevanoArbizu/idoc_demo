package controlador;

import dao.DocumentoImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Documento;

@Named(value = "documentoC")
@SessionScoped
public class DocumentoC implements Serializable {

    Documento documento;
    private Documento selectedDocumento;
    private List<Documento> lista;
    private DocumentoImpl daoDocumento;

    public DocumentoC() {
        documento = new Documento();
        selectedDocumento = new Documento();
        daoDocumento = new DocumentoImpl();
        lista = new ArrayList<>();
    }

    @PostConstruct
    public void iniciar() {
        try {
            listarDocumento();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void registrarDocumento() throws Exception {
        try {
            daoDocumento.registrar(documento);
            listarDocumento();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado Correctamente", null));
            documento.clear();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Registrar" + e, null));
            throw e;
        }
    }

    public void modificarDocumento() throws Exception {
        try {
            daoDocumento.editar(selectedDocumento);
            listarDocumento();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Modificar" + e, null));
        }
    }

    public void eliminarDocumento() throws Exception {
        try {
            daoDocumento.eliminar(selectedDocumento);
            listarDocumento();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Eliminar", null));
        }
    }

    public void listarDocumento() {
        try {
            lista = daoDocumento.listar();
        } catch (Exception e) {
            e.printStackTrace();
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
        return lista;
    }

    public void setLstdocumento(List<Documento> lista) {
        this.lista = lista;
    }
}
