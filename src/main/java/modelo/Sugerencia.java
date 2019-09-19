package modelo;

public class Sugerencia {

    Login login = new Login();
    int IDSUG;
    String SUG;
    String ESTSUG;

    public void clear() {
        this.SUG = null;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public int getIDSUG() {
        return IDSUG;
    }

    public void setIDSUG(int IDSUG) {
        this.IDSUG = IDSUG;
    }

    public String getSUG() {
        return SUG;
    }

    public void setSUG(String SUG) {
        this.SUG = SUG;
    }

    public String getESTSUG() {
        return ESTSUG;
    }

    public void setESTSUG(String ESTSUG) {
        this.ESTSUG = ESTSUG;
    }

}
