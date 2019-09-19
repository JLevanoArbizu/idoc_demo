package controlador;

import dao.TrabajadorImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Login;
import modelo.Trabajador;
import org.primefaces.PrimeFaces;

@Named(value = "trabajadorC")
@SessionScoped
public class TrabajadorC implements Serializable {

    Trabajador trabajador, trabajadorSeleccionado;
    List<Trabajador> listaTrabajador, listaTrabajadorFiltrado;
    TrabajadorImpl daoTrabajador;

    public TrabajadorC() throws Exception {
        trabajador = new Trabajador();
        trabajadorSeleccionado = new Trabajador();
        listaTrabajador = new ArrayList<>();
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

    public void registrar(Login loginSesion) throws Exception {
        try {
            if (loginSesion.getTIPLOG().charAt(0) != 'S') {
                trabajador.setArea(loginSesion.getTrabajador().getArea());
            }
            if (listaTrabajador.contains(trabajador) == false) {
                daoTrabajador.registrar(trabajador);
//                trabajador.clear();
//                listar();
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage("Registro exitoso")
                );
                PrimeFaces.current().executeScript("enviar('" + "Trabajador" + "');");
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage("El trabajador al que intentó registrar ya lo esá")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editar() throws Exception {
        try {
            daoTrabajador.editar(trabajadorSeleccionado);
//            listar();
            PrimeFaces.current().executeScript("enviar('" + "Trabajador" + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminar() throws Exception {
        try {
            daoTrabajador.eliminar(trabajadorSeleccionado);
//            listar();
            PrimeFaces.current().executeScript("enviar('" + "Trabajador" + "');");
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

    public List<Trabajador> getListaTrabajador() {
        return listaTrabajador;
    }

    public void setListaTrabajador(List<Trabajador> listaTrabajador) {
        this.listaTrabajador = listaTrabajador;
    }

    public List<Trabajador> getListaTrabajadorFiltrado() {
        return listaTrabajadorFiltrado;
    }

    public void setListaTrabajadorFiltrado(List<Trabajador> listaTrabajadorFiltrado) {
        this.listaTrabajadorFiltrado = listaTrabajadorFiltrado;
    }

}
