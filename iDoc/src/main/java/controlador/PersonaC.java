package controlador;

import dao.PersonaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashSet;
import javax.annotation.PostConstruct;
import modelo.Persona;

@Named(value = "personaC")
@SessionScoped
public class PersonaC implements Serializable {

    Persona persona, personaSeleccionada;
    HashSet<Persona> lista, listaFiltrada;
    PersonaImpl daoPersona;

    public PersonaC() {
        persona = new Persona();
        personaSeleccionada = new Persona();
        lista = new HashSet<>();
        listaFiltrada = new HashSet<>();
        daoPersona = new PersonaImpl();
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
            lista = daoPersona.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrar() throws Exception {
        try {
            if (lista.contains(persona) == false) {
                daoPersona.registrar(persona);
                listar();
                persona.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editar() throws Exception {
        try {
            daoPersona.editar(personaSeleccionada);
            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminar() throws Exception {
        try {
            daoPersona.eliminar(personaSeleccionada);
            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Persona getPersonaSeleccionada() {
        return personaSeleccionada;
    }

    public void setPersonaSeleccionada(Persona personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
    }

    public HashSet<Persona> getLista() {
        return lista;
    }

    public void setLista(HashSet<Persona> lista) {
        this.lista = lista;
    }

    public HashSet<Persona> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(HashSet<Persona> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

}
