package modelo;

public class Empresa {
   
   private int IDEMP;
   private String RAZSOCEMP;
   private String RUCEMP;
   private String DIREMP;
   private String ESTEMP;

   public void clear(){
       this.IDEMP = 0;
       this.RAZSOCEMP = null;
       this.RUCEMP = null;
       this.DIREMP = null;
       this.ESTEMP = null;
   }

    public int getIDEMP() {
        return IDEMP;
    }

    public void setIDEMP(int IDEMP) {
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

    public String getESTEMP() {
        return ESTEMP;
    }

    public void setESTEMP(String ESTEMP) {
        this.ESTEMP = ESTEMP;
    }
}
