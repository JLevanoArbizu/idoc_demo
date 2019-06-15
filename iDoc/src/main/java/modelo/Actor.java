package modelo;

import java.util.Objects;

public class Actor {
    String IDACT, IDACTA, IDPER, TIPACT;
    
    public void clear(){
        this.IDACT = null;
        this.IDACTA = null;
        this.IDPER = null;
        this.TIPACT = null;
    }

    @Override
    public String toString() {
        return "Actor{" + "IDACT=" + IDACT + ", IDDOC=" + IDACTA + ", IDPER=" + IDPER + ", TIPACT=" + TIPACT + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.IDACT);
        hash = 41 * hash + Objects.hashCode(this.IDACTA);
        hash = 41 * hash + Objects.hashCode(this.IDPER);
        hash = 41 * hash + Objects.hashCode(this.TIPACT);
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
        final Actor other = (Actor) obj;
        if (!Objects.equals(this.IDACT, other.IDACT)) {
            return false;
        }
        if (!Objects.equals(this.IDACTA, other.IDACTA)) {
            return false;
        }
        if (!Objects.equals(this.IDPER, other.IDPER)) {
            return false;
        }
        if (!Objects.equals(this.TIPACT, other.TIPACT)) {
            return false;
        }
        return true;
    }

    public String getIDACT() {
        return IDACT;
    }

    public void setIDACT(String IDACT) {
        this.IDACT = IDACT;
    }

    public String getIDACTA() {
        return IDACTA;
    }

    public void setIDACTA(String IDACTA) {
        this.IDACTA = IDACTA;
    }

    public String getIDPER() {
        return IDPER;
    }

    public void setIDPER(String IDPER) {
        this.IDPER = IDPER;
    }

    public String getTIPACT() {
        return TIPACT;
    }

    public void setTIPACT(String TIPACT) {
        this.TIPACT = TIPACT;
    }
}
