package modelo;

import java.util.Objects;

public class Acta {

    String IDACTA, IDMUN, IDLOG, IDPER, NUMLIBACTA, NUMFOLACTA,
            OBSACTA, CODUBI, DIRACT, TIPACTA, ESTACTA;
    java.util.Date FECREGACTA, FECACT;
    String titular, esposa, declarante, papa, mama, celebrante, medico;

    public java.util.Date getFECREGACTA() {
        return FECREGACTA;
    }

    public void setFECREGACTA(java.util.Date FECREGACTA) {
        this.FECREGACTA = FECREGACTA;
    }

    public java.util.Date getFECACT() {
        return FECACT;
    }

    public void setFECACT(java.util.Date FECACT) {
        this.FECACT = FECACT;
    }

    @Override
    public String toString() {
        return "Documento{" + "IDDOC=" + IDACTA + ", IDMUN=" + IDMUN + ", IDLOG=" + IDLOG + ", IDPER=" + IDPER + ", NUMLIBDOC=" + NUMLIBACTA + ", NUMFOLDOC=" + NUMFOLACTA + ", OBSDOC=" + OBSACTA + ", CODUBI=" + CODUBI + ", DIRACT=" + DIRACT + ", TIPDOC=" + TIPACTA + ", ESTDOC=" + ESTACTA + ", FECREGDOC=" + FECREGACTA + ", FECACT=" + FECACT + ", titular=" + titular + ", esposa=" + esposa + ", declarante=" + declarante + ", papa=" + papa + ", mama=" + mama + ", celebrante=" + celebrante + ", medico=" + medico + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.IDACTA);
        hash = 89 * hash + Objects.hashCode(this.IDMUN);
        hash = 89 * hash + Objects.hashCode(this.IDLOG);
        hash = 89 * hash + Objects.hashCode(this.IDPER);
        hash = 89 * hash + Objects.hashCode(this.NUMLIBACTA);
        hash = 89 * hash + Objects.hashCode(this.NUMFOLACTA);
        hash = 89 * hash + Objects.hashCode(this.OBSACTA);
        hash = 89 * hash + Objects.hashCode(this.CODUBI);
        hash = 89 * hash + Objects.hashCode(this.DIRACT);
        hash = 89 * hash + Objects.hashCode(this.TIPACTA);
        hash = 89 * hash + Objects.hashCode(this.ESTACTA);
        hash = 89 * hash + Objects.hashCode(this.FECREGACTA);
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
        final Acta other = (Acta) obj;
        if (!Objects.equals(this.IDACTA, other.IDACTA)) {
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
        if (!Objects.equals(this.NUMLIBACTA, other.NUMLIBACTA)) {
            return false;
        }
        if (!Objects.equals(this.NUMFOLACTA, other.NUMFOLACTA)) {
            return false;
        }
        if (!Objects.equals(this.OBSACTA, other.OBSACTA)) {
            return false;
        }
        if (!Objects.equals(this.CODUBI, other.CODUBI)) {
            return false;
        }
        if (!Objects.equals(this.DIRACT, other.DIRACT)) {
            return false;
        }
        if (!Objects.equals(this.TIPACTA, other.TIPACTA)) {
            return false;
        }
        if (!Objects.equals(this.ESTACTA, other.ESTACTA)) {
            return false;
        }
        if (!Objects.equals(this.FECREGACTA, other.FECREGACTA)) {
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

    public String getIDACTA() {
        return IDACTA;
    }

    public void setIDACTA(String IDACTA) {
        this.IDACTA = IDACTA;
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

    public String getNUMLIBACTA() {
        return NUMLIBACTA;
    }

    public void setNUMLIBACTA(String NUMLIBACTA) {
        this.NUMLIBACTA = NUMLIBACTA;
    }

    public String getNUMFOLACTA() {
        return NUMFOLACTA;
    }

    public void setNUMFOLACTA(String NUMFOLACTA) {
        this.NUMFOLACTA = NUMFOLACTA;
    }

    public String getOBSACTA() {
        return OBSACTA;
    }

    public void setOBSACTA(String OBSACTA) {
        this.OBSACTA = OBSACTA;
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

    public String getTIPACTA() {
        return TIPACTA;
    }

    public void setTIPACTA(String TIPACTA) {
        this.TIPACTA = TIPACTA;
    }

    public String getESTACTA() {
        return ESTACTA;
    }

    public void setESTACTA(String ESTACTA) {
        this.ESTACTA = ESTACTA;
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
        this.IDACTA = null;
        this.IDMUN = null;
        this.IDLOG = null;
        this.IDPER = null;
        this.NUMLIBACTA = null;
        this.NUMFOLACTA = null;
        this.OBSACTA = null;
        this.CODUBI = null;
        this.DIRACT = null;
        this.TIPACTA = null;
        this.ESTACTA = null;

        this.FECREGACTA = null;
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
