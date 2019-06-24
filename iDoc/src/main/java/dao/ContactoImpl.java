package dao;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import modelo.Contacto;

public class ContactoImpl {

    public String enviarMensaje(final Contacto contacto) {

        Properties propiedades = new Properties();
        Session sesion = null;
        MimeMessage mensaje = null;
        Address fromAddress = null;
        Address toAddress = null;

        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.host", "true");
        propiedades.put("mail.smtp.port", "true");
        sesion = Session.getInstance(propiedades,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(contacto.getUser(), contacto.getPssw());
                    }
                });

        mensaje = new MimeMessage(sesion);

        try {
            mensaje.setContent(contacto.getDescription(), "text/plain");
            mensaje.setSubject(contacto.getSubject());

            fromAddress = new InternetAddress(contacto.getFrom());
            mensaje.setFrom(fromAddress);

            toAddress = new InternetAddress(contacto.getTo());

            mensaje.setRecipient(
                    Message.RecipientType.TO, 
                    toAddress
            );
            mensaje.saveChanges();

            Transport transport = sesion.getTransport("smtp");
            transport.connect(
                    contacto.getSmtp(),
                    contacto.getPuerto(),
                    contacto.getUser(),
                    contacto.getPssw()
            );
            if (!transport.isConnected()) {
                return "Email Faliido";
            }
            transport.sendMessage(mensaje, mensaje.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            return "[Error] " + e.getMessage();
        }

        return "Mensaje enviado satisfactoriamente";
    }
}
