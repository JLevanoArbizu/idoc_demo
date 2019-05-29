package controlador;

import dao.PersonaImpl;
import modelo.Persona;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Ubigeo;

@Named(value = "personaC")
@SessionScoped
public class PersonaC extends UbigeoC implements Serializable {

    Persona persona;

    List<Persona> listaPersona;

    PersonaImpl daoPersona;

    public PersonaC() throws Exception {
        daoPersona = new PersonaImpl();
        persona = new Persona();
        listaPersona = new ArrayList<>();
        listarPersonas();
    }

    public void editarPersona() throws Exception {
        try {
            seterCodigoUbigeo();
            daoPersona.editar(persona);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Editado Correctamente", null));
            listarPersonas();
        } catch (Exception e) {
        }
    }
    
    public void eliminarPersona(Persona person) throws Exception{
        try {
            daoPersona.eliminar(person);
        } catch (Exception e) {
        }
    }
    public void registrarPersona() throws Exception {
        try {
            if (!daoPersona.existe(listaPersona, persona)) {
                seterCodigoUbigeo();
                daoPersona.registrar(persona);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Exitoso", null));
                listarPersonas();
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro Fallido", null));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro Fallido", null));
            e.printStackTrace();
        }
    }

    public void listarPersonas() throws Exception {
        try {
            listaPersona = daoPersona.listar();
            List<Persona> listaTemp = new ArrayList<>();
            for (Persona nextPersona : listaPersona) {
                for (Ubigeo nextUbigeo : listaUbigeo) {
                    if (nextPersona.getCODUBI().equals(nextUbigeo.getCODUBI())) {
                        nextPersona.setCODUBI(nextUbigeo.getDISTUBI());
                        listaTemp.add(nextPersona);
                    }
                }
            }
            listaPersona = listaTemp;
            persona.clear();
            ubigeo.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void seterCodigoUbigeo() throws Exception {
        ubigeo.setDISTUBI(persona.getCODUBI());
        persona.setCODUBI(obtenerCodigoUbigeo().getCODUBI());
    }

    public Persona obtenerCodigo() throws Exception {
        return daoPersona.obtenerCodigo(listaPersona, persona);
    }

    public List<String> buscarPersona(String query) throws Exception {
        return daoPersona.buscar(query, listaPersona);
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Persona> getListaPersona() {
        return listaPersona;
    }

    public void setListaPersona(List<Persona> listaPersona) {
        this.listaPersona = listaPersona;
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
}
