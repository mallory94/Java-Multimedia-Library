package bibliotheque;

import documentEmpruntable.DejaEmprunteException;
import documentEmpruntable.DejaReserverException;
import documentEmpruntable.ReservationException;
import dvd.LimitationAgeException;

public class EmpruntException extends Exception {
	private String msgUtilisateur;
	private boolean dejaReserverOuEmprunter = false;
	
	private static final long serialVersionUID = -5770254047220744546L;

	public EmpruntException(DejaReserverException e) {
		super("Un emprunt s'est vu annul� car le document que l'utilisateur "
				+ e.getNomAboVoulantReserver() + " veut emprunter est d�j� r�serv�");
		renseigneReservePourEmprunt();
		dejaReserverOuEmprunter = true;
	}
	
	public EmpruntException(DejaReserverException e, ReservationException e2) {
		super("Une r�servation s'est vue annul�e car le document que l'utilisateur "
				+ e.getNomAboVoulantReserver() + " veut r�server est d�j� r�serv�");
		renseigneReservePourReservation();
		dejaReserverOuEmprunter = true;
	}
	
	public EmpruntException(DejaEmprunteException e) {
		super("Un emprunt s'est vu annul� car le document que l'utilisateur "
				+ " veut emprunter est d�j� emprunt�");
		renseigneEmpruntePourEmprunt();
		dejaReserverOuEmprunter = true;
	}

	public EmpruntException(DejaEmprunteException e1, ReservationException e2) {
		super("Une r�servation s'est vue annul�e car le document que l'utilisateur "
				+ " veut r�server est d�j� emprunt�");
		renseigneEmpruntePourReservation();
		dejaReserverOuEmprunter = true;
	}
	
	public EmpruntException(RechercheDocumentException e) {
		super("Un emprunt s'est vu annul� car le num�ro de document que l'utilisateur "
				+ "a renseign� ( � savoir : " + e.getNumDoc() 
				+ ") ne correspond � aucun document de la biblioth�que");
		renseigneDocumentInconnu();
	}
	
	public EmpruntException(RechercheAbonneException e) {
		super("Erreur d'emprunt : le numero d'abonne renseign� par l'utilisateur "
				+ " ( � savoir : " + e.getnumAbo() +  ") ne correspond � "
				+ "aucun abonn� connu de la bibliotheque");
		renseigneAbonneInconnu();
	}

	public EmpruntException(RechercheDocumentException e, ReservationException reservationException) {
		super("Une reservation s'est vue annul�e car le num�ro de document que l'utilisateur "
				+ "a renseign� ( � savoir : " + e.getNumDoc() 
				+ ") ne correspond � aucun document de la biblioth�que");
		renseigneDocumentInconnu();
	}

	public EmpruntException(RechercheAbonneException e2, ReservationException reservationException) {
		super("Erreur de r�servation : le num�ro d'abonn� renseign� par l'utilisateur "
				+ " ( � savoir : " + e2.getnumAbo() +  ") ne correspond � "
				+ "aucun abonne connu de la bibliotheque");
		renseigneAbonneInconnu();
	}

	public EmpruntException(LimitationAgeException limitationAgeException, int idAbo, int limitationAge) {
		super("Erreur d'emprunt : L'abonn� poss�dant l'identifiant " + idAbo + " a tent� "
				+ " d'emprunt� un document r�serv� aux plus de "  + limitationAge 
				+ " ans sans avoir l'�ge requis.");
		renseigneLimitationAge(limitationAge);
	}
	
	

	public EmpruntException(LimitationAgeException limitationAgeException, 
			ReservationException reservationException, int idAbo, int limitationAge) {
		super("Erreur de r�servation. L'abonn� poss�dant l'identifiant " + idAbo + " a tent� "
				+ " de r�server un document r�serv� aux plus de " + limitationAge + 
				" ans sans avoir l'�ge requis.");
		renseigneLimitationAge(limitationAge);
	}

	private void renseigneLimitationAge(int limitationAge) {
		msgUtilisateur = "Erreur d'emprunt/r�servation : Vous n'avez pas l'�ge requis pour acc�der"
				+ " � ce document. Il est r�serv� aux plus de " + limitationAge + " ans.";
	}

	public String getMsgUtilisateur() {
		return msgUtilisateur;
	}
	
	public void renseigneReservePourReservation() {
		msgUtilisateur = "Erreur d'emprunt : le document que vous souhaitez r�server "
				+ "est r�serv�.";
	}
	
	public void renseigneEmpruntePourReservation() {
		msgUtilisateur = "Erreur d'emprunt : le document que vous souhaitez r�server "
				+ "est en cours d'emprunt.";
	}
	
	public void renseigneReservePourEmprunt() {
		msgUtilisateur = "Erreur d'emprunt : le document que vous souhaitez emprunter "
				+ "est r�serv�.";
	}
	
	public void renseigneEmpruntePourEmprunt() {
		msgUtilisateur = "Erreur d'emprunt : le document que vous souhaitez emprunter "
				+ "est en cours d'emprunt.";
	}
	
	
	public void renseigneDocumentInconnu() {
		msgUtilisateur = "Votre demande ne peut �tre satisfaite car le numero de "
				+ "document renseign� ne correspond � aucun document connu de la "
				+ "biblioth�que.";
	}
	
	public void renseigneAbonneInconnu() {
		msgUtilisateur = "Votre demande ne peut �tre satisfaite car le numero d'"
				+ "abonn� renseign� ne correspond � aucun abonn� connu de la "
				+ "biblioth�que.";
	}
	
	public boolean estDejaReserverOuEmprunterException(){
		return dejaReserverOuEmprunter;
	}
	
}

