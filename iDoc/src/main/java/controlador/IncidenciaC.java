package controlador;

import dao.IncidenciaImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import modelo.Acta;
import modelo.Incidencia;

@Named(value = "incidenciaC")
@SessionScoped
public class IncidenciaC implements Serializable {
    
    Incidencia incidencia, incidenciaSeleccionado;
    List<Incidencia> lista, listaFiltrado;
    IncidenciaImpl daoIncidencia;
    
    public IncidenciaC() {
        incidencia = new Incidencia();
        incidenciaSeleccionado = new Incidencia();
        lista = new ArrayList<>();
        listaFiltrado = new ArrayList<>();
        daoIncidencia = new IncidenciaImpl();
    }
    
    public void listar(Acta acta) throws Exception {
        try {
            incidencia.setActa(acta);
            lista = daoIncidencia.listar(incidencia);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void registrar() throws Exception {
        try {
            if (lista.contains(incidencia) == false) {
                daoIncidencia.registrar(incidencia);
                incidencia.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void editar() throws Exception {
        try {
            daoIncidencia.editar(incidenciaSeleccionado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void eliminar() throws Exception {
        try {
            daoIncidencia.eliminar(incidenciaSeleccionado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Incidencia getIncidencia() {
        return incidencia;
    }
    
    public void setIncidencia(Incidencia incidencia) {
        this.incidencia = incidencia;
    }
    
    public Incidencia getIncidenciaSeleccionado() {
        return incidenciaSeleccionado;
    }
    
    public void setIncidenciaSeleccionado(Incidencia incidenciaSeleccionado) {
        this.incidenciaSeleccionado = incidenciaSeleccionado;
    }
    
    public List<Incidencia> getLista() {
        return lista;
    }
    
    public void setLista(List<Incidencia> lista) {
        this.lista = lista;
    }
    
    public List<Incidencia> getListaFiltrado() {
        return listaFiltrado;
    }
    
    public void setListaFiltrado(List<Incidencia> listaFiltrado) {
        this.listaFiltrado = listaFiltrado;
    }
    
}
