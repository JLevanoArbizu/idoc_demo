package modelo;

import java.sql.Date;
import java.util.Objects;

public class Trabajador {

    Persona persona;
    Area area;
    String IDTRAB, ESTTRAB;
    Date FECINITRAB, FECFINTRAB;
    java.util.Date FECINITRAB_T, FECFINTRAB_T;

    public void clear() {
        this.persona = null;
        this.area = null;
        this.IDTRAB = null;
        this.ESTTRAB = null;
        this.FECINITRAB = null;
        this.FECFINTRAB = null;
        this.FECINITRAB_T = null;
        this.FECFINTRAB_T = null;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.persona);
        hash = 37 * hash + Objects.hashCode(this.area);
        hash = 37 * hash + Objects.hashCode(this.IDTRAB);
        hash = 37 * hash + Objects.hashCode(this.ESTTRAB);
        hash = 37 * hash + Objects.hashCode(this.FECINITRAB);
        hash = 37 * hash + Objects.hashCode(this.FECFINTRAB);
        hash = 37 * hash + Objects.hashCode(this.FECINITRAB_T);
        hash = 37 * hash + Objects.hashCode(this.FECFINTRAB_T);
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
        final Trabajador other = (Trabajador) obj;
        if (!Objects.equals(this.IDTRAB, other.IDTRAB)) {
            return false;
        }
        if (!Objects.equals(this.ESTTRAB, other.ESTTRAB)) {
            return false;
        }
        if (!Objects.equals(this.persona, other.persona)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.FECINITRAB, other.FECINITRAB)) {
            return false;
        }
        if (!Objects.equals(this.FECFINTRAB, other.FECFINTRAB)) {
            return false;
        }
        if (!Objects.equals(this.FECINITRAB_T, other.FECINITRAB_T)) {
            return false;
        }
        if (!Objects.equals(this.FECFINTRAB_T, other.FECFINTRAB_T)) {
            return false;
        }
        return true;
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

    public java.util.Date getFECINITRAB_T() {
        return FECINITRAB_T;
    }

    public void setFECINITRAB_T(java.util.Date FECINITRAB_T) {
        this.FECINITRAB_T = FECINITRAB_T;
    }

    public java.util.Date getFECFINTRAB_T() {
        return FECFINTRAB_T;
    }

    public void setFECFINTRAB_T(java.util.Date FECFINTRAB_T) {
        this.FECFINTRAB_T = FECFINTRAB_T;
    }

    @Override
    public String toString() {
        return "Trabajador{" + "persona=" + persona + ", area=" + area + ", IDTRAB=" + IDTRAB + ", ESTTRAB=" + ESTTRAB + ", FECINITRAB=" + FECINITRAB + ", FECFINTRAB=" + FECFINTRAB + ", FECINITRAB_T=" + FECINITRAB_T + ", FECFINTRAB_T=" + FECFINTRAB_T + '}';
    }


}
