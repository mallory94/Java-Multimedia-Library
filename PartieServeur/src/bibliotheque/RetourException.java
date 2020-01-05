package bibliotheque;

import livre.NonEmprunteNonReserverException;

public class RetourException extends Exception {
	
	private String msgUtilisateur;
	private static final long serialVersionUID = 9133644544815098254L;
	
	public RetourException(NonEmprunteNonReserverException e) {
		super("Erreur de retour : un abonne tente de retourner le document poss�dant l'identifiant "
				+ e.getnumDoc() +
				" ou bien annul� la r�servation de ce document cependant que le document en question"
				+ " n'est ni emprunt�, ni r�serv�");
		msgUtilisateur = "Erreur de retour :  Vous tentez de rendre le document renseign� ou bien "
				+ "d'annuler sa r�servation mais ce document n'est ni emprunt�, ni r�serv�";
	}

	public RetourException(RechercheDocumentException e) {
		super("Erreur de retour : Un utilisateur a renseign� "
				+ "un identifiant de document ne correspondant"
				+ " � aucun document connu de la bibliotheque");
		msgUtilisateur = "Erreur de retour : Le num�ro de document que vous avez renseign� ne"
				+ " correspond � aucun document connu de la biblioth�que.";
	}
	
	public String getMsgUtilisateur() {
		return msgUtilisateur;
	}

}
