package modelo;

import java.util.Objects;

public class IncidenciaTipo {

    private int IDINCTIP;
    private String NOMINCTIP, TIPINCTIP, LEYINCTIP, ESTINCTIP = "A";

    public void clear() {
        this.IDINCTIP = 0;
        this.NOMINCTIP = null;
        this.TIPINCTIP = null;
        this.LEYINCTIP = null;
        this.ESTINCTIP = null;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.LEYINCTIP);
        hash = 83 * hash + Objects.hashCode(this.ESTINCTIP);
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
        final IncidenciaTipo other = (IncidenciaTipo) obj;
        if (!Objects.equals(this.LEYINCTIP, other.LEYINCTIP)) {
            return false;
        }
        if (!Objects.equals(this.ESTINCTIP, other.ESTINCTIP)) {
            return false;
        }
        return true;
    }

    public int getIDINCTIP() {
        return IDINCTIP;
    }

    public void setIDINCTIP(int IDINCTIP) {
        this.IDINCTIP = IDINCTIP;
    }

    public String getNOMINCTIP() {
        return NOMINCTIP;
    }

    public void setNOMINCTIP(String NOMINCTIP) {
        this.NOMINCTIP = NOMINCTIP;
    }

    public String getTIPINCTIP() {
        return TIPINCTIP;
    }

    public void setTIPINCTIP(String TIPINCTIP) {
        this.TIPINCTIP = TIPINCTIP;
    }

    public String getLEYINCTIP() {
        return LEYINCTIP;
    }

    public void setLEYINCTIP(String LEYINCTIP) {
        this.LEYINCTIP = LEYINCTIP;
    }

    public String getESTINCTIP() {
        return ESTINCTIP;
    }

    public void setESTINCTIP(String ESTINCTIP) {
        this.ESTINCTIP = ESTINCTIP;
    }
    
}
