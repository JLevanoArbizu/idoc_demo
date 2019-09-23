package modelo;

import java.util.Date;

public class Documento {

    private int IDDOC;
    private int CODDOC;
    private String NUMLIBDOC;
    private String NUMFOLDOC;
    private DocumentoTipo documentotipo = new DocumentoTipo();
    private Date FECDOC = new Date();
    private String ASUDOC;
    private String OBSDOC;
    private String ESTDOC;
    private Tupa tupa = new Tupa();
    private Login login = new Login();
    private Empresa empresa = new Empresa();
    private Persona persona = new Persona();
    private String KEYDOC;
    private String DENDOC;

    public void clear() {

    }

    public int getIDDOC() {
        return IDDOC;
    }

    public void setIDDOC(int IDDOC) {
        this.IDDOC = IDDOC;
    }

    public int getCODDOC() {
        return CODDOC;
    }

    public void setCODDOC(int CODDOC) {
        this.CODDOC = CODDOC;
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

    public DocumentoTipo getDocumentotipo() {
        return documentotipo;
    }

    public void setDocumentotipo(DocumentoTipo documentotipo) {
        this.documentotipo = documentotipo;
    }

    
    public Date getFECDOC() {
        return FECDOC;
    }

    public void setFECDOC(Date FECDOC) {
        this.FECDOC = FECDOC;
    }

    public String getASUDOC() {
        return ASUDOC;
    }

    public void setASUDOC(String ASUDOC) {
        this.ASUDOC = ASUDOC;
    }

    public String getOBSDOC() {
        return OBSDOC;
    }

    public void setOBSDOC(String OBSDOC) {
        this.OBSDOC = OBSDOC;
    }

    public String getESTDOC() {
        return ESTDOC;
    }

    public void setESTDOC(String ESTDOC) {
        this.ESTDOC = ESTDOC;
    }

    public Tupa getTupa() {
        return tupa;
    }

    public void setTupa(Tupa tupa) {
        this.tupa = tupa;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getKEYDOC() {
        return KEYDOC;
    }

    public void setKEYDOC(String KEYDOC) {
        this.KEYDOC = KEYDOC;
    }

    public String getDENDOC() {
        return DENDOC;
    }

    public void setDENDOC(String DENDOC) {
        this.DENDOC = DENDOC;
    }

}
