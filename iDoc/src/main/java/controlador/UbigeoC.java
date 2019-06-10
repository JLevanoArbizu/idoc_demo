package controlador;

import dao.UbigeoImpl;
import java.io.Serializable;

import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import modelo.Ubigeo;
@Named(value = "ubigeoC")
@SessionScoped
public class UbigeoC implements Serializable{

    UbigeoImpl daoUbigeo;

    List<Ubigeo> listaUbigeo;

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

    Ubigeo ubigeo;

    public UbigeoC(){
        try {
            daoUbigeo = new UbigeoImpl();
            listaUbigeo = daoUbigeo.listar();
            ubigeo = new Ubigeo();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
        
    
    public List<String> buscarUbigeo(String distrito) throws Exception {
        return daoUbigeo.buscar(distrito, listaUbigeo);
    }
    
    public String obtenerDepartamento(String distrito){
        for (Ubigeo next : listaUbigeo) {
            if (next.getDISTUBI().equals(distrito)) {
                return next.getPROVUBI();
            }
        }
        return null;
    }
    
    public Ubigeo obtenerCodigoUbigeo() throws Exception{
        return daoUbigeo.obtenerCodigo(listaUbigeo, ubigeo);
    }
}
