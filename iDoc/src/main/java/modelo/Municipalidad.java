package modelo;


public class Municipalidad {

    String IDMUN, CODUBI, DIRMUN, NOMMUN, ESTMUN, TLFMUN;

    public String getIDMUN() {
        return IDMUN;
    }

    public void setIDMUN(String IDMUN) {
        this.IDMUN = IDMUN;
    }

    public String getCODUBI() {
        return CODUBI;
    }

    public void setCODUBI(String CODUBI) {
        this.CODUBI = CODUBI;
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

    public void clear() {
        this.IDMUN = null;
        this.CODUBI = null;
        this.DIRMUN = null;
        this.NOMMUN = null;
        this.ESTMUN = null;
        this.TLFMUN = null;
    }
}
