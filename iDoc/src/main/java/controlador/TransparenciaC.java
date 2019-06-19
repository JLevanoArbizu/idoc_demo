package controlador;

import dao.TransparenciaImpl;
import modelo.EmpresaM;
import modelo.Persona;
import modelo.Transparencia;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "transparenciaC")
@SessionScoped
public class TransparenciaC implements Serializable {
    Transparencia transparencia;
    List<Transparencia> listaTransparencia;
    TransparenciaImpl daoTransparencia;

    Persona persona;
    EmpresaM empresa;

    public TransparenciaC(){
        transparencia = new Transparencia();
        listaTransparencia = new ArrayList<>();
        daoTransparencia = new TransparenciaImpl();
        persona = new Persona();
        empresa = new EmpresaM();
    }


    public void listar() throws Exception{
        try {
            listaTransparencia = daoTransparencia.listar(transparencia);
            if (listaTransparencia.size()>0){
                persona = listaTransparencia.get(1).getPersona();
                empresa = listaTransparencia.get(1).getEmpresa();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Consulta Exitosa.", null));
            }else{
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Consulta Fallida.", null));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public EmpresaM getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaM empresa) {
        this.empresa = empresa;
    }

    public Transparencia getTransparencia() {
        return transparencia;
    }

    public void setTransparencia(Transparencia transparencia) {
        this.transparencia = transparencia;
    }

    public List<Transparencia> getListaTransparencia() {
        return listaTransparencia;
    }

    public void setListaTransparencia(List<Transparencia> listaTransparencia) {
        this.listaTransparencia = listaTransparencia;
    }
}
