package controlador;

import dao.IncidenciaImpl;
import java.io.Serializable;
import java.util.HashSet;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import modelo.Incidencia;

@Named(value = "incidenciaC")
@SessionScoped
public class IncidenciaC implements Serializable {

    Incidencia incidencia, incidenciaSeleccionado;
    HashSet<Incidencia> lista, listaFiltrado;
    IncidenciaImpl daoIncidencia;

    public IncidenciaC() {
        incidencia = new Incidencia();
        incidenciaSeleccionado = new Incidencia();
        lista = new HashSet<>();
        listaFiltrado = new HashSet<>();
        daoIncidencia = new IncidenciaImpl();
    }

    @PostConstruct
    public void onInit() {
        try {
            listar();
        } catch (Exception e) {
        }
    }

    public void listar() throws Exception {
        try {
            lista = daoIncidencia.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrar() throws Exception {
        try {
            if (lista.contains(incidencia) == false) {
                daoIncidencia.registrar(incidencia);
                listar();
                incidencia.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editar() throws Exception {
        try {
            daoIncidencia.editar(incidenciaSeleccionado);
            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminar() throws Exception {
        try {
            daoIncidencia.eliminar(incidenciaSeleccionado);
            listar();
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

    public HashSet<Incidencia> getLista() {
        return lista;
    }

    public void setLista(HashSet<Incidencia> lista) {
        this.lista = lista;
    }

    public HashSet<Incidencia> getListaFiltrado() {
        return listaFiltrado;
    }

    public void setListaFiltrado(HashSet<Incidencia> listaFiltrado) {
        this.listaFiltrado = listaFiltrado;
    }

}
