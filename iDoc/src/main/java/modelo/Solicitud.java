package modelo;

import java.sql.Date;
import java.util.Objects;


public class Solicitud {
    Login login;
    Persona persona;
    Acta acta;
    Date FECSOL;
    java.util.Date FECSOL_T;
    String ESTSOL;
    
    public void clear(){
        this.login = null;
        this.persona = null;
        this.acta = null;
        this.FECSOL = null;
        this.FECSOL_T = null;
        this.ESTSOL = null;
    }

    @Override
    public String toString() {
        return "Solicitud{" + "login=" + login + ", persona=" + persona + ", acta=" + acta + ", FECSOL=" + FECSOL + ", FECSOL_T=" + FECSOL_T + ", ESTSOL=" + ESTSOL + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.login);
        hash = 41 * hash + Objects.hashCode(this.persona);
        hash = 41 * hash + Objects.hashCode(this.acta);
        hash = 41 * hash + Objects.hashCode(this.FECSOL);
        hash = 41 * hash + Objects.hashCode(this.FECSOL_T);
        hash = 41 * hash + Objects.hashCode(this.ESTSOL);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Solicitud other = (Solicitud) obj;
        if (!Objects.equals(this.ESTSOL, other.ESTSOL)) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.persona, other.persona)) {
            return false;
        }
        if (!Objects.equals(this.acta, other.acta)) {
            return false;
        }
        if (!Objects.equals(this.FECSOL, other.FECSOL)) {
            return false;
        }
        if (!Objects.equals(this.FECSOL_T, other.FECSOL_T)) {
            return false;
        }
        return true;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Acta getActa() {
        return acta;
    }

    public void setActa(Acta acta) {
        this.acta = acta;
    }

    public Date getFECSOL() {
        return FECSOL;
    }

    public void setFECSOL(Date FECSOL) {
        this.FECSOL = FECSOL;
    }

    public java.util.Date getFECSOL_T() {
        return FECSOL_T;
    }

    public void setFECSOL_T(java.util.Date FECSOL_T) {
        this.FECSOL_T = FECSOL_T;
    }

    public String getESTSOL() {
        return ESTSOL;
    }

    public void setESTSOL(String ESTSOL) {
        this.ESTSOL = ESTSOL;
    }
    
}