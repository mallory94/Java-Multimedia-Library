package documentEmpruntable;

import bibliotheque.Abonne;
import bibliotheque.Document;

public class ReservationException extends Exception {
	private static final long serialVersionUID = 1L;
	private Abonne aboVoulantEmprunter;
	private DocumentEmpruntable doc;
	
	public ReservationException() {
	}
	
	public ReservationException(Abonne aboVoulantEmprunter, DocumentEmpruntable doc) {
		super();
		System.out.println("création de la reservation exception avec " + aboVoulantEmprunter.getNom() + "possèdant le mail " + aboVoulantEmprunter.getMail() + 
				 " et le document " + doc.getTitre() + " qui a le num " + doc.getNumero());
		this.aboVoulantEmprunter = aboVoulantEmprunter;
		this.doc = doc;
	}
	
	//renvoit le nom de l'abonne souhaitant Emprunter
	public Abonne getAboVoulantEmprunter() {
		return aboVoulantEmprunter;
	}
	
	public DocumentEmpruntable getDocConcerne() {
		return doc;
	}
}
