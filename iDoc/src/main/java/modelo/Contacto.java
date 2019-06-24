package modelo;

public class Contacto {

    String to, from, subject, description, user, pssw, smtp;
    int puerto;

    public void clear() {
        this.to = null;
        this.from = "martin.saman@vallegrande.edu.pe";
        this.subject = null;
        this.description = null;
        this.user = null;
        this.pssw = null;
        this.smtp = "smtp.gmail.com";
        this.puerto = 465;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPssw() {
        return pssw;
    }

    public void setPssw(String pssw) {
        this.pssw = pssw;
    }

    public String getSmtp() {
        return smtp;
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }
}
