package livre;

public class NonEmprunteNonReserverException extends Exception {

	
	private static final long serialVersionUID = -5268404809978775331L;
	private int numDoc;
	
	public NonEmprunteNonReserverException(int numDoc) {
		this.numDoc = numDoc;
	}
	
	public int getnumDoc() {
		return numDoc;
	}
}
