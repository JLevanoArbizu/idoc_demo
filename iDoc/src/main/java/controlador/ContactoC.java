package controlador;

import dao.ContactoImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import modelo.Contacto;

@Named(value = "contactoC")
@SessionScoped
public class ContactoC implements Serializable {

    Contacto contacto;
    ContactoImpl daoContacto;

    public ContactoC() {
        contacto = new Contacto();
        daoContacto = new ContactoImpl();
    }
    
    @PostConstruct
    public void init(){
        contacto.clear();
    }
    
    public void enviarMensaje(){
        daoContacto.enviarMensaje(contacto);
    }
    
    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

}
