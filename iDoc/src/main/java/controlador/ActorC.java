package controlador;

import dao.ActorImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import modelo.Actor;


@Named(value = "actorC")
@SessionScoped
public class ActorC extends PersonaC implements Serializable {

    List<Actor> listaActores;
    Actor actor;
    ActorImpl daoActor;
    



    public ActorC() throws Exception {
        listaActores = new ArrayList<>();
        actor = new Actor();
        daoActor = new ActorImpl();
    }

    public void registrarActor() throws Exception {
        try {
            actor.setIDPER(obtenerCodigo().getIDPER());
            daoActor.registrar(actor);
            persona.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
