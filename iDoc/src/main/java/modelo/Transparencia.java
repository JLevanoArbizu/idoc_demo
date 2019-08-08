package modelo;

import java.util.Objects;

public class Transparencia {
    Documento documento;
    Transferencia transferencia;
    Empresa empresa;
    Persona persona;
    Area emisor, receptor;
    String key, dni, coddoc;


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
                Objects.equals(coddoc, that.coddoc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documento, transferencia, empresa, persona, emisor, receptor, key, dni, coddoc);
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Transferencia getTransferencia() {
        return transferencia;
    }

    public void setTransferencia(Transferencia transferencia) {
        this.transferencia = transferencia;
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

    public String getCoddoc() {
        return coddoc;
    }

    public void setCoddoc(String coddoc) {
        this.coddoc = coddoc;
    }
}
