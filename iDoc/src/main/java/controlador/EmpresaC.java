package controlador;

import dao.EmpresaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Empresa;

@Named(value = "empresaC")
@SessionScoped
public class EmpresaC implements Serializable {

    Empresa empresa;
    private Empresa selectedEmpresa;
    private List<Empresa> lstEmpresa;
    private EmpresaImpl daoempresa;

    public EmpresaC() {
        empresa = new Empresa();
        daoempresa = new EmpresaImpl();
        lstEmpresa = new ArrayList<>();
    }

    
    @PostConstruct
    public void iniciar() {
        try {
            listarEmpresa();
        } catch (Exception e) {
        }
    }

    public void limpiarEmpresa() throws Exception {
        empresa = new Empresa();
    }

    public void registrarEmpresa() throws Exception {
        try {
            daoempresa.registrar(empresa);
//            listarEmpresa();
            limpiarEmpresa();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Registrar"+e, null));
        }
    }

    public void editarEmpresa() throws Exception {
        try {
            daoempresa.editar(selectedEmpresa);
//            listarEmpresa();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Correctamente", null));
        } catch (Exception e) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Modificar"+e, null));
            throw e;
        }
    }

    public void eliminarEmpresa() throws Exception {
        try {
            daoempresa.eliminar(selectedEmpresa);
//            listarEmpresa();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Eliminar", null));
        }
    }

    public void listarEmpresa() throws Exception {
        try {
            lstEmpresa = daoempresa.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Empresa getSelectedEmpresa() {
        return selectedEmpresa;
    }

    public void setSelectedEmpresa(Empresa selectedEmpresa) {
        this.selectedEmpresa = selectedEmpresa;
    }
    public List<Empresa> getLstEmpresa() {
        return lstEmpresa;
    }

    public void setLstEmpresa(List<Empresa> lstEmpresa) {
        this.lstEmpresa = lstEmpresa;
    }

}
