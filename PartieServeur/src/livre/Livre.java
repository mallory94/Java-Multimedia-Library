package livre;

import bibliotheque.Abonne;
import bibliotheque.EmpruntException;
import bibliotheque.RetourException;
import documentEmpruntable.DocumentEmpruntable;

public class Livre extends DocumentEmpruntable{
	private EtatDegradation etatDegradation;
	public Livre(String nom) {
		super(nom);
	}
	
	
	@Override
	public void reserver(Abonne ab) throws EmpruntException {
		try {
			super.reserver(ab);
		}
		catch (EmpruntException e) {
			
		}
	}
	
	@Override
	public void retour() throws RetourException {
		
		super.retour();
	}
	
	
}
