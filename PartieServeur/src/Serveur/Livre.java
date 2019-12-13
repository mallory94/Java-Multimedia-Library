package Serveur;

public class Livre implements Document {
	
	private static int cpt = 0;
	private int num�ro;
	private String nom;
	private Abonne abonneReserver;
	private boolean isEmprumter;
	
	public Livre(String nom) {
		this.num�ro = cpt;
		cpt++;
		this.nom = nom;
		this.abonneReserver = null;
		this.isEmprumter = false;
	}
	
	@Override
	public int numero() {
		return this.num�ro;
	}

	@Override
	public void reserver(Abonne ab) throws EmpruntException {
		if(this.abonneReserver != null) {
			this.abonneReserver = ab;
		}
		else {
			throw new EmpruntException();
		}
		
	}

	@Override
	public void emprunter(Abonne ab) throws EmpruntException {
		if(ab == this.abonneReserver) {
			this.isEmprumter = true;
		}
		else {
			throw new EmpruntException();
		}
	}

}
