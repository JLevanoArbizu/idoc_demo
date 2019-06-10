package modelo;

import java.sql.Date;

public class Trabajador {

    Persona persona;
    Area area;
    String IDTRAB, ESTTRAB;
    Date FECINITRAB, FECFINTRAB;

    public void clear() {
        this.persona = null;
        this.area = null;
        this.IDTRAB = null;
        this.ESTTRAB = null;
        this.FECINITRAB = null;
        this.FECFINTRAB = null;
    }

    @Override
    public String toString() {
        return "Trabajador{" + "persona=" + persona + ", area=" + area + ", IDTRAB=" + IDTRAB + ", ESTTRAB=" + ESTTRAB + ", FECINITRAB=" + FECINITRAB + ", FECFINTRAB=" + FECFINTRAB + '}';
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getIDTRAB() {
        return IDTRAB;
    }

    public void setIDTRAB(String IDTRAB) {
        this.IDTRAB = IDTRAB;
    }

    public String getESTTRAB() {
        return ESTTRAB;
    }

    public void setESTTRAB(String ESTTRAB) {
        this.ESTTRAB = ESTTRAB;
    }

    public Date getFECINITRAB() {
        return FECINITRAB;
    }

    public void setFECINITRAB(Date FECINITRAB) {
        this.FECINITRAB = FECINITRAB;
    }

    public Date getFECFINTRAB() {
        return FECFINTRAB;
    }

    public void setFECFINTRAB(Date FECFINTRAB) {
        this.FECFINTRAB = FECFINTRAB;
    }

}
