package controlador;

import dao.IncidenciaTipoImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import modelo.IncidenciaTipo;

@Named(value = "incidenciatipoC")
@SessionScoped
public class IncidenciaTipoC implements Serializable {

    List<IncidenciaTipo> listaIncidenciaTipo;

    IncidenciaTipo incidenciaTipo;

    public List<IncidenciaTipo> getListaIncidenciaTipo() {
        return listaIncidenciaTipo;
    }

    public void setListaIncidenciaTipo(List<IncidenciaTipo> listaIncidenciaTipo) {
        this.listaIncidenciaTipo = listaIncidenciaTipo;
    }

    public IncidenciaTipo getIncidenciaTipo() {
        return incidenciaTipo;
    }

    public void setIncidenciaTipo(IncidenciaTipo incidenciaTipo) {
        this.incidenciaTipo = incidenciaTipo;
    }

    IncidenciaTipoImpl daoIncidenciaTipo;

    public IncidenciaTipoC() throws Exception {
        listaIncidenciaTipo = new ArrayList<>();
        incidenciaTipo = new IncidenciaTipo();

        daoIncidenciaTipo = new IncidenciaTipoImpl();
        listarIncidenciaTipo();
    }

    @PostConstruct
    public void init() {
        try {
            listarIncidenciaTipo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrar() throws Exception {
        try {
            daoIncidenciaTipo.registrar(incidenciaTipo);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado Correctamente", null));
            listarIncidenciaTipo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editar() throws Exception {
        try {
            daoIncidenciaTipo.editar(incidenciaTipo);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado Correctamente", null));
            listarIncidenciaTipo();
        } catch (Exception e) {

        }
    }

    public void eliminar(IncidenciaTipo incidenciaTipo) throws Exception {
        try {
            daoIncidenciaTipo.eliminar(incidenciaTipo);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado Correctamente", null));
            listarIncidenciaTipo();
            listarIncidenciaTipo();
        } catch (Exception e) {
        }
    }

    public void listarIncidenciaTipo() throws Exception {
        try {
            listaIncidenciaTipo = daoIncidenciaTipo.listar();
            List<IncidenciaTipo> listaTemp = new ArrayList<>();
            for (IncidenciaTipo nextIncidenciaTipo : listaIncidenciaTipo) {
                nextIncidenciaTipo.setESTINCTIP(nextIncidenciaTipo.getESTINCTIP().equals("A") ? "Activo" : "Inactivo");
                switch (nextIncidenciaTipo.getTIPINCTIP()) {
                    case "J":
                        nextIncidenciaTipo.setTIPINCTIP("Judicial");
                        break;
                    case "N":
                        nextIncidenciaTipo.setTIPINCTIP("Notarial");
                        break;
                }

                listaTemp.add(nextIncidenciaTipo);
            }
            listaIncidenciaTipo = listaTemp;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public List<String> buscarIncidenciaTipo(String nombre) throws Exception {
        return daoIncidenciaTipo.buscar(nombre, listaIncidenciaTipo);
    }

    public IncidenciaTipo obtenerCodigoIncidenciaTipo() throws Exception {
        return daoIncidenciaTipo.obtenerCodigo(listaIncidenciaTipo, incidenciaTipo);
    }    
}
