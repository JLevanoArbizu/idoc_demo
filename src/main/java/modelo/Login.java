package modelo;

import java.util.Objects;

public class Login {

    private int IDLOG;
    private String USRLOG, PSSWLOG, ESTLOG = "A", TIPLOG;
    private Trabajador trabajador = new Trabajador();

    public void clear() {
        this.IDLOG = 0;
        this.USRLOG = null;
        this.PSSWLOG = null;
        this.ESTLOG = "A";
        this.TIPLOG = null;
        this.trabajador.clear();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.USRLOG);
        hash = 17 * hash + Objects.hashCode(this.PSSWLOG);
        hash = 17 * hash + Objects.hashCode(this.ESTLOG);
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
        final Login other = (Login) obj;
        if (!Objects.equals(this.USRLOG, other.USRLOG)) {
            return false;
        }
        if (!Objects.equals(this.PSSWLOG, other.PSSWLOG)) {
            return false;
        }
        if (!Objects.equals(this.ESTLOG, other.ESTLOG)) {
            return false;
        }
        return true;
    }

    public int getIDLOG() {
        return IDLOG;
    }

    public void setIDLOG(int IDLOG) {
        this.IDLOG = IDLOG;
    }

    public String getUSRLOG() {
        return USRLOG;
    }

    public void setUSRLOG(String USRLOG) {
        this.USRLOG = USRLOG;
    }

    public String getPSSWLOG() {
        return PSSWLOG;
    }

    public void setPSSWLOG(String PSSWLOG) {
        this.PSSWLOG = PSSWLOG;
    }

    public String getESTLOG() {
        return ESTLOG;
    }

    public void setESTLOG(String ESTLOG) {
        this.ESTLOG = ESTLOG;
    }

    public String getTIPLOG() {
        return TIPLOG;
    }

    public void setTIPLOG(String TIPLOG) {
        this.TIPLOG = TIPLOG;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

}
