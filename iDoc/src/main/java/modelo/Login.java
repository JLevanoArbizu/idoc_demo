package modelo;

public class Login {
    
    Trabajador trabajador;
    String IDLOG,IDTRAB, USRLOG, PSSWLOG, ESTLOG, TIPLOG;

    public void clear() {
        this.trabajador = null;
        this.IDLOG = null;
        this.USRLOG = null;
        this.PSSWLOG = null;
        this.ESTLOG = null;
        this.TIPLOG = null;
        this.IDTRAB = null;
    }

    @Override
    public String toString() {
        return "Login{" + "trabajador=" + trabajador + ", IDLOG=" + IDLOG + ", USRLOG=" + USRLOG + ", PSSWLOG=" + PSSWLOG + ", ESTLOG=" + ESTLOG + ", TIPLOG=" + TIPLOG + '}';
    }

    public String getIDTRAB() {
        return IDTRAB;
    }

    public void setIDTRAB(String IDTRAB) {
        this.IDTRAB = IDTRAB;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public String getIDLOG() {
        return IDLOG;
    }

    public void setIDLOG(String IDLOG) {
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
}
