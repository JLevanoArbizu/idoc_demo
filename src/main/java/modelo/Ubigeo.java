package modelo;

import java.util.Objects;

public class Ubigeo {

    private String CODUBI, DEPUBI, PROVUBI, DISTUBI;

    public void clear() {
        this.CODUBI = null;
        this.DEPUBI = null;
        this.PROVUBI = null;
        this.DISTUBI = null;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.DEPUBI);
        hash = 97 * hash + Objects.hashCode(this.PROVUBI);
        hash = 97 * hash + Objects.hashCode(this.DISTUBI);
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
        final Ubigeo other = (Ubigeo) obj;
        if (!Objects.equals(this.DEPUBI, other.DEPUBI)) {
            return false;
        }
        if (!Objects.equals(this.PROVUBI, other.PROVUBI)) {
            return false;
        }
        if (!Objects.equals(this.DISTUBI, other.DISTUBI)) {
            return false;
        }
        return true;
    }

    public String getCODUBI() {
        return CODUBI;
    }

    public void setCODUBI(String CODUBI) {
        this.CODUBI = CODUBI;
    }

    public String getDEPUBI() {
        return DEPUBI;
    }

    public void setDEPUBI(String DEPUBI) {
        this.DEPUBI = DEPUBI;
    }

    public String getPROVUBI() {
        return PROVUBI;
    }

    public void setPROVUBI(String PROVUBI) {
        this.PROVUBI = PROVUBI;
    }

    public String getDISTUBI() {
        return DISTUBI;
    }

    public void setDISTUBI(String DISTUBI) {
        this.DISTUBI = DISTUBI;
    }

}
