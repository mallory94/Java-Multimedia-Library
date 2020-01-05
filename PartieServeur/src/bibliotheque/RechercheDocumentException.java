package bibliotheque;

public class RechercheDocumentException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private int numDoc;
	public RechercheDocumentException(int numero) {
		numDoc = numero;
	}
	public int getNumDoc() {
		return numDoc;
	}
	
}
