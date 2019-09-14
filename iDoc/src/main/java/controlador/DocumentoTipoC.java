package controlador;

import dao.DocumentoImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import modelo.DocumentoTipo;

@Named(value = "documentoTipoC")
@SessionScoped
public class DocumentoTipoC implements Serializable {

    DocumentoTipo documentoTipo;
    DocumentoTipo selecteddocumentoTipo;
    List<DocumentoTipo> lstDocumento, filtrado;
    DocumentoImpl dao;

    public DocumentoTipoC() {

        documentoTipo = new DocumentoTipo();
        lstDocumento = new ArrayList<>();
        filtrado = new ArrayList<>();
        selecteddocumentoTipo = new DocumentoTipo();
        dao = new DocumentoImpl();
    }

    @PostConstruct
    public void iniciar() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void limpiarDocumentotipo() {
        documentoTipo = new DocumentoTipo();
    }
    
    public void registrarDocTipo(){
    
    
    }

}
