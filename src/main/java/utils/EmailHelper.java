package utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailHelper {
    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;

    private void setPropertis() {
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        mailServerProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

    }



    private void createMessage(String fromEmail, String toEmail, String subject, String emailBody) throws MessagingException {
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        generateMailMessage.setSubject(subject);
        generateMailMessage.setContent(emailBody, "text/html");

    }

    public void sendEmail(String fromEmail, String password, String subject, String body, String toEmail) throws MessagingException {
//
        setPropertis();
        createMessage(fromEmail, toEmail, subject, body);

        Transport transport = getMailSession.getTransport("smtp");
        transport.connect("smtp.gmail.com", fromEmail, password);
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();


    }


}