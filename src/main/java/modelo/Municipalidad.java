package modelo;

import java.util.Objects;

public class Municipalidad {

    private int IDMUN;
    private String DIRMUN, NOMMUN, ESTMUN = "A", TLFMUN;
    private Ubigeo ubigeo = new Ubigeo();

    public void clear() {
        this.IDMUN = 0;
        this.DIRMUN = null;
        this.NOMMUN = null;
        this.ESTMUN = "A";
        this.TLFMUN = null;
        this.ubigeo.clear();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.ubigeo);
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
        final Municipalidad other = (Municipalidad) obj;
        if (!Objects.equals(this.ubigeo, other.ubigeo)) {
            return false;
        }
        return true;
    }

    public Ubigeo getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(Ubigeo ubigeo) {
        this.ubigeo = ubigeo;
    }

    public int getIDMUN() {
        return IDMUN;
    }

    public void setIDMUN(int IDMUN) {
        this.IDMUN = IDMUN;
    }

    public String getDIRMUN() {
        return DIRMUN;
    }

    public void setDIRMUN(String DIRMUN) {
        this.DIRMUN = DIRMUN;
    }

    public String getNOMMUN() {
        return NOMMUN;
    }

    public void setNOMMUN(String NOMMUN) {
        this.NOMMUN = NOMMUN;
    }

    public String getESTMUN() {
        return ESTMUN;
    }

    public void setESTMUN(String ESTMUN) {
        this.ESTMUN = ESTMUN;
    }

    public String getTLFMUN() {
        return TLFMUN;
    }

    public void setTLFMUN(String TLFMUN) {
        this.TLFMUN = TLFMUN;
    }

}
