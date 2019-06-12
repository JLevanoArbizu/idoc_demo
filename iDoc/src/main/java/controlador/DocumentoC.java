package controlador;

import dao.DocumentoImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Documento;
import modelo.Ubigeo;

@Named(value = "documentoC")
@SessionScoped
public class DocumentoC extends UbigeoC implements Serializable {

    Documento documento;
    List<Documento> listaDocumentosGeneral;
    List<Documento> listaDocumentosAN;
    List<Documento> listaDocumentosAM;
    List<Documento> listaDocumentosAD;

    List<Documento> listaDocumentosANfiltrado;
    List<Documento> listaDocumentosAMfiltrado;
    List<Documento> listaDocumentosADfiltrado;

    DocumentoImpl daoDocumento;
    ActorC actorC;


    public DocumentoC() {
        try {
            documento = new Documento();
            listaDocumentosGeneral = new ArrayList<>();
            listaDocumentosAN = new ArrayList<>();
            listaDocumentosAM = new ArrayList<>();
            listaDocumentosAD = new ArrayList<>();
            List<Documento> listaDocumentosANfiltrado = new ArrayList<>();
            List<Documento> listaDocumentosAMfiltrado = new ArrayList<>();
            List<Documento> listaDocumentosADfiltrado = new ArrayList<>();
            daoDocumento = new DocumentoImpl();
            actorC = new ActorC();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void init() {
        try {
            listarActas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listar() throws Exception {
        try {
            listaDocumentosGeneral = daoDocumento.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void accionDocumento(char tipoDocumento, char tipoAccion, Documento documentoEliminar) throws Exception {
        try {
            documento.setTIPDOC(String.valueOf(tipoDocumento));
            seterCodigos();
            boolean existe = false;
            switch (tipoAccion) {
                case '1':
                    switch (tipoDocumento) {
                        case '1':
                            existe = daoDocumento.existe(listaDocumentosAN, documento);
                            break;
                        case '2':
                            existe = daoDocumento.existe(listaDocumentosAM, documento);
                            break;
                        case '3':
                            existe = daoDocumento.existe(listaDocumentosAD, documento);
                            break;
                    }
                    if (!existe) {
                        daoDocumento.registrar(documento);
                    } else {
                        return;
                    }
                    break;
                case '2':
                    daoDocumento.editar(documento);
                    break;
                case '3':
                    daoDocumento.eliminar(documentoEliminar);
                    break;
            }
            registrarActores();
            documento.clear();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", null));
            e.printStackTrace();
        }
    }

    public void registrarActores() throws Exception {
        try {
            listar();
            actorC.actor.setIDDOC(daoDocumento.obtenerCodigo(listaDocumentosGeneral, documento).getIDDOC());
            switch (documento.getTIPDOC()) {
                case "1":
                    actorC.persona.setCOMPLETO(documento.getDeclarante());
                    actorC.actor.setTIPACT("2");
                    actorC.registrarActor();
                    documento.setDeclarante(actorC.persona.getCOMPLETO());

                    actorC.persona.setCOMPLETO(documento.getPapa());
                    actorC.actor.setTIPACT("3");
                    actorC.registrarActor();
                    documento.setPapa(actorC.persona.getCOMPLETO());

                    actorC.persona.setCOMPLETO(documento.getMama());
                    actorC.actor.setTIPACT("4");
                    actorC.registrarActor();
                    documento.setMama(actorC.persona.getCOMPLETO());

                    actorC.persona.setCOMPLETO(documento.getMedico());
                    actorC.actor.setTIPACT("6");
                    actorC.registrarActor();
                    documento.setMedico(actorC.persona.getCOMPLETO());

                    break;
                case "2":
                    actorC.persona.setCOMPLETO(documento.getEsposa());
                    actorC.actor.setTIPACT("1");
                    actorC.registrarActor();
                    documento.setEsposa(actorC.persona.getCOMPLETO());

                    actorC.persona.setCOMPLETO(documento.getCelebrante());
                    actorC.actor.setTIPACT("5");
                    actorC.registrarActor();
                    documento.setCelebrante(actorC.persona.getCOMPLETO());

                    break;

                case "3":
                    actorC.persona.setCOMPLETO(documento.getDeclarante());
                    actorC.actor.setTIPACT("2");
                    actorC.registrarActor();
                    documento.setDeclarante(actorC.persona.getCOMPLETO());

                    break;
            }
            documento.clear();
            actorC.actor.clear();
            listarActas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void seterCodigos() throws Exception {
        documento.setIDMUN("6");
        documento.setCODUBI(obtenerCodigoUbigeo().getCODUBI());
        actorC.persona.setCOMPLETO(documento.getTitular());
        documento.setIDPER(actorC.obtenerCodigo().getIDPER());
        documento.setFECREGDOC(new java.sql.Date(documento.getFECREGDOC().getTime()));
        documento.setFECACT(new java.sql.Date(documento.getFECACT().getTime()));
    }

    public void listarActas() throws Exception {
        listar();
        listaDocumentosAN.clear();
        listaDocumentosAM.clear();
        listaDocumentosAD.clear();
        Documento docTmpAM = new Documento();
        for (Documento documentoTemp : listaDocumentosGeneral) {
            String tipoDocumento = documentoTemp.getTIPDOC();
            if (tipoDocumento != null) {
                for (Ubigeo ubigeo1 : listaUbigeo) {
                    if (ubigeo1.getCODUBI().equals(documentoTemp.getCODUBI())) {
                        documentoTemp.setCODUBI(ubigeo1.getDISTUBI());
                        break;
                    }
                }
                switch (tipoDocumento) {
                    case "1":
                        listaDocumentosAN.add(documentoTemp);
                        break;
                    case "2":
                        if (documentoTemp.getCelebrante() != null) {
                            listaDocumentosAM.remove(listaDocumentosAM.indexOf(docTmpAM));
                            docTmpAM.setCelebrante(documentoTemp.getCelebrante());
                        } else {
                            docTmpAM = documentoTemp;
                        }
                        listaDocumentosAM.add(docTmpAM);
                        break;
                    case "3":
                        listaDocumentosAD.add(documentoTemp);
                        break;
                    default:
                        break;
                }
            }

        }

        Documento anterior = new Documento();
        Documento docTmpAN = new Documento();
        List<Documento> listaTmp = new ArrayList<>();
        for (Documento documentoTemporal : listaDocumentosAN) {
            if (documentoTemporal.getIDDOC().equals(anterior.getIDDOC())) {
                if (documentoTemporal.getPapa() != null) {
                    docTmpAN = documentoTemporal;
                    docTmpAN.setPapa(documentoTemporal.getPapa());
                } else if (documentoTemporal.getMama() != null) {
                    docTmpAN.setMama(documentoTemporal.getMama());
                } else if (documentoTemporal.getMedico() != null) {
                    docTmpAN.setMedico(documentoTemporal.getMedico());
                    listaTmp.add(docTmpAN);
                }
                if (anterior.getDeclarante() != null) {
                    docTmpAN.setDeclarante(anterior.getDeclarante());
                }
            }
            anterior = documentoTemporal;

        }
        listaDocumentosAN = listaTmp;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public List<Documento> getListaDocumentosGeneral() {
        return listaDocumentosGeneral;
    }

    public void setListaDocumentosGeneral(List<Documento> listaDocumentosGeneral) {
        this.listaDocumentosGeneral = listaDocumentosGeneral;
    }

    public ActorC getActorC() {
        return actorC;
    }

    public void setActorC(ActorC actorC) {
        this.actorC = actorC;
    }

    public List<Documento> getListaDocumentosAN() {
        return listaDocumentosAN;
    }

    public void setListaDocumentosAN(List<Documento> listaDocumentosAN) {
        this.listaDocumentosAN = listaDocumentosAN;
    }

    public List<Documento> getListaDocumentosAM() {
        return listaDocumentosAM;
    }

    public void setListaDocumentosAM(List<Documento> listaDocumentosAM) {
        this.listaDocumentosAM = listaDocumentosAM;
    }

    public List<Documento> getListaDocumentosAD() {
        return listaDocumentosAD;
    }

    public void setListaDocumentosAD(List<Documento> listaDocumentosAD) {
        this.listaDocumentosAD = listaDocumentosAD;
    }

    public List<Documento> getListaDocumentosANfiltrado() {
        return listaDocumentosANfiltrado;
    }

    public void setListaDocumentosANfiltrado(List<Documento> listaDocumentosANfiltrado) {
        this.listaDocumentosANfiltrado = listaDocumentosANfiltrado;
    }

    public List<Documento> getListaDocumentosAMfiltrado() {
        return listaDocumentosAMfiltrado;
    }

    public void setListaDocumentosAMfiltrado(List<Documento> listaDocumentosAMfiltrado) {
        this.listaDocumentosAMfiltrado = listaDocumentosAMfiltrado;
    }

    public List<Documento> getListaDocumentosADfiltrado() {
        return listaDocumentosADfiltrado;
    }

    public void setListaDocumentosADfiltrado(List<Documento> listaDocumentosADfiltrado) {
        this.listaDocumentosADfiltrado = listaDocumentosADfiltrado;
    }

}
