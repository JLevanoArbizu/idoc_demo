package modelo;

import java.util.Objects;

public class Area {

    private int IDARE;
    private String NOMARE, ESTARE;
    private Municipalidad municipalidad = new Municipalidad();
    public AreaPadre areaPadre = new AreaPadre();

    public void clear() {
        this.IDARE = 0;
        this.NOMARE = null;
        this.ESTARE = null;
        this.municipalidad.clear();
        this.areaPadre.clear();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.NOMARE);
        hash = 97 * hash + Objects.hashCode(this.areaPadre);
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
        if (!Objects.equals(this.NOMARE, other.NOMARE)) {
            return false;
        }
        return Objects.equals(this.areaPadre, other.areaPadre);
    }

    public AreaPadre getAreaPadre() {
        return areaPadre;
    }

    public void setAreaPadre(AreaPadre areaPadre) {
        this.areaPadre = areaPadre;
    }

    public int getIDARE() {
        return IDARE;
    }

    public void setIDARE(int IDARE) {
        this.IDARE = IDARE;
    }

    public String getNOMARE() {
        return NOMARE;
    }

    public void setNOMARE(String NOMARE) {
        this.NOMARE = NOMARE;
    }

    public String getESTARE() {
        return ESTARE;
    }

    public void setESTARE(String ESTARE) {
        this.ESTARE = ESTARE;
    }

    public Municipalidad getMunicipalidad() {
        return municipalidad;
    }

    public void setMunicipalidad(Municipalidad municipalidad) {
        this.municipalidad = municipalidad;
    }

}
