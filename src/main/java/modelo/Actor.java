package modelo;

import java.util.Objects;

public class Actor {

    private int IDACT;
    private String TIPACT;
    private Acta acta = new Acta();
    private Persona actor = new Persona();

    public void clear() {
        this.IDACT = 0;
        this.TIPACT = null;
        this.acta.clear();
        this.actor.clear();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.TIPACT);
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
        return Objects.equals(this.TIPACT, other.TIPACT);
    }


    public int getIDACT() {
        return IDACT;
    }

    public void setIDACT(int IDACT) {
        this.IDACT = IDACT;
    }

    public String getTIPACT() {
        return TIPACT;
    }

    public void setTIPACT(String TIPACT) {
        this.TIPACT = TIPACT;
    }

    public Acta getActa() {
        return acta;
    }

    public void setActa(Acta acta) {
        this.acta = acta;
    }

    public Persona getActor() {
        return actor;
    }

    public void setActor(Persona actor) {
        this.actor = actor;
    }
}
