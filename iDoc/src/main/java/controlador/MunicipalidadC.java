package controlador;

import dao.MunicipalidadImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashSet;
import javax.annotation.PostConstruct;
import modelo.Municipalidad;

@Named(value = "municipalidadC")
@SessionScoped
public class MunicipalidadC implements Serializable {

    Municipalidad municipalidad, municipalidadSeleccionada;
    HashSet<Municipalidad> lista, listaFiltrada;
    MunicipalidadImpl daoMunicipalidad;

    public MunicipalidadC() {
        municipalidad = new Municipalidad();
        municipalidadSeleccionada = new Municipalidad();
        lista = new HashSet<>();
        listaFiltrada = new HashSet<>();
        daoMunicipalidad = new MunicipalidadImpl();
    }

    @PostConstruct
    public void onInit() {
        try {
            listar();
        } catch (Exception e) {
        }
    }

    public void registrar() throws Exception {
        try {
            if (lista.contains(municipalidad) == false) {
                daoMunicipalidad.registrar(municipalidad);
                listar();
                municipalidad.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editar() throws Exception {
        try {
            daoMunicipalidad.editar(municipalidadSeleccionada);
            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminar() throws Exception {
        try {
            daoMunicipalidad.eliminar(municipalidadSeleccionada);
            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listar() throws Exception {
        try {
            lista = daoMunicipalidad.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Municipalidad getMunicipalidad() {
        return municipalidad;
    }

    public void setMunicipalidad(Municipalidad municipalidad) {
        this.municipalidad = municipalidad;
    }

    public Municipalidad getMunicipalidadSeleccionada() {
        return municipalidadSeleccionada;
    }

    public void setMunicipalidadSeleccionada(Municipalidad municipalidadSeleccionada) {
        this.municipalidadSeleccionada = municipalidadSeleccionada;
    }

    public HashSet<Municipalidad> getLista() {
        return lista;
    }

    public void setLista(HashSet<Municipalidad> lista) {
        this.lista = lista;
    }

    public HashSet<Municipalidad> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(HashSet<Municipalidad> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

}
