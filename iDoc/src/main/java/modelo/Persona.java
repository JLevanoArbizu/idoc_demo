package modelo;

import java.util.Objects;


public class Persona {

    String IDPER, APEPATPER, APEMATPER, NOMPER, DNIPER, CODUBI, DISTPER, DIRPER, NACPER, GENPER, ESTPER, COMPLETO;

    @Override
    public String toString() {
        return "Persona{" + "IDPER=" + IDPER + ", APEPATPER=" + APEPATPER + ", APEMATPER=" + APEMATPER + ", NOMPER=" + NOMPER + ", DNIPER=" + DNIPER + ", CODUBI=" + CODUBI + ", DIRPER=" + DIRPER + ", NACPER=" + NACPER + ", GENPER=" + GENPER + ", ESTPER=" + ESTPER + ", COMPLETO=" + COMPLETO + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.IDPER);
        hash = 31 * hash + Objects.hashCode(this.APEPATPER);
        hash = 31 * hash + Objects.hashCode(this.APEMATPER);
        hash = 31 * hash + Objects.hashCode(this.NOMPER);
        hash = 31 * hash + Objects.hashCode(this.DNIPER);
        hash = 31 * hash + Objects.hashCode(this.CODUBI);
        hash = 31 * hash + Objects.hashCode(this.DIRPER);
        hash = 31 * hash + Objects.hashCode(this.NACPER);
        hash = 31 * hash + Objects.hashCode(this.GENPER);
        hash = 31 * hash + Objects.hashCode(this.ESTPER);
        hash = 31 * hash + Objects.hashCode(this.COMPLETO);
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
        final Persona other = (Persona) obj;
        if (!Objects.equals(this.IDPER, other.IDPER)) {
            return false;
        }
        if (!Objects.equals(this.APEPATPER, other.APEPATPER)) {
            return false;
        }
        if (!Objects.equals(this.APEMATPER, other.APEMATPER)) {
            return false;
        }
        if (!Objects.equals(this.NOMPER, other.NOMPER)) {
            return false;
        }
        if (!Objects.equals(this.DNIPER, other.DNIPER)) {
            return false;
        }
        if (!Objects.equals(this.CODUBI, other.CODUBI)) {
            return false;
        }
        if (!Objects.equals(this.DIRPER, other.DIRPER)) {
            return false;
        }
        if (!Objects.equals(this.NACPER, other.NACPER)) {
            return false;
        }
        if (!Objects.equals(this.GENPER, other.GENPER)) {
            return false;
        }
        if (!Objects.equals(this.ESTPER, other.ESTPER)) {
            return false;
        }
        if (!Objects.equals(this.COMPLETO, other.COMPLETO)) {
            return false;
        }
        return true;
    }

    public String getCOMPLETO() {
        return COMPLETO;
    }

    public void setCOMPLETO(String COMPLETO) {
        this.COMPLETO = COMPLETO;
    }

    public String getIDPER() {
        return IDPER;
    }

    public void setIDPER(String IDPER) {
        this.IDPER = IDPER;
    }

    public String getAPEPATPER() {
        return APEPATPER;
    }

    public void setAPEPATPER(String APEPATPER) {
        this.APEPATPER = APEPATPER;
    }

    public String getAPEMATPER() {
        return APEMATPER;
    }

    public void setAPEMATPER(String APEMATPER) {
        this.APEMATPER = APEMATPER;
    }

    public String getNOMPER() {
        return NOMPER;
    }

    public void setNOMPER(String NOMPER) {
        this.NOMPER = NOMPER;
    }

    public String getDNIPER() {
        return DNIPER;
    }

    public void setDNIPER(String DNIPER) {
        this.DNIPER = DNIPER;
    }

    public String getCODUBI() {
        return CODUBI;
    }

    public void setCODUBI(String CODUBI) {
        this.CODUBI = CODUBI;
    }

    public String getDIRPER() {
        return DIRPER;
    }

    public void setDIRPER(String DIRPER) {
        this.DIRPER = DIRPER;
    }

    public String getNACPER() {
        return NACPER;
    }

    public void setNACPER(String NACPER) {
        this.NACPER = NACPER;
    }

    public String getGENPER() {
        return GENPER;
    }

    public void setGENPER(String GENPER) {
        this.GENPER = GENPER;
    }

    public String getESTPER() {
        return ESTPER;
    }

    public void setESTPER(String ESTPER) {
        this.ESTPER = ESTPER;
    }

    public void clear() {
        this.IDPER = null;
        this.APEPATPER = null;
        this.APEMATPER = null;
        this.NOMPER = null;
        this.DNIPER = null;
        this.CODUBI = null;
        this.DIRPER = null;
        this.NACPER = null;
        this.GENPER = null;
        this.ESTPER = null;
    }
}
