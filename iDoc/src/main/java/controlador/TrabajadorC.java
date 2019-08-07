package controlador;

import dao.TrabajadorImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashSet;
import javax.annotation.PostConstruct;
import modelo.Trabajador;

@Named(value = "trabajadorC")
@SessionScoped
public class TrabajadorC implements Serializable {

    Trabajador trabajador, trabajadorSeleccionado;
    HashSet<Trabajador> listaTrabajador, listaTrabajadorFiltrado;
    TrabajadorImpl daoTrabajador;

    public TrabajadorC() throws Exception {
        trabajador = new Trabajador();
        trabajadorSeleccionado = new Trabajador();
        listaTrabajador = new HashSet<>();
        listaTrabajadorFiltrado = new HashSet<>();
        daoTrabajador = new TrabajadorImpl();
    }

    @PostConstruct
    public void init() {
        try {
            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listar() throws Exception {
        try {
            listaTrabajador = daoTrabajador.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrar() throws Exception {
        try {
            if (listaTrabajador.contains(trabajador) == false) {
                daoTrabajador.registrar(trabajador);
                listar();
                trabajador.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void editar() throws Exception{
        try {
            daoTrabajador.editar(trabajadorSeleccionado);
            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public Trabajador getTrabajadorSeleccionado() {
        return trabajadorSeleccionado;
    }

    public void setTrabajadorSeleccionado(Trabajador trabajadorSeleccionado) {
        this.trabajadorSeleccionado = trabajadorSeleccionado;
    }

    public HashSet<Trabajador> getListaTrabajador() {
        return listaTrabajador;
    }

    public void setListaTrabajador(HashSet<Trabajador> listaTrabajador) {
        this.listaTrabajador = listaTrabajador;
    }

    public HashSet<Trabajador> getListaTrabajadorFiltrado() {
        return listaTrabajadorFiltrado;
    }

    public void setListaTrabajadorFiltrado(HashSet<Trabajador> listaTrabajadorFiltrado) {
        this.listaTrabajadorFiltrado = listaTrabajadorFiltrado;
    }

}
