package modelo;

import java.util.Objects;

public class Documento {

    String IDDOC, IDMUN, IDLOG, IDPER, NUMLIBDOC, NUMFOLDOC,
            OBSDOC, CODUBI, DIRACT, TIPDOC, ESTDOC;
    java.util.Date FECREGDOC, FECACT;
    String titular, esposa, declarante, papa, mama, celebrante, medico;

    public java.util.Date getFECREGDOC() {
        return FECREGDOC;
    }

    public void setFECREGDOC(java.util.Date FECREGDOC) {
        this.FECREGDOC = FECREGDOC;
    }

    public java.util.Date getFECACT() {
        return FECACT;
    }

    public void setFECACT(java.util.Date FECACT) {
        this.FECACT = FECACT;
    }

    @Override
    public String toString() {
        return "Documento{" + "IDDOC=" + IDDOC + ", IDMUN=" + IDMUN + ", IDLOG=" + IDLOG + ", IDPER=" + IDPER + ", NUMLIBDOC=" + NUMLIBDOC + ", NUMFOLDOC=" + NUMFOLDOC + ", OBSDOC=" + OBSDOC + ", CODUBI=" + CODUBI + ", DIRACT=" + DIRACT + ", TIPDOC=" + TIPDOC + ", ESTDOC=" + ESTDOC + ", FECREGDOC=" + FECREGDOC + ", FECACT=" + FECACT + ", titular=" + titular + ", esposa=" + esposa + ", declarante=" + declarante + ", papa=" + papa + ", mama=" + mama + ", celebrante=" + celebrante + ", medico=" + medico + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.IDDOC);
        hash = 89 * hash + Objects.hashCode(this.IDMUN);
        hash = 89 * hash + Objects.hashCode(this.IDLOG);
        hash = 89 * hash + Objects.hashCode(this.IDPER);
        hash = 89 * hash + Objects.hashCode(this.NUMLIBDOC);
        hash = 89 * hash + Objects.hashCode(this.NUMFOLDOC);
        hash = 89 * hash + Objects.hashCode(this.OBSDOC);
        hash = 89 * hash + Objects.hashCode(this.CODUBI);
        hash = 89 * hash + Objects.hashCode(this.DIRACT);
        hash = 89 * hash + Objects.hashCode(this.TIPDOC);
        hash = 89 * hash + Objects.hashCode(this.ESTDOC);
        hash = 89 * hash + Objects.hashCode(this.FECREGDOC);
        hash = 89 * hash + Objects.hashCode(this.FECACT);
        hash = 89 * hash + Objects.hashCode(this.titular);
        hash = 89 * hash + Objects.hashCode(this.esposa);
        hash = 89 * hash + Objects.hashCode(this.declarante);
        hash = 89 * hash + Objects.hashCode(this.papa);
        hash = 89 * hash + Objects.hashCode(this.mama);
        hash = 89 * hash + Objects.hashCode(this.celebrante);
        hash = 89 * hash + Objects.hashCode(this.medico);
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
        final Documento other = (Documento) obj;
        if (!Objects.equals(this.IDDOC, other.IDDOC)) {
            return false;
        }
        if (!Objects.equals(this.IDMUN, other.IDMUN)) {
            return false;
        }
        if (!Objects.equals(this.IDLOG, other.IDLOG)) {
            return false;
        }
        if (!Objects.equals(this.IDPER, other.IDPER)) {
            return false;
        }
        if (!Objects.equals(this.NUMLIBDOC, other.NUMLIBDOC)) {
            return false;
        }
        if (!Objects.equals(this.NUMFOLDOC, other.NUMFOLDOC)) {
            return false;
        }
        if (!Objects.equals(this.OBSDOC, other.OBSDOC)) {
            return false;
        }
        if (!Objects.equals(this.CODUBI, other.CODUBI)) {
            return false;
        }
        if (!Objects.equals(this.DIRACT, other.DIRACT)) {
            return false;
        }
        if (!Objects.equals(this.TIPDOC, other.TIPDOC)) {
            return false;
        }
        if (!Objects.equals(this.ESTDOC, other.ESTDOC)) {
            return false;
        }
        if (!Objects.equals(this.FECREGDOC, other.FECREGDOC)) {
            return false;
        }
        if (!Objects.equals(this.FECACT, other.FECACT)) {
            return false;
        }
        if (!Objects.equals(this.titular, other.titular)) {
            return false;
        }
        if (!Objects.equals(this.esposa, other.esposa)) {
            return false;
        }
        if (!Objects.equals(this.declarante, other.declarante)) {
            return false;
        }
        if (!Objects.equals(this.papa, other.papa)) {
            return false;
        }
        if (!Objects.equals(this.mama, other.mama)) {
            return false;
        }
        if (!Objects.equals(this.celebrante, other.celebrante)) {
            return false;
        }
        if (!Objects.equals(this.medico, other.medico)) {
            return false;
        }
        return true;
    }

    public String getIDDOC() {
        return IDDOC;
    }

    public void setIDDOC(String IDDOC) {
        this.IDDOC = IDDOC;
    }

    public String getIDMUN() {
        return IDMUN;
    }

    public void setIDMUN(String IDMUN) {
        this.IDMUN = IDMUN;
    }

    public String getIDLOG() {
        return IDLOG;
    }

    public void setIDLOG(String IDLOG) {
        this.IDLOG = IDLOG;
    }

    public String getIDPER() {
        return IDPER;
    }

    public void setIDPER(String IDPER) {
        this.IDPER = IDPER;
    }

    public String getNUMLIBDOC() {
        return NUMLIBDOC;
    }

    public void setNUMLIBDOC(String NUMLIBDOC) {
        this.NUMLIBDOC = NUMLIBDOC;
    }

    public String getNUMFOLDOC() {
        return NUMFOLDOC;
    }

    public void setNUMFOLDOC(String NUMFOLDOC) {
        this.NUMFOLDOC = NUMFOLDOC;
    }

    public String getOBSDOC() {
        return OBSDOC;
    }

    public void setOBSDOC(String OBSDOC) {
        this.OBSDOC = OBSDOC;
    }

    public String getCODUBI() {
        return CODUBI;
    }

    public void setCODUBI(String CODUBI) {
        this.CODUBI = CODUBI;
    }

    public String getDIRACT() {
        return DIRACT;
    }

    public void setDIRACT(String DIRACT) {
        this.DIRACT = DIRACT;
    }

    public String getTIPDOC() {
        return TIPDOC;
    }

    public void setTIPDOC(String TIPDOC) {
        this.TIPDOC = TIPDOC;
    }

    public String getESTDOC() {
        return ESTDOC;
    }

    public void setESTDOC(String ESTDOC) {
        this.ESTDOC = ESTDOC;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getEsposa() {
        return esposa;
    }

    public void setEsposa(String esposa) {
        this.esposa = esposa;
    }

    public String getDeclarante() {
        return declarante;
    }

    public void setDeclarante(String declarante) {
        this.declarante = declarante;
    }

    public String getPapa() {
        return papa;
    }

    public void setPapa(String papa) {
        this.papa = papa;
    }

    public String getMama() {
        return mama;
    }

    public void setMama(String mama) {
        this.mama = mama;
    }

    public String getCelebrante() {
        return celebrante;
    }

    public void setCelebrante(String celebrante) {
        this.celebrante = celebrante;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public void clear() {
        this.IDDOC = null;
        this.IDMUN = null;
        this.IDLOG = null;
        this.IDPER = null;
        this.NUMLIBDOC = null;
        this.NUMFOLDOC = null;
        this.OBSDOC = null;
        this.CODUBI = null;
        this.DIRACT = null;
        this.TIPDOC = null;
        this.ESTDOC = null;

        this.FECREGDOC = null;
        this.FECACT = null;
        this.titular = null;
        this.esposa = null;
        this.declarante = null;
        this.papa = null;
        this.mama = null;
        this.celebrante = null;
        this.medico = null;

    }
}
