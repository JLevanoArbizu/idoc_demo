package controlador;

import dao.ActaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import modelo.Acta;
import modelo.Ubigeo;

@Named(value = "actaC")
@SessionScoped
public class ActaC extends UbigeoC implements Serializable {

    Acta acta;
    List<Acta> listaDocumentosGeneral;
    List<Acta> listaDocumentosAN;
    List<Acta> listaDocumentosAM;
    List<Acta> listaDocumentosAD;

    List<Acta> listaDocumentosANfiltrado;
    List<Acta> listaDocumentosAMfiltrado;
    List<Acta> listaDocumentosADfiltrado;

    ActaImpl daoActa;
    ActorC actorC;
    
    @ManagedProperty("#{trabajadorC}")
    TrabajadorC trabajadorC;

    public ActaC() {
        try {
            acta = new Acta();
            listaDocumentosGeneral = new ArrayList<>();
            listaDocumentosAN = new ArrayList<>();
            listaDocumentosAM = new ArrayList<>();
            listaDocumentosAD = new ArrayList<>();
            daoActa = new ActaImpl();
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
            listaDocumentosGeneral = daoActa.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void accionActa(char tipoDocumento, char tipoAccion, Acta documentoEliminar) throws Exception {
        try {
            acta.setTIPACTA(String.valueOf(tipoDocumento));
            seterCodigos();
            boolean existe = false;
            switch (tipoAccion) {
                case '1':
                    switch (tipoDocumento) {
                        case '1':
                            existe = daoActa.existe(listaDocumentosAN, acta);
                            break;
                        case '2':
                            existe = daoActa.existe(listaDocumentosAM, acta);
                            break;
                        case '3':
                            existe = daoActa.existe(listaDocumentosAD, acta);
                            break;
                    }
                    if (!existe) {
                        daoActa.registrar(acta);
                    } else {
                        return;
                    }
                    break;
                case '2':
                    daoActa.editar(acta);
                    break;
                case '3':
                    daoActa.eliminar(documentoEliminar);
                    break;
            }
            registrarActores();
            acta.clear();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", null));
            e.printStackTrace();
        }
    }
    
    public void descargarReporte(Acta doc) throws Exception{
        try {
            daoActa.generarReporte(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void registrarActores() throws Exception {
        try {
            listar();
            actorC.actor.setIDACTA(daoActa.obtenerCodigo(listaDocumentosGeneral, acta).getIDACTA());
            switch (acta.getTIPACTA()) {
                case "1":
                    actorC.persona.setCOMPLETO(acta.getDeclarante());
                    actorC.actor.setTIPACT("2");
                    actorC.registrarActor();
                    acta.setDeclarante(actorC.persona.getCOMPLETO());

                    actorC.persona.setCOMPLETO(acta.getPapa());
                    actorC.actor.setTIPACT("3");
                    actorC.registrarActor();
                    acta.setPapa(actorC.persona.getCOMPLETO());

                    actorC.persona.setCOMPLETO(acta.getMama());
                    actorC.actor.setTIPACT("4");
                    actorC.registrarActor();
                    acta.setMama(actorC.persona.getCOMPLETO());

                    actorC.persona.setCOMPLETO(acta.getMedico());
                    actorC.actor.setTIPACT("6");
                    actorC.registrarActor();
                    acta.setMedico(actorC.persona.getCOMPLETO());

                    break;
                case "2":
                    actorC.persona.setCOMPLETO(acta.getEsposa());
                    actorC.actor.setTIPACT("1");
                    actorC.registrarActor();
                    acta.setEsposa(actorC.persona.getCOMPLETO());

                    actorC.persona.setCOMPLETO(acta.getCelebrante());
                    actorC.actor.setTIPACT("5");
                    actorC.registrarActor();
                    acta.setCelebrante(actorC.persona.getCOMPLETO());

                    break;

                case "3":
                    actorC.persona.setCOMPLETO(acta.getDeclarante());
                    actorC.actor.setTIPACT("2");
                    actorC.registrarActor();
                    acta.setDeclarante(actorC.persona.getCOMPLETO());

                    break;
            }
            listarActas();
            actorC.actor.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void seterCodigos() throws Exception {
        acta.setCODUBI(obtenerCodigoUbigeo().getCODUBI());
        actorC.persona.setCOMPLETO(acta.getTitular());
        acta.setIDPER(actorC.obtenerCodigo().getIDPER());
        acta.setFECREGACTA(new java.sql.Date(acta.getFECREGACTA().getTime()));
        acta.setFECACT(new java.sql.Date(acta.getFECACT().getTime()));
    }

    public void listarActas() throws Exception {
        listar();
        listaDocumentosAN.clear();
        listaDocumentosAM.clear();
        listaDocumentosAD.clear();
        Acta docTmpAM = new Acta();
        for (Acta documentoTemp : listaDocumentosGeneral) {
            String tipoDocumento = documentoTemp.getTIPACTA();
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

        Acta anterior = new Acta();
        Acta docTmpAN = new Acta();
        List<Acta> listaTmp = new ArrayList<>();
        for (Acta documentoTemporal : listaDocumentosAN) {
            if (documentoTemporal.getIDACTA().equals(anterior.getIDACTA())) {
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

    public Acta getActa() {
        return acta;
    }

    public void setActa(Acta acta) {
        this.acta = acta;
    }

    public List<Acta> getListaDocumentosGeneral() {
        return listaDocumentosGeneral;
    }

    public void setListaDocumentosGeneral(List<Acta> listaDocumentosGeneral) {
        this.listaDocumentosGeneral = listaDocumentosGeneral;
    }

    public ActorC getActorC() {
        return actorC;
    }

    public void setActorC(ActorC actorC) {
        this.actorC = actorC;
    }

    public List<Acta> getListaDocumentosAN() {
        return listaDocumentosAN;
    }

    public void setListaDocumentosAN(List<Acta> listaDocumentosAN) {
        this.listaDocumentosAN = listaDocumentosAN;
    }

    public List<Acta> getListaDocumentosAM() {
        return listaDocumentosAM;
    }

    public void setListaDocumentosAM(List<Acta> listaDocumentosAM) {
        this.listaDocumentosAM = listaDocumentosAM;
    }

    public List<Acta> getListaDocumentosAD() {
        return listaDocumentosAD;
    }

    public void setListaDocumentosAD(List<Acta> listaDocumentosAD) {
        this.listaDocumentosAD = listaDocumentosAD;
    }

    public List<Acta> getListaDocumentosANfiltrado() {
        return listaDocumentosANfiltrado;
    }

    public void setListaDocumentosANfiltrado(List<Acta> listaDocumentosANfiltrado) {
        this.listaDocumentosANfiltrado = listaDocumentosANfiltrado;
    }

    public List<Acta> getListaDocumentosAMfiltrado() {
        return listaDocumentosAMfiltrado;
    }

    public void setListaDocumentosAMfiltrado(List<Acta> listaDocumentosAMfiltrado) {
        this.listaDocumentosAMfiltrado = listaDocumentosAMfiltrado;
    }

    public List<Acta> getListaDocumentosADfiltrado() {
        return listaDocumentosADfiltrado;
    }

    public void setListaDocumentosADfiltrado(List<Acta> listaDocumentosADfiltrado) {
        this.listaDocumentosADfiltrado = listaDocumentosADfiltrado;
    }

    public TrabajadorC getTrabajadorC() {
        return trabajadorC;
    }

    public void setTrabajadorC(TrabajadorC trabajadorC) {
        this.trabajadorC = trabajadorC;
    }


}
