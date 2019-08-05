package modelo;

import java.util.Objects;

public class Area {

    String IDARE, NOMARE, IDMUN, NOMMUN, IDARE_PADR, NOMARE_PADR, ESTARE;

    @Override
    public String toString() {
        return "Area{" + "IDARE=" + IDARE + ", NOMARE=" + NOMARE + ", IDMUN=" + IDMUN + ", NOMMUN=" + NOMMUN + ", IDARE_PADR=" + IDARE_PADR + ", NOMARE_PADR=" + NOMARE_PADR + ", ESTARE=" + ESTARE + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.IDARE);
        hash = 23 * hash + Objects.hashCode(this.NOMARE);
        hash = 23 * hash + Objects.hashCode(this.IDMUN);
        hash = 23 * hash + Objects.hashCode(this.NOMMUN);
        hash = 23 * hash + Objects.hashCode(this.IDARE_PADR);
        hash = 23 * hash + Objects.hashCode(this.NOMARE_PADR);
        hash = 23 * hash + Objects.hashCode(this.ESTARE);
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
        final Area other = (Area) obj;
        if (!Objects.equals(this.IDARE, other.IDARE)) {
            return false;
        }
        if (!Objects.equals(this.NOMARE, other.NOMARE)) {
            return false;
        }
        if (!Objects.equals(this.IDMUN, other.IDMUN)) {
            return false;
        }
        if (!Objects.equals(this.NOMMUN, other.NOMMUN)) {
            return false;
        }
        if (!Objects.equals(this.IDARE_PADR, other.IDARE_PADR)) {
            return false;
        }
        if (!Objects.equals(this.NOMARE_PADR, other.NOMARE_PADR)) {
            return false;
        }
        if (!Objects.equals(this.ESTARE, other.ESTARE)) {
            return false;
        }
        return true;
    }

    public String getIDARE() {
        return IDARE;
    }

    public void setIDARE(String IDARE) {
        this.IDARE = IDARE;
    }

    public String getNOMARE() {
        return NOMARE;
    }

    public void setNOMARE(String NOMARE) {
        this.NOMARE = NOMARE;
    }

    public String getIDMUN() {
        return IDMUN;
    }

    public void setIDMUN(String IDMUN) {
        this.IDMUN = IDMUN;
    }

    public String getNOMMUN() {
        return NOMMUN;
    }

    public void setNOMMUN(String NOMMUN) {
        this.NOMMUN = NOMMUN;
    }

    public String getIDARE_PADR() {
        return IDARE_PADR;
    }

    public void setIDARE_PADR(String IDARE_PADR) {
        this.IDARE_PADR = IDARE_PADR;
    }

    public String getNOMARE_PADR() {
        return NOMARE_PADR;
    }

    public void setNOMARE_PADR(String NOMARE_PADR) {
        this.NOMARE_PADR = NOMARE_PADR;
    }

    public String getESTARE() {
        return ESTARE;
    }

    public void setESTARE(String ESTARE) {
        this.ESTARE = ESTARE;
    }



    
    public void clear() {
        this.IDARE = null;
        this.NOMARE = null;
        this.IDMUN = null;
        this.IDARE_PADR = null;
        this.ESTARE = null;
    }
}
