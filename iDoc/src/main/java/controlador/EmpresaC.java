package controlador;

import dao.EmpresaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Empresa;

@Named(value = "empresaC")
@SessionScoped
public class EmpresaC implements Serializable {

//    Empresa empresa = new Empresa();
//    private Empresa selectedEmpresa;
//    private List<Empresa> lstEmpresa;
//    private EmpresaImpl daoempresa;
//
//    @PostConstruct
//    public void iniciar() {
//        try {
//            listarEmpresa();
//        } catch (Exception e) {
//        }
//    }
//
//    public void limpiarEmpresa() throws Exception {
//        empresa = new Empresa();
//    }
//
//    public void registrarEmpresa() throws Exception {
//        EmpresaImpl dao;
//        AutoCompleteS svc;
//        try {
//            dao = new EmpresaImpl();
//            dao.registrar(empresa);
//            listarEmpresa();
//            limpiarEmpresa();
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado Correctamente", null));
//        } catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Registrar"+e, null));
//        }
//    }
//
//    public void editarEmpresa() throws Exception {
//        EmpresaImpl dao;
//        try {
//            dao = new EmpresaImpl();
//            dao.editar(selectedEmpresa);
//            listarEmpresa();
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Correctamente", null));
//        } catch (Exception e) {
//           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Modificar"+e, null));
//            throw e;
//        }
//    }
//
//    public void eliminarEmpresa() throws Exception {
//        EmpresaImpl dao;
//        try {
//            dao = new EmpresaImpl();
//            dao.eliminar(selectedEmpresa);
//            listarEmpresa();
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado Correctamente", null));
//        } catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Eliminar", null));
//        }
//    }
//
//    public void generarReporte(Empresa IDEMP)throws Exception{
//        EmpresaImpl reportPer = new EmpresaImpl();
//        try {
//            Map<String, Object> parameters = new HashMap(); // Libro de parametros
//            parameters.put(null, IDEMP); //Insertamos un parametro
//            reportPer.generarReporte(parameters); //Pido exportar Reporte con los parametros
////            report.exportarPDF2(parameters);
//        } catch (Exception e) {
//            throw e;
//        }
//
//    }
//
//    public void listarEmpresa() throws Exception {
//        EmpresaImpl dao;
//        try {
//            dao = new EmpresaImpl();
//            lstEmpresa = dao.listar();
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//
//    public List<String> completeTextUbi(String query) throws SQLException, Exception {
//        AutoCompleteS svc = new AutoCompleteS();
//        return svc.queryAutoCompleteUbi(query);
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
//    public Empresa getSelectedEmpresa() {
//        return selectedEmpresa;
//    }
//
//    public void setSelectedEmpresa(Empresa selectedEmpresa) {
//        this.selectedEmpresa = selectedEmpresa;
//    }
//    public List<Empresa> getLstEmpresa() {
//        return lstEmpresa;
//    }
//
//    public void setLstEmpresa(List<Empresa> lstEmpresa) {
//        this.lstEmpresa = lstEmpresa;
//    }

}
