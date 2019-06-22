package controlador;

import dao.ImplEmpresaD;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.EmpresaM;
import servicios.AutoCompleteS;

@Named(value = "empresaC")
@SessionScoped
public class EmpresaC implements Serializable {

    EmpresaM empresa = new EmpresaM();
    private EmpresaM selectedEmpresa;
    private List<EmpresaM> lstEmpresa;
    private ImplEmpresaD daoempresa;

    @PostConstruct
    public void iniciar() {
        try {
            listarEmpresa();
        } catch (Exception e) {
        }
    }

    public void limpiarEmpresa() throws Exception {
        empresa = new EmpresaM();
    }

    public void registrarEmpresa() throws Exception {
        ImplEmpresaD dao;
        AutoCompleteS svc;
        try {
            dao = new ImplEmpresaD();
//            svc = new AutoCompleteS();
//            empresa.setCODUBI(svc.leerUbi(empresa.getCODUBI()));
            dao.registrar(empresa);
            listarEmpresa();
            limpiarEmpresa();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Registrar"+e, null));
        }
    }

    public void editarEmpresa() throws Exception {
        ImplEmpresaD dao;
        AutoCompleteS svc;
        try {
            dao = new ImplEmpresaD();
//            svc = new AutoCompleteS();
//            selectedEmpresa.setCODUBI(svc.leerUbi(selectedEmpresa.getUBIGEO()));
            dao.editar(selectedEmpresa);
            listarEmpresa();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Correctamente", null));
        } catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Modificar", null));
            throw e;
        }
    }

    public void eliminarEmpresa() throws Exception {
        ImplEmpresaD dao;
        try {
            dao = new ImplEmpresaD();
            dao.eliminar(selectedEmpresa);
            listarEmpresa();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Eliminar", null));
        }
    }

    public void generarReporte(EmpresaM emper)throws Exception{
        try {
         //   daoempresa.generarReporte(emper);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void listarEmpresa() throws Exception {
        ImplEmpresaD dao;
        try {
            dao = new ImplEmpresaD();
            lstEmpresa = dao.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<String> completeTextUbi(String query) throws SQLException, Exception {
        AutoCompleteS svc = new AutoCompleteS();
        return svc.queryAutoCompleteUbi(query);
    }

    public EmpresaM getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaM empresa) {
        this.empresa = empresa;
    }

    public EmpresaM getSelectedEmpresa() {
        return selectedEmpresa;
    }

    public void setSelectedEmpresa(EmpresaM selectedEmpresa) {
        this.selectedEmpresa = selectedEmpresa;
    }
    public List<EmpresaM> getLstEmpresa() {
        return lstEmpresa;
    }

    public void setLstEmpresa(List<EmpresaM> lstEmpresa) {
        this.lstEmpresa = lstEmpresa;
    }

}
