package modelo;

public class Dashboard {
    private Acta acta = new Acta();
    private int cantidad;
    //======documento
    private Documento documento = new Documento();
    private int cantidadD;
    //======Trazabilidad
    private Transferencia transferencia = new Transferencia();
    private int cantidadT;
    //======Tupa
    private Tupa tupa = new  Tupa();
    private int cantidadTU;

    public Transferencia getTransferencia() {
        return transferencia;
    }

    public void setTransferencia(Transferencia transferencia) {
        this.transferencia = transferencia;
    }

    public int getCantidadT() {
        return cantidadT;
    }

    public void setCantidadT(int cantidadT) {
        this.cantidadT = cantidadT;
    }

    public Tupa getTupa() {
        return tupa;
    }

    public void setTupa(Tupa tupa) {
        this.tupa = tupa;
    }

    public int getCantidadTU() {
        return cantidadTU;
    }

    public void setCantidadTU(int cantidadTU) {
        this.cantidadTU = cantidadTU;
    }

    
    
    
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

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public int getCantidadD() {
        return cantidadD;
    }

    public void setCantidadD(int cantidadD) {
        this.cantidadD = cantidadD;
    }
    
}
