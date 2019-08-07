package controlador;

import dao.TransparenciaImpl;
import modelo.Documento;
import modelo.Empresa;
import modelo.Persona;
import modelo.Transparencia;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import modelo.Tupa;

@Named(value = "transparenciaC")
@SessionScoped
public class TransparenciaC implements Serializable {

//    Transparencia transparencia;
//    List<Transparencia> listaTransparencia;
//    TransparenciaImpl daoTransparencia;
//
//    Persona persona;
//    Empresa empresa;
//    Documento documento;
//
//    List<Tupa> listaTupa, listaTupaTemp;
//
//    String opcion, query;
//
//    public TransparenciaC() {
//        transparencia = new Transparencia();
//        listaTransparencia = new ArrayList<>();
//        daoTransparencia = new TransparenciaImpl();
//        persona = new Persona();
//        empresa = new Empresa();
//        documento = new Documento();
//        listaTupa = new ArrayList<>();
//        listaTupaTemp = new ArrayList<>();
//    }
//
//    public void clear() {
//        persona.clear();
//        empresa.clear();
//        documento.clear();
//    }
//
//    @PostConstruct
//    public void init(){
//        try {
//            listarTupa();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void listarTupa() throws Exception {
//        try {
//            listaTupa = daoTransparencia.listarTupa();
//            listaTupa.remove(0);
//            listaTupaTemp = listaTupa;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void buscarTupa() throws Exception {
//        try {
//            listaTupaTemp = daoTransparencia.buscarTupa(query.toUpperCase(), listaTupa);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void listarTransparencia() throws Exception {
//        try {
//            clear();
//            listaTransparencia = daoTransparencia.listarTransferencia(transparencia);
//            if (listaTransparencia.size() > 0) {
//                persona = listaTransparencia.get(0).getPersona();
//                empresa = listaTransparencia.get(0).getEmpresa();
//                documento = listaTransparencia.get(0).getDocumento();
//                FacesContext.getCurrentInstance().addMessage(null,
//                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Consulta Exitosa.", null));
//            } else {
//                FacesContext.getCurrentInstance().addMessage(null,
//                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Consulta Fallida.", null));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public Documento getDocumento() {
//        return documento;
//    }
//
//    public void setDocumento(Documento documento) {
//        this.documento = documento;
//    }
//
//    public Persona getPersona() {
//        return persona;
//    }
//
//    public void setPersona(Persona persona) {
//        this.persona = persona;
//    }
//
//    public Empresa getEmpresa() {
//        return empresa;
//    }
//
//    public void setEmpresa(Empresa empresa) {
//        this.empresa = empresa;
//    }
//
//    public Transparencia getTransparencia() {
//        return transparencia;
//    }
//
//    public void setTransparencia(Transparencia transparencia) {
//        this.transparencia = transparencia;
//    }
//
//    public List<Transparencia> getListaTransparencia() {
//        return listaTransparencia;
//    }
//
//    public void setListaTransparencia(List<Transparencia> listaTransparencia) {
//        this.listaTransparencia = listaTransparencia;
//    }
//
//    public String getOpcion() {
//        return opcion;
//    }
//
//    public void setOpcion(String opcion) {
//        this.opcion = opcion;
//    }
//
//    public List<Tupa> getListaTupa() {
//        return listaTupa;
//    }
//
//    public void setListaTupa(List<Tupa> listaTupa) {
//        this.listaTupa = listaTupa;
//    }
//
//    public List<Tupa> getListaTupaTemp() {
//        return listaTupaTemp;
//    }
//
//    public void setListaTupaTemp(List<Tupa> listaTupaTemp) {
//        this.listaTupaTemp = listaTupaTemp;
//    }
//
//    public String getQuery() {
//        return query;
//    }
//
//    public void setQuery(String query) {
//        this.query = query;
//    }

}
