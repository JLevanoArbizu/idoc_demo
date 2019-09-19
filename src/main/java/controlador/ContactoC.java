package controlador;

import dao.ContactoImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    public void init() {
        contacto.clear();
    }

    public void enviarMensaje() {
        try {
            contacto.setFrom(contacto.getUser());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, daoContacto.enviarMensaje(contacto), null));
            contacto.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

}
