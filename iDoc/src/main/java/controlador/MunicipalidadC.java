package controlador;

import dao.MunicipalidadImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import modelo.Municipalidad;
import modelo.Ubigeo;

@Named(value = "municipalidadC")
@SessionScoped
public class MunicipalidadC extends UbigeoC implements Serializable {

    List<Municipalidad> listaMunicipalidad;

    public List<Municipalidad> getListaMunicipalidad() {
        return listaMunicipalidad;
    }

    public void setListaMunicipalidad(List<Municipalidad> listaMunicipalidad) {
        this.listaMunicipalidad = listaMunicipalidad;
    }

    public Municipalidad getMunicipalidad() {
        return municipalidad;
    }

    public void setMunicipalidad(Municipalidad municipalidad) {
        this.municipalidad = municipalidad;
    }

    public List<Ubigeo> getListaUbigeo() {
        return listaUbigeo;
    }

    public void setListaUbigeo(List<Ubigeo> listaUbigeo) {
        this.listaUbigeo = listaUbigeo;
    }

    public Ubigeo getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(Ubigeo ubigeo) {
        this.ubigeo = ubigeo;
    }

    Municipalidad municipalidad;
    
    MunicipalidadImpl daoMunicipalidad;
    
    public MunicipalidadC() throws Exception {
        listaMunicipalidad = new ArrayList<>();
        municipalidad = new Municipalidad();
        
        daoMunicipalidad = new MunicipalidadImpl();
    }
    
    @PostConstruct
    public void initM(){
        try {
            listarMunicipalidad();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
    
    public void registrar() throws Exception{
        try {
            setearCodigoUbigeo();
            daoMunicipalidad.registrar(municipalidad);
            FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado Correctamente", null));
            listarMunicipalidad();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void editar() throws Exception{
        try {
            setearCodigoUbigeo();
            daoMunicipalidad.editar(municipalidad);
            FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Editado Correctamente", null));
            listarMunicipalidad();
        } catch (Exception e) {
        }
    }
    public void eliminar(Municipalidad muni) throws Exception{
        try {
            daoMunicipalidad.eliminar(muni);
            FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado Correctamente", null));
            listarMunicipalidad();
        } catch (Exception e) {
        }
    }
    
    public void listarMunicipalidad() throws Exception{
        try {
            listaMunicipalidad = daoMunicipalidad.listar();
            List<Municipalidad> listaTemp = new ArrayList<>();
            for (Municipalidad nextMunicipalidad : listaMunicipalidad) {
                for (Ubigeo nextUbigeo : listaUbigeo) {
                    if (nextMunicipalidad.getCODUBI().equals(nextUbigeo.getCODUBI())) {
                        nextMunicipalidad.setCODUBI(nextUbigeo.getDISTUBI());
                        nextMunicipalidad.setESTMUN(nextMunicipalidad.getESTMUN().equals("A")?"Activo":"Inactivo");
                        listaTemp.add(nextMunicipalidad);                        
                    }
                }
            }
            listaMunicipalidad = listaTemp;
            
            municipalidad.clear();
            ubigeo.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Municipalidad obtenerIdMunicipalidad() throws Exception{
        return daoMunicipalidad.obtenerCodigo(listaMunicipalidad, municipalidad);
    }
    public void setearCodigoUbigeo() throws Exception{
        ubigeo.setDISTUBI(municipalidad.getCODUBI());
        municipalidad.setCODUBI(obtenerCodigoUbigeo().getCODUBI());
    }
    
    public List<String> buscarMunicipalidad(String nombre) throws Exception {
        return daoMunicipalidad.buscar(nombre, listaMunicipalidad);
    }
}
