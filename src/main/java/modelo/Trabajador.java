package modelo;

import java.util.Date;
import java.util.Objects;

public class Trabajador {

    private int IDTRAB;
    private String ESTTRAB = "A";
    private Date FECINITRAB = new Date(), FECFINTRAB = new Date();
    private Persona persona = new Persona();
    Area area = new Area();

    public void clear() {
        this.IDTRAB = 0;
        this.ESTTRAB = "A";
        this.persona.clear();
        this.area.clear();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.ESTTRAB);
        hash = 29 * hash + Objects.hashCode(this.persona);
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
        if (!Objects.equals(this.ESTTRAB, other.ESTTRAB)) {
            return false;
        }
        return Objects.equals(this.persona, other.persona);
    }

    public int getIDTRAB() {
        return IDTRAB;
    }

    public void setIDTRAB(int IDTRAB) {
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

}
