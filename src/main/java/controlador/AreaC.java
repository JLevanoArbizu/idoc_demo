package controlador;

import dao.AreaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import modelo.Area;
import org.primefaces.PrimeFaces;

@Named(value = "areaC")
@SessionScoped
public class AreaC implements Serializable {

    Area area, areaSeleccionada;
    List<Area> lista, listaFiltrado;
    AreaImpl daoArea;

    public AreaC() {
        area = new Area();
        areaSeleccionada = new Area();
        lista = new ArrayList<>();
        listaFiltrado = new ArrayList<>();
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
//                listar();
                area.clear();
                PrimeFaces.current().executeScript("enviar('" + "Area" + "');");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editar() throws Exception {
        try {
            daoArea.editar(areaSeleccionada);
            PrimeFaces.current().executeScript("enviar('" + "Area" + "');");
//            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminar() throws Exception {
        try {
            daoArea.eliminar(areaSeleccionada);
            PrimeFaces.current().executeScript("enviar('" + "Area" + "');");
//            listar();
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

    public List<Area> getLista() {
        return lista;
    }

    public void setLista(List<Area> lista) {
        this.lista = lista;
    }

    public List<Area> getListaFiltrado() {
        return listaFiltrado;
    }

    public void setListaFiltrado(List<Area> listaFiltrado) {
        this.listaFiltrado = listaFiltrado;
    }

}
