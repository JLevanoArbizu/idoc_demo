package controlador;

import dao.SugerenciaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import modelo.Sugerencia;
import org.primefaces.PrimeFaces;

@Named(value = "sugerenciaC")
@SessionScoped
public class SugerenciaC implements Serializable {

    Sugerencia sugerencia, sugerenciaSeleccionado;
    List<Sugerencia> sugerencias;
    SugerenciaImpl dao;

    public SugerenciaC() {
        sugerencia = new Sugerencia();
        sugerenciaSeleccionado = new Sugerencia();
        sugerencias = new ArrayList<>();
        dao = new SugerenciaImpl();
    }

    @PostConstruct
    public void init() {
        try {
            listar();
        } catch (Exception e) {
        }
    }

    public void listar() throws Exception {
        try {
            sugerencias = dao.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrar() throws Exception {
        try {
            dao.registrar(sugerencia);
            sugerencia.clear();
            PrimeFaces.current().executeScript("enviar('" + "Sugerencia" + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editar() throws Exception {
        try {
            dao.editar(sugerenciaSeleccionado);
            PrimeFaces.current().executeScript("enviar('" + "Sugerencia" + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Sugerencia getSugerencia() {
        return sugerencia;
    }

    public void setSugerencia(Sugerencia sugerencia) {
        this.sugerencia = sugerencia;
    }

    public Sugerencia getSugerenciaSeleccionado() {
        return sugerenciaSeleccionado;
    }

    public void setSugerenciaSeleccionado(Sugerencia sugerenciaSeleccionado) {
        this.sugerenciaSeleccionado = sugerenciaSeleccionado;
    }

    public List<Sugerencia> getSugerencias() {
        return sugerencias;
    }

    public void setSugerencias(List<Sugerencia> sugerencias) {
        this.sugerencias = sugerencias;
    }

}
