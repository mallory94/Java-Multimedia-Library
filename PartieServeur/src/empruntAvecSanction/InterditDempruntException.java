package empruntAvecSanction;

import bibliotheque.Abonne;
import documentEmpruntable.ReservationException;

public class InterditDempruntException extends Exception {

	private static final long serialVersionUID = -3444953671560807396L;
	private String msgUtilisateur;
	
	public InterditDempruntException(Abonne ab, int numDoc) {
		super("Erreur d'emprunt: L'abonne " + ab.getNom() + " immatriculé " + ab.getId() + " a tenté de d'emprunter le document " + numDoc + " alors qu'il est interdit d'emprunt.");
		msgUtilisateur = "Erreur d'emprunt: Vous avez tenté d'emprunter un document alors que vous êtes interdit d'emprunt.";
	}
	
	public InterditDempruntException(ReservationException e, Abonne ab, int numDoc) {
		super("Erreur de réservation : L'abonne " + ab.getNom() + " immatriculé " + ab.getId() + " a tenté de réserver le document " + numDoc + " alors qu'il est interdit d'emprunt.");
		msgUtilisateur = "Erreur d'emprunt: Vous avez tenté de reserver un document alors que vous êtes interdit d'emprunt.";
	}
	
	public String getMsgUtilisateur() {
		return msgUtilisateur;
	}
	
	public InterditDempruntException(String motif, Abonne ab, 
			DocumentAvecEmpruntSanctionnable doc) {
		super("L'utilisateur " + ab.getNom() + " immatriculé " + ab.getId() + " a "
				+ "vient d'être interdit d'emprunt parce que il a rendu le document " + doc.getNumero() + " " + motif);
		msgUtilisateur = "Le retour de votre document a bien été enregistré cependant "
			+ "le document a été rendu " + motif + ". Cela vous vaudra 1 mois (30 jours) d'i"
			+ "nterdiction d'emprunt.";
		doc.reparerDocument();
	}
}
