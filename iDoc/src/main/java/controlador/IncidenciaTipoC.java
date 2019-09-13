package controlador;

import dao.IncidenciaTipoImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import modelo.IncidenciaTipo;

@Named(value = "incidenciatipoC")
@SessionScoped
public class IncidenciaTipoC implements Serializable {

    IncidenciaTipo tipoIncidencia, tipoIncidenciaSeleccionado;
    List<IncidenciaTipo> lista, listaFiltrado;
    IncidenciaTipoImpl daoIncidenciaTipo;

    public IncidenciaTipoC() {
        tipoIncidencia = new IncidenciaTipo();
        tipoIncidenciaSeleccionado = new IncidenciaTipo();
        lista = new ArrayList<>();
        daoIncidenciaTipo = new IncidenciaTipoImpl();
    }

    @PostConstruct
    public void onInit() {
        try {
            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listar() throws Exception {
        try {
            lista = daoIncidenciaTipo.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrar() throws Exception {
        try {
            if (lista.contains(tipoIncidencia) == false) {
                daoIncidenciaTipo.registrar(tipoIncidencia);
//                listar();
                tipoIncidencia.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editar() throws Exception {
        try {
            daoIncidenciaTipo.editar(tipoIncidenciaSeleccionado);
//            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void eliminar() throws Exception{
        try {
            daoIncidenciaTipo.eliminar(tipoIncidenciaSeleccionado);
//            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public IncidenciaTipo getTipoIncidencia() {
        return tipoIncidencia;
    }

    public void setTipoIncidencia(IncidenciaTipo tipoIncidencia) {
        this.tipoIncidencia = tipoIncidencia;
    }

    public IncidenciaTipo getTipoIncidenciaSeleccionado() {
        return tipoIncidenciaSeleccionado;
    }

    public void setTipoIncidenciaSeleccionado(IncidenciaTipo tipoIncidenciaSeleccionado) {
        this.tipoIncidenciaSeleccionado = tipoIncidenciaSeleccionado;
    }

    public List<IncidenciaTipo> getLista() {
        return lista;
    }

    public void setLista(List<IncidenciaTipo> lista) {
        this.lista = lista;
    }

    public List<IncidenciaTipo> getListaFiltrado() {
        return listaFiltrado;
    }

    public void setListaFiltrado(List<IncidenciaTipo> listaFiltrado) {
        this.listaFiltrado = listaFiltrado;
    }

   

}
