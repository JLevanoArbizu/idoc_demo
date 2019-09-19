package modelo;

// No requiere el usuario, por el momento
public class Solicitud {

    private Login login = new Login();
    private Acta acta = new Acta();
    private String ESTSOL = "A", CODSOL;

    public void clear() {
        this.login.clear();
        this.acta.clear();
        this.ESTSOL = null;
        this.CODSOL = null;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Acta getActa() {
        return acta;
    }

    public void setActa(Acta acta) {
        this.acta = acta;
    }

    public String getESTSOL() {
        return ESTSOL;
    }

    public void setESTSOL(String ESTSOL) {
        this.ESTSOL = ESTSOL;
    }

    public String getCODSOL() {
        return CODSOL;
    }

    public void setCODSOL(String CODSOL) {
        this.CODSOL = CODSOL;
    }

}
