package servicios;

import javax.faces.context.FacesContext;
import modelo.Login;

public class SesionS {

    public static Login getSesion() {
        return (Login) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");
    }
}
