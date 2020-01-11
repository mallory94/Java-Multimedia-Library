package alerteMailDisponibilite;

import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import bibliotheque.Abonne;
import bibliotheque.Document;
import documentEmpruntable.DocumentEmpruntable;
import documentEmpruntable.ReservationException;

public class Mailer implements Runnable{
	private Abonne abo;
	private DocumentEmpruntable document;
	
	
	public Mailer(DocumentEmpruntable documentReserve, Abonne abo) {
		this.document = documentReserve;
		this.abo = abo;
		new Thread(this).start();
	}
		
	public Mailer(ReservationException re) {
		this(re.getDocConcerne(),re.getAboVoulantEmprunter());
	}

	@Override
	public void run() {
		synchronized (document) {
			try {
				document.wait();
				envoyerMail();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Un mail alerte vient d'être envoyé à l'abonné " + abo.getNom() + 
				"(" + abo.getMail() + ")");
	}
	
	
	public void envoyerMail() {
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
	                InternetAddress.parse(abo.getMail() + ", BibliothequeServeur2020@gmail.com")
	        );
	        message.setSubject("Un document qui vous intéresse est disponible");
	        message.setText("Cher " + abo.getNom() + ", "
	                + "\n\n Le document \"" + document.getTitre() + "\" sur lequel vous avez placé une"
	                		+ " alerte est maintenant disponible à la bibliothèque.");
	
	        Transport.send(message);
	        
	    } catch (MessagingException e) {
	        e.printStackTrace();
	    }
	
	
	
	}











	
}









