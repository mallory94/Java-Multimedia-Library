package Appli;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

public class test {

	public static void main(String[] args) {
		
		Properties props = new Properties();
	    props.put("mail.smtp.host", "my-mail-server");
	    Session session = Session.getInstance(props, null);

	    try {
	        MimeMessage msg = new MimeMessage(session);
	        msg.setFrom("me@example.com");
	        msg.setRecipients(Message.RecipientType.TO,
	                          "you@example.com");
	        msg.setSubject("JavaMail hello world example");
	        msg.setSentDate(new Date());
	        msg.setText("Hello, world!\n");
	        Transport.send(msg, "me@example.com", "my-password");
	    } catch (MessagingException mex) {
	        System.out.println("send failed, exception: " + mex);
	    }
	}
	
	
	String smtpHost = "smtp.xyz.com";
    String from = "originataire@origine.com";
    String to = "destinataire@destination.com";
    String username = "moi";
    String password = "mon_mot_secret";
 
    Properties props = new Properties();
    props.put("mail.smtp.host", smtpHost);
    props.put("mail.smtp.auth", "true");
 
    Session session = Session.getDefaultInstance(props);
    session.setDebug(true);
 
    MimeMessage message = new MimeMessage(session);   
    message.setFrom(new InternetAddress(from));
    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    message.setSubject("Hello");
    message.setText("Hello World");
 
    Transport tr = session.getTransport("smtp");
    tr.connect(smtpHost, username, password);
    message.saveChanges();
 
    // tr.send(message);
    /** Genere l'erreur. Avec l authentification, oblige d utiliser sendMessage meme pour une seule adresse... */
 
    tr.sendMessage(message,message.getAllRecipients());
    tr.close();
}
