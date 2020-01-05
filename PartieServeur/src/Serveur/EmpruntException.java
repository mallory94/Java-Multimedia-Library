package Serveur;

public class EmpruntException extends Exception {
	private String msgUtilisateur;
	
	private static final long serialVersionUID = -5770254047220744546L;

	public EmpruntException(DejaReserverException e) {
		super("Un emprunt s'est vu annulé car le document que l'utilisateur "
				+ e.getNomAboVoulantReserver() + " veut emprunter est déjà réservé");
		renseigneReservePourEmprunt();
	}
	
	public EmpruntException(DejaReserverException e, ReservationException e2) {
		super("Une réservation s'est vue annulée car le document que l'utilisateur "
				+ e.getNomAboVoulantReserver() + " veut réserver est déjà réservé");
		renseigneReservePourReservation();
	}
	
	public EmpruntException(DejaEmprunteException e) {
		super("Un emprunt s'est vu annulé car le document que l'utilisateur "
				+ " veut emprunter est déjà emprunté");
		renseigneEmpruntePourEmprunt();
	}

	public EmpruntException(DejaEmprunteException e1, ReservationException e2) {
		super("Une réservation s'est vue annulée car le document que l'utilisateur "
				+ " veut réserver est déjà emprunté");
		renseigneEmpruntePourReservation();
	}
	
	public EmpruntException(RechercheDocumentException e) {
		super("Un emprunt s'est vu annulé car le numéro de document que l'utilisateur "
				+ "a renseigné ( à savoir : " + e.getNumDoc() 
				+ ") ne correspond à aucun document de la bibliothèque");
		renseigneDocumentInconnu();
	}
	
	public EmpruntException(RechercheAbonneException e) {
		super("Erreur d'emprunt : le numero d'abonne renseigné par l'utilisateur "
				+ " ( à savoir : " + e.getnumAbo() +  ") ne correspond à "
				+ "aucun abonné connu de la bibliotheque");
		renseigneAbonneInconnu();
	}

	public EmpruntException(RechercheDocumentException e, ReservationException reservationException) {
		super("Une reservation s'est vue annulée car le numéro de document que l'utilisateur "
				+ "a renseigné ( à savoir : " + e.getNumDoc() 
				+ ") ne correspond à aucun document de la bibliothèque");
		renseigneDocumentInconnu();
	}

	public EmpruntException(RechercheAbonneException e2, ReservationException reservationException) {
		super("Erreur de réservation : le numéro d'abonné renseigné par l'utilisateur "
				+ " ( à savoir : " + e2.getnumAbo() +  ") ne correspond à "
				+ "aucun abonne connu de la bibliotheque");
		renseigneAbonneInconnu();
	}

	public String getMsgUtilisateur() {
		return msgUtilisateur;
	}
	
	public void renseigneReservePourReservation() {
		msgUtilisateur = "Erreur d'emprunt : le document que vous souhaitez réserver "
				+ "est réservé.";
	}
	
	public void renseigneEmpruntePourReservation() {
		msgUtilisateur = "Erreur d'emprunt : le document que vous souhaitez réserver "
				+ "est en cours d'emprunt.";
	}
	
	public void renseigneReservePourEmprunt() {
		msgUtilisateur = "Erreur d'emprunt : le document que vous souhaitez emprunter "
				+ "est réservé.";
	}
	
	public void renseigneEmpruntePourEmprunt() {
		msgUtilisateur = "Erreur d'emprunt : le document que vous souhaitez emprunter "
				+ "est en cours d'emprunt.";
	}
	
	
	public void renseigneDocumentInconnu() {
		msgUtilisateur = "Votre demande ne peut être satisfaite car le numero de "
				+ "document renseigné ne correspond à aucun document connu de la "
				+ "bibliothèque.";
	}
	
	public void renseigneAbonneInconnu() {
		msgUtilisateur = "Votre demande ne peut être satisfaite car le numero d'"
				+ "abonné renseigné ne correspond à aucun abonné connu de la "
				+ "bibliothèque.";
	}
}

