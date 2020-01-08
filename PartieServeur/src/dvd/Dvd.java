package dvd;

import bibliotheque.Abonne;
import bibliotheque.Document;
import bibliotheque.EmpruntException;
import documentEmpruntable.ReservationException;
import empruntAvecSanction.DocumentAvecEmpruntSanctionnable;

public class Dvd extends DocumentAvecEmpruntSanctionnable implements Document {
	private int limitationAge;
	private static int aucuneLimitation = 0;
	
	public Dvd(String nom) {
		super(nom);
		limitationAge = aucuneLimitation;
	}
	
	public Dvd(String nom, int limitationAge) {
		this(nom);
		this.limitationAge = limitationAge;
	}
	
	@Override
	public void reserver(Abonne ab) throws EmpruntException {
		if (!depasseLimitationAge(ab)) {
			throw new EmpruntException(new LimitationAgeException(), new ReservationException(),
					ab.getId(), limitationAge);
		}
		else
			super.reserver(ab);
	}
	
	@Override
	public void emprunter(Abonne ab) throws EmpruntException {
		if (!depasseLimitationAge(ab)){
			throw new EmpruntException(new LimitationAgeException(), ab.getId(), limitationAge);
		}
		else
			super.emprunter(ab);
	}
	
	public boolean depasseLimitationAge(Abonne ab) {
		return !(ab.getAge() < limitationAge && (limitationAge != aucuneLimitation));
	}
}
