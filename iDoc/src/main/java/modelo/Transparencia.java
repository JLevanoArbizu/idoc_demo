package modelo;

import java.util.Objects;

public class Transparencia {
    DocumentoM documento;
    TransferenciaM transferencia;
    EmpresaM empresa;
    Persona persona;
    Area emisor, receptor;
    String key, dni, idtra;

    @Override
    public String toString() {
        return "Transparencia{" +
                "documento=" + documento +
                ", transferencia=" + transferencia +
                ", empresa=" + empresa +
                ", persona=" + persona +
                ", emisor=" + emisor +
                ", receptor=" + receptor +
                ", key='" + key + '\'' +
                ", dni='" + dni + '\'' +
                ", idtra='" + idtra + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transparencia that = (Transparencia) o;
        return Objects.equals(documento, that.documento) &&
                Objects.equals(transferencia, that.transferencia) &&
                Objects.equals(empresa, that.empresa) &&
                Objects.equals(persona, that.persona) &&
                Objects.equals(emisor, that.emisor) &&
                Objects.equals(receptor, that.receptor) &&
                Objects.equals(key, that.key) &&
                Objects.equals(dni, that.dni) &&
                Objects.equals(idtra, that.idtra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documento, transferencia, empresa, persona, emisor, receptor, key, dni, idtra);
    }

    public DocumentoM getDocumento() {
        return documento;
    }

    public void setDocumento(DocumentoM documento) {
        this.documento = documento;
    }

    public TransferenciaM getTransferencia() {
        return transferencia;
    }

    public void setTransferencia(TransferenciaM transferencia) {
        this.transferencia = transferencia;
    }

    public EmpresaM getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaM empresa) {
        this.empresa = empresa;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Area getEmisor() {
        return emisor;
    }

    public void setEmisor(Area emisor) {
        this.emisor = emisor;
    }

    public Area getReceptor() {
        return receptor;
    }

    public void setReceptor(Area receptor) {
        this.receptor = receptor;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getIdtra() {
        return idtra;
    }

    public void setIdtra(String idtra) {
        this.idtra = idtra;
    }
}
