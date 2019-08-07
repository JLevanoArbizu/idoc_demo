package controlador;

import dao.UbigeoImpl;
import java.io.Serializable;
import java.util.HashSet;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import modelo.Ubigeo;

@Named(value = "ubigeoC")
@SessionScoped
public class UbigeoC implements Serializable {

    HashSet<Ubigeo> listaUbigeo;
    UbigeoImpl daoUbigeo;

    public UbigeoC() {
        listaUbigeo = new HashSet<>();
        daoUbigeo = new UbigeoImpl();
    }

    @PostConstruct
    public void onInit() {
        try {
            listar();
        } catch (Exception e) {
        }
    }

    public void listar() throws Exception {
        try {
            listaUbigeo = daoUbigeo.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashSet<Ubigeo> getListaUbigeo() {
        return listaUbigeo;
    }

    public void setListaUbigeo(HashSet<Ubigeo> listaUbigeo) {
        this.listaUbigeo = listaUbigeo;
    }

}
