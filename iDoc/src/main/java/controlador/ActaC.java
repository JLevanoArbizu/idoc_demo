package controlador;

import dao.ActaImpl;
import dao.ActorImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import modelo.Acta;
import modelo.Actor;

@Named(value = "actaC")
@SessionScoped
public class ActaC implements Serializable {

    Actor detalle, detalleSeleccionado;
    Acta cabecera;

    List<Actor> listaDetalle, listaDetalleFiltrado;

    ActaImpl daoActa;
    ActorImpl daoDetalle;

    public ActaC() {
        detalle = new Actor();
        detalleSeleccionado = new Actor();
        cabecera = new Acta();
        listaDetalle = new ArrayList<>();
        listaDetalleFiltrado = new ArrayList<>();
        daoActa = new ActaImpl();
        daoDetalle = new ActorImpl();
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
            listaDetalle = daoDetalle.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrar() throws Exception {
        try {
            //Analizando
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Actor getDetalle() {
        return detalle;
    }

    public void setDetalle(Actor detalle) {
        this.detalle = detalle;
    }

    public Actor getDetalleSeleccionado() {
        return detalleSeleccionado;
    }

    public void setDetalleSeleccionado(Actor detalleSeleccionado) {
        this.detalleSeleccionado = detalleSeleccionado;
    }

    public Acta getCabecera() {
        return cabecera;
    }

    public void setCabecera(Acta cabecera) {
        this.cabecera = cabecera;
    }

    public List<Actor> getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(List<Actor> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }

    public List<Actor> getListaDetalleFiltrado() {
        return listaDetalleFiltrado;
    }

    public void setListaDetalleFiltrado(List<Actor> listaDetalleFiltrado) {
        this.listaDetalleFiltrado = listaDetalleFiltrado;
    }

}
