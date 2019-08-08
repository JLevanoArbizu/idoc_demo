package modelo;

public class Dashboard {
    private Acta acta = new Acta();
    private int cantidad;

    public Acta getActa() {
        return acta;
    }

    public void setActa(Acta acta) {
        this.acta = acta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
