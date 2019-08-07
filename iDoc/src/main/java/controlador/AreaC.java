package controlador;

import dao.AreaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import javax.annotation.PostConstruct;
import modelo.Area;

@Named(value = "areaC")
@SessionScoped
public class AreaC implements Serializable {

    Area area, areaSeleccionada;
    HashSet<Area> lista, listaFiltrado;
    AreaImpl daoArea;

    public AreaC() {
        area = new Area();
        areaSeleccionada = new Area();
        lista = new HashSet<>();
        listaFiltrado = new LinkedHashSet<>();
        daoArea = new AreaImpl();
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
            lista = daoArea.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrar() throws Exception {
        try {
            if (lista.contains(area) == false) {
                daoArea.registrar(area);
                listar();
                area.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editar() throws Exception {
        try {
            daoArea.editar(areaSeleccionada);
            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminar() throws Exception {
        try {
            daoArea.eliminar(areaSeleccionada);
            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Area getAreaSeleccionada() {
        return areaSeleccionada;
    }

    public void setAreaSeleccionada(Area areaSeleccionada) {
        this.areaSeleccionada = areaSeleccionada;
    }

    public HashSet<Area> getLista() {
        return lista;
    }

    public void setLista(HashSet<Area> lista) {
        this.lista = lista;
    }

    public HashSet<Area> getListaFiltrado() {
        return listaFiltrado;
    }

    public void setListaFiltrado(HashSet<Area> listaFiltrado) {
        this.listaFiltrado = listaFiltrado;
    }

}
