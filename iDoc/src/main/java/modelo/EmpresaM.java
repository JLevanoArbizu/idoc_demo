package modelo;

public class EmpresaM {
   
   private String IDEMP;
   private String RAZSOCEMP;
   private String RUCEMP;
   private String DIREMP;
   private String ESTEMP;

   public void clear(){
       this.IDEMP = null;
       this.RAZSOCEMP = null;
       this.RUCEMP = null;
       this.DIREMP = null;
       this.ESTEMP = null;
   }
//   private String CODUBI;
   
//   private String UBIGEO;

    public String getIDEMP() {
        return IDEMP;
    }

    public void setIDEMP(String IDEMP) {
        this.IDEMP = IDEMP;
    }


    public String getRAZSOCEMP() {
        return RAZSOCEMP;
    }

    public void setRAZSOCEMP(String RAZSOCEMP) {
        this.RAZSOCEMP = RAZSOCEMP;
    }

    public String getRUCEMP() {
        return RUCEMP;
    }

    public void setRUCEMP(String RUCEMP) {
        this.RUCEMP = RUCEMP;
    }

    public String getDIREMP() {
        return DIREMP;
    }

    public void setDIREMP(String DIREMP) {
        this.DIREMP = DIREMP;
    }

//    public String getCODUBI() {
//        return CODUBI;
//    }
//
//    public void setCODUBI(String CODUBI) {
//        this.CODUBI = CODUBI;
//    }

    public String getESTEMP() {
        return ESTEMP;
    }

    public void setESTEMP(String ESTEMP) {
        this.ESTEMP = ESTEMP;
    }

//    public String getUBIGEO() {
//        return UBIGEO;
//    }
//
//    public void setUBIGEO(String UBIGEO) {
//        this.UBIGEO = UBIGEO;
//    }
}
