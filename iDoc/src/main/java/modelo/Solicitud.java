package modelo;

import java.sql.Date;
import java.util.Objects;


public class Solicitud {
    Login login;
    Acta acta;
    String ESTSOL, CODSOL;

    public void clear(){
        this.login = null;
        this.acta = null;
        this.ESTSOL = null;
        this.CODSOL = null;
    }

    @Override
    public String toString() {
        return "Solicitud{" +
                "login=" + login +
                ", acta=" + acta +
                ", ESTSOL='" + ESTSOL + '\'' +
                ", CODSOL='" + CODSOL + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solicitud solicitud = (Solicitud) o;
        return Objects.equals(login, solicitud.login) &&
                Objects.equals(acta, solicitud.acta) &&
                Objects.equals(ESTSOL, solicitud.ESTSOL) &&
                Objects.equals(CODSOL, solicitud.CODSOL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, acta, ESTSOL, CODSOL);
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }


    public Acta getActa() {
        return acta;
    }

    public void setActa(Acta acta) {
        this.acta = acta;
    }

    public String getESTSOL() {
        return ESTSOL;
    }

    public void setESTSOL(String ESTSOL) {
        this.ESTSOL = ESTSOL;
    }

    public String getCODSOL() {
        return CODSOL;
    }

    public void setCODSOL(String CODSOL) {
        this.CODSOL = CODSOL;
    }
}