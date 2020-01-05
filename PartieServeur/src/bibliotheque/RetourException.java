package bibliotheque;

import livre.NonEmprunteNonReserverException;

public class RetourException extends Exception {
	
	private String msgUtilisateur;
	private static final long serialVersionUID = 9133644544815098254L;
	
	public RetourException(NonEmprunteNonReserverException e) {
		super("Erreur de retour : un abonne tente de retourner le document possédant l'identifiant "
				+ e.getnumDoc() +
				" ou bien annulé la réservation de ce document cependant que le document en question"
				+ " n'est ni emprunté, ni réservé");
		msgUtilisateur = "Erreur de retour :  Vous tentez de rendre le document renseigné ou bien "
				+ "d'annuler sa réservation mais ce document n'est ni emprunté, ni réservé";
	}

	public RetourException(RechercheDocumentException e) {
		super("Erreur de retour : Un utilisateur a renseigné "
				+ "un identifiant de document ne correspondant"
				+ " à aucun document connu de la bibliotheque");
		msgUtilisateur = "Erreur de retour : Le numéro de document que vous avez renseigné ne"
				+ " correspond à aucun document connu de la bibliothèque.";
	}
	
	public String getMsgUtilisateur() {
		return msgUtilisateur;
	}

}
