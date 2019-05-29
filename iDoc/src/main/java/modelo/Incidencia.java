
package modelo;

import java.sql.Date;



public class Incidencia {
   String IDINC,IDDOC,IDINCTIP,MOTINC,ESTINC,NOMINCTIP;

    public String getNOMINCTIP() {
        return NOMINCTIP;
    }

    public void setNOMINCTIP(String NOMINCTIP) {
        this.NOMINCTIP = NOMINCTIP;
    }

    

    public String getESTINC() {
        return ESTINC;
    }

    public void setESTINC(String ESTINC) {
        this.ESTINC = ESTINC;
    }
   
   Date FECINC;
   java.util.Date fechaTemporal;   

    public java.util.Date getFechaTemporal() {
        return fechaTemporal;
    }

    public void setFechaTemporal(java.util.Date fechaTemporal) {
        this.fechaTemporal = fechaTemporal;
    }

    public String getIDINC() {
        return IDINC;
    }

    public void setIDINC(String IDINC) {
        this.IDINC = IDINC;
    }

    public String getIDDOC() {
        return IDDOC;
    }

    public void setIDDOC(String IDDOC) {
        this.IDDOC = IDDOC;
    }

    public String getIDINCTIP() {
        return IDINCTIP;
    }

    public void setIDINCTIP(String IDINCTIP) {
        this.IDINCTIP = IDINCTIP;
    }

    public Date getFECINC() {
        return FECINC;
    }

    public void setFECINC(Date FECINC) {
        this.FECINC = FECINC;
    }

    public String getMOTINC() {
        return MOTINC;
    }

    public void setMOTINC(String MOTINC) {
        this.MOTINC = MOTINC;
    }
    
    public void clear(){
        this.IDINC = null;
        this.IDDOC = null;
        this.IDINCTIP = null;
        this.fechaTemporal = null;
        this.FECINC = null;
        this.MOTINC = null;
        this.ESTINC = null;
    }
}
