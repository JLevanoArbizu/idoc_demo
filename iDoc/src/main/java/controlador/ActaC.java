package controlador;

import dao.ActaImpl;
import dao.ActorImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Acta;
import modelo.Actor;
import org.primefaces.PrimeFaces;
import org.primefaces.model.StreamedContent;

@Named(value = "actaC")
@SessionScoped
public class ActaC implements Serializable {

    Actor detalle, detalleSeleccionado;
    Acta cabecera, cabeceraSeleccionado;

    List<Actor> listaDetalle, listaDetalleFiltrado;
    List<Acta> listaCabecera, listaCabeceraFiltrado;
    List<Actor> listaDetalleR, listaDetalleSeleccionado;
    ActaImpl daoActa;
    ActorImpl daoDetalle;

    StreamedContent reporte;

    public ActaC() {
        detalle = new Actor();
        detalleSeleccionado = new Actor();
        cabeceraSeleccionado = new Acta();
        cabecera = new Acta();
        listaDetalle = new ArrayList<>();
        listaDetalleSeleccionado = new ArrayList<>();
        listaDetalleR = new ArrayList<>();
        listaCabecera = new ArrayList<>();
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
            listaDetalleR = daoDetalle.listar();
            listaCabecera = daoActa.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarActa() throws Exception {
        try {
            listaDetalle = daoDetalle.listar(cabeceraSeleccionado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean comprobar() {
        if (listaDetalleSeleccionado.size() > 0) {
            List<String> listaTipos = new ArrayList<>();
            for (Actor actor : listaDetalleSeleccionado) {
                if (listaTipos.contains(actor.getTIPACT()) == true) {
                    FacesContext.getCurrentInstance().addMessage(
                            null,
                            new FacesMessage("No pueden haber dos Personas con el mismo tipo!")
                    );
                    return false;
                } else {
                    listaTipos.add(actor.getTIPACT());
                }
            }
            return true;
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage("Seleccione almenos un actor")
            );
            return false;
        }

    }

    public void registrar() throws Exception {
        try {
            if (comprobar() == true) {
                if (listaCabecera.contains(cabecera) == false) {
                    daoActa.registrar(cabecera);
                    try {
                        listaCabecera = daoActa.listar();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    cabecera.setIDACTA(listaCabecera.get(listaCabecera.size() - 1).getIDACTA());
                    for (Actor actor : listaDetalleSeleccionado) {
                        actor.setActa(cabecera);
                        daoDetalle.registrar(actor);
                    }
                    PrimeFaces.current().executeScript("enviar('" + cabecera.getLogin().getTrabajador().getPersona().getNOMPER() + "');");
                    listar();
                    listaDetalleSeleccionado.clear();
//                    listaDetalleFiltrado.clear();
                    cabecera.clear();
//                    FacesContext.getCurrentInstance().addMessage(
//                            null,
//                            new FacesMessage("Registro Exitoso")
//                    );
                } else {
                    FacesContext.getCurrentInstance().addMessage(
                            null,
                            new FacesMessage("Ya existen registros")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void eliminar() throws Exception{
        try {
            daoActa.eliminar(cabeceraSeleccionado);
            listar();
            FacesContext.getCurrentInstance().addMessage(
                            null,
                            new FacesMessage("Eliminado correctamente")
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generarReporte() throws Exception {
        try {
            reporte = daoActa.generarReporteIndividualPrev(cabeceraSeleccionado);
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

    public List<Actor> getListaDetalleSeleccionado() {
        return listaDetalleSeleccionado;
    }

    public void setListaDetalleSeleccionado(List<Actor> listaDetalleSeleccionado) {
        this.listaDetalleSeleccionado = listaDetalleSeleccionado;
    }

    public List<Actor> getListaDetalleR() {
        return listaDetalleR;
    }

    public void setListaDetalleR(List<Actor> listaDetalleR) {
        this.listaDetalleR = listaDetalleR;
    }

    public List<Acta> getListaCabecera() {
        return listaCabecera;
    }

    public void setListaCabecera(List<Acta> listaCabecera) {
        this.listaCabecera = listaCabecera;
    }

    public Acta getCabeceraSeleccionado() {
        return cabeceraSeleccionado;
    }

    public void setCabeceraSeleccionado(Acta cabeceraSeleccionado) {
        this.cabeceraSeleccionado = cabeceraSeleccionado;
    }

    public List<Acta> getListaCabeceraFiltrado() {
        return listaCabeceraFiltrado;
    }

    public void setListaCabeceraFiltrado(List<Acta> listaCabeceraFiltrado) {
        this.listaCabeceraFiltrado = listaCabeceraFiltrado;
    }

    public StreamedContent getReporte() {
        return reporte;
    }

    public void setReporte(StreamedContent reporte) {
        this.reporte = reporte;
    }

}
