package modelo;

import java.util.Date;
import java.util.Objects;

public class Acta {

    private int IDACTA;
    private String NUMLIBACTA, NUMFOLACTA, OBSACTA, DIRACT, TIPACTA, ESTACTA = "A";
    private Date FECREGACTA = new Date(), FECACT = new Date();
    private Municipalidad municipalidad = new Municipalidad();
    private Ubigeo ubigeo = new Ubigeo();
    private Login login = new Login();
    private Persona titular = new Persona();

    public void clear() {
        this.IDACTA = 0;
        this.NUMLIBACTA = null;
        this.NUMFOLACTA = null;
        this.OBSACTA = null;
        this.DIRACT = null;
        this.TIPACTA = null;
        this.ESTACTA = "A";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.NUMLIBACTA);
        hash = 73 * hash + Objects.hashCode(this.NUMFOLACTA);
        hash = 73 * hash + Objects.hashCode(this.titular);
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
        if (!Objects.equals(this.NUMLIBACTA, other.NUMLIBACTA)) {
            return false;
        }
        if (!Objects.equals(this.NUMFOLACTA, other.NUMFOLACTA)) {
            return false;
        }
        return Objects.equals(this.titular, other.titular);
    }

    public int getIDACTA() {
        return IDACTA;
    }

    public void setIDACTA(int IDACTA) {
        this.IDACTA = IDACTA;
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

    public Date getFECREGACTA() {
        return FECREGACTA;
    }

    public void setFECREGACTA(Date FECREGACTA) {
        this.FECREGACTA = FECREGACTA;
    }

    public Date getFECACT() {
        return FECACT;
    }

    public void setFECACT(Date FECACT) {
        this.FECACT = FECACT;
    }

    public Municipalidad getMunicipalidad() {
        return municipalidad;
    }

    public void setMunicipalidad(Municipalidad municipalidad) {
        this.municipalidad = municipalidad;
    }

    public Ubigeo getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(Ubigeo ubigeo) {
        this.ubigeo = ubigeo;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Persona getTitular() {
        return titular;
    }

    public void setTitular(Persona titular) {
        this.titular = titular;
    }

}
