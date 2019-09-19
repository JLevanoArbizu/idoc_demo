package modelo;

import java.util.Date;
import java.util.Objects;

public class Incidencia {
    
    private int IDINC;
    private String MOTINC, ESTINC = "A";
    private Date FECINC = new Date(119, 3, 1);
    private Acta acta = new Acta();
    private IncidenciaTipo tipoIncidencia = new IncidenciaTipo();

    public void clear() {
//        this.IDINC = 0;
        this.MOTINC = null;
        this.ESTINC = null;
        this.tipoIncidencia.clear();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.MOTINC);
        hash = 23 * hash + Objects.hashCode(this.ESTINC);
        hash = 23 * hash + Objects.hashCode(this.FECINC);
        hash = 23 * hash + Objects.hashCode(this.tipoIncidencia);
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
        final Incidencia other = (Incidencia) obj;
        if (!Objects.equals(this.MOTINC, other.MOTINC)) {
            return false;
        }
        if (!Objects.equals(this.ESTINC, other.ESTINC)) {
            return false;
        }
        if (!Objects.equals(this.FECINC, other.FECINC)) {
            return false;
        }
        if (!Objects.equals(this.tipoIncidencia, other.tipoIncidencia)) {
            return false;
        }
        return true;
    }

    public int getIDINC() {
        return IDINC;
    }

    public void setIDINC(int IDINC) {
        this.IDINC = IDINC;
    }

    public String getMOTINC() {
        return MOTINC;
    }

    public void setMOTINC(String MOTINC) {
        this.MOTINC = MOTINC;
    }

    public String getESTINC() {
        return ESTINC;
    }

    public void setESTINC(String ESTINC) {
        this.ESTINC = ESTINC;
    }

    public Date getFECINC() {
        return FECINC;
    }

    public void setFECINC(Date FECINC) {
        this.FECINC = FECINC;
    }

    public Acta getActa() {
        return acta;
    }

    public void setActa(Acta acta) {
        this.acta = acta;
    }

    public IncidenciaTipo getTipoIncidencia() {
        return tipoIncidencia;
    }

    public void setTipoIncidencia(IncidenciaTipo tipoIncidencia) {
        this.tipoIncidencia = tipoIncidencia;
    }
    
    
}
