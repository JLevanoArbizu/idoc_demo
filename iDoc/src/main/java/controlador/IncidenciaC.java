package controlador;

import dao.IncidenciaImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import modelo.Incidencia;
import modelo.IncidenciaTipo;

@Named(value = "incidenciaC")
@SessionScoped
public class IncidenciaC implements Serializable {
    
    IncidenciaTipoC incidenciatipo;

    public IncidenciaTipoC getIncidenciatipo() {
        return incidenciatipo;
    }

    public void setIncidenciatipo(IncidenciaTipoC incidenciatipo) {
        this.incidenciatipo = incidenciatipo;
    }

    List<Incidencia> listaIncidencia;

    Incidencia incidencia;

    public List<Incidencia> getListaIncidencia() {
        return listaIncidencia;
    }

    public void setListaIncidencia(List<Incidencia> listaIncidencia) {
        this.listaIncidencia = listaIncidencia;
    }

    public Incidencia getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(Incidencia incidencia) {
        this.incidencia = incidencia;
    }

    IncidenciaImpl daoIncidencia;

    public IncidenciaC() throws Exception {
        listaIncidencia = new ArrayList<>();
        incidencia = new Incidencia();
        incidenciatipo = new IncidenciaTipoC();
        daoIncidencia = new IncidenciaImpl();
    }

    @PostConstruct
    public void init() {
        try {
            incidenciatipo.listarIncidenciaTipo();
            listarIncidencia();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrarI() throws Exception {
        try {
            setearCodigoIncidenciaTipo();
            incidencia.setFECINC(new java.sql.Date(incidencia.getFechaTemporal().getTime()));
            incidencia.setIDINCTIP(incidenciatipo.obtenerCodigoIncidenciaTipo().getIDINCTIP());
            daoIncidencia.registrar(incidencia);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado Correctamente", null));
            listarIncidencia();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editarI() throws Exception {
        try {
            incidencia.setFECINC(new java.sql.Date(incidencia.getFechaTemporal().getTime()));
            incidenciatipo.incidenciaTipo.setNOMINCTIP(incidencia.getIDINCTIP());
            incidencia.setIDINCTIP(incidenciatipo.obtenerCodigoIncidenciaTipo().getIDINCTIP());
            daoIncidencia.editar(incidencia);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Editado Correctamente", null));
            listarIncidencia();
        } catch (Exception e) {
        }
    }

    public void eliminar(Incidencia inci) throws Exception {
        try {
            daoIncidencia.eliminar(inci);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado Correctamente", null));
            listarIncidencia();
            listarIncidencia();
        } catch (Exception e) {
        }
    }

    public void setearCodigoIncidenciaTipo() throws Exception {
        incidenciatipo.incidenciaTipo.setNOMINCTIP(incidencia.getIDINCTIP());
        incidencia.setIDINCTIP(incidenciatipo.obtenerCodigoIncidenciaTipo().getIDINCTIP());
    }

    public void listarIncidencia() throws Exception {
        try {
            listaIncidencia = daoIncidencia.listar();
            List<Incidencia> listaTemp = new ArrayList<>();

            for (Incidencia nextIncidencia : listaIncidencia) {

                for (IncidenciaTipo nextIncidenciaTipo : incidenciatipo.listaIncidenciaTipo) {

                    if (nextIncidencia.getIDINCTIP().equals(nextIncidenciaTipo.getIDINCTIP())) {
                        nextIncidencia.setIDINCTIP(nextIncidenciaTipo.getNOMINCTIP());
                        listaTemp.add(nextIncidencia);
                    }
                }

            }

            listaIncidencia = listaTemp;
            incidencia.clear();
            incidenciatipo.incidenciaTipo.clear();
        } catch (Exception e) {
        }
    }

}
