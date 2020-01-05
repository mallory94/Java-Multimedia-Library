package bibliotheque;

public class RechercheAbonneException extends Exception{
	
	
	private static final long serialVersionUID = 649335891679404595L;

	
	private int numAbo;
	public RechercheAbonneException(int numero) {
		numAbo = numero;
	}
	public int getnumAbo() {
		return numAbo;
	}
}
