package empruntAvecSanction;

import bibliotheque.Abonne;
import documentEmpruntable.ReservationException;

public class InterditDempruntException extends Exception {

	private static final long serialVersionUID = -3444953671560807396L;
	private String msgUtilisateur;
	
	public InterditDempruntException(Abonne ab, int numDoc) {
		super("Erreur d'emprunt: L'abonne " + ab.getNom() + " immatricul� " + ab.getId() + " a tent� de d'emprunter le document " + numDoc + " alors qu'il est interdit d'emprunt.");
		msgUtilisateur = "Erreur d'emprunt: Vous avez tent� d'emprunter un document alors que vous �tes interdit d'emprunt.";
	}
	
	public InterditDempruntException(ReservationException e, Abonne ab, int numDoc) {
		super("Erreur de r�servation : L'abonne " + ab.getNom() + " immatricul� " + ab.getId() + " a tent� de r�server le document " + numDoc + " alors qu'il est interdit d'emprunt.");
		msgUtilisateur = "Erreur d'emprunt: Vous avez tent� de reserver un document alors que vous �tes interdit d'emprunt.";
	}
	
	public String getMsgUtilisateur() {
		return msgUtilisateur;
	}
	
	public InterditDempruntException(String motif, Abonne ab, 
			DocumentAvecEmpruntSanctionnable doc) {
		super("L'utilisateur " + ab.getNom() + " immatricul� " + ab.getId() + " a "
				+ "vient d'�tre interdit d'emprunt parce que il a rendu le document " + doc.getNumero() + " " + motif);
		msgUtilisateur = "Le retour de votre document a bien �t� enregistr� cependant "
			+ "le document a �t� rendu " + motif + ". Cela vous vaudra 1 mois (30 jours) d'i"
			+ "nterdiction d'emprunt.";
		doc.reparerDocument();
	}
}
