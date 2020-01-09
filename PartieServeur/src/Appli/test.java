package Appli;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class test {

//	public static void main (String[] args) throws Exception {
//		 
//	    String smtpHost = "smtp.gmail.com";
//	    String from = "587";
//	    String to = "malloga94@gmail.com";
//	    String username = "BibliothequeServeur2020@gmail.com";
//	    String password = "Bretteur2020";
//	 
//	    Properties props = new Properties();
//	    props.put("mail.smtp.host", smtpHost);
//	    props.put("mail.smtp.auth", "true");
//	 
//	    Session session = Session.getDefaultInstance(props);
//	    session.setDebug(true);
//	 
//	    MimeMessage message = new MimeMessage(session);
//	    message.setFrom(new InternetAddress(from));
//	    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//	    message.setSubject("Hello");
//	    message.setText("Hello World");
//	 
//	    Transport tr = session.getTransport("smtp");
//	    tr.connect(smtpHost, username, password);
//	    message.saveChanges();
//	 
//	    // tr.send(message);
//	    /** Genere l'erreur. Avec l authentification, oblige d utiliser sendMessage meme pour une seule adresse... */
//	 
//	    tr.sendMessage(message,message.getAllRecipients());
//	    tr.close();
//	 
//	  }
	
	
	
	public static void main(String[] args) {
		
		final String username = "BibliothequeServeur2020@gmail.com";
        final String password = "Bretteur2020";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("BibliothequeServeur2020@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("malloga94@gmail.com, BibliothequeServeur2020@gmail.com")
            );
            message.setSubject("Testing Gmail TLS");
            message.setText("Dear Mail Crawler,"
                    + "\n\n Please do not spam my email!");

            Transport.send(message);

            System.out.println("Done");
            
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        

	}	
}

	

