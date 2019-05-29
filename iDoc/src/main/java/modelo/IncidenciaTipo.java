package modelo;


public class IncidenciaTipo {

     String IDINCTIP, NOMINCTIP, TIPINCTIP, LEYINCTIP, ESTINCTIP;

    public String getIDINCTIP() {
        return IDINCTIP;
    }

    public void setIDINCTIP(String IDINCTIP) {
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

    public void clear() {
        this.IDINCTIP = null;
        this.NOMINCTIP = null;
        this.TIPINCTIP = null;
        this.LEYINCTIP = null;
        this.ESTINCTIP = null;
    }
}
