package Serveur;

public class Livre implements Document {
	
	private static int cpt = 0;
	private int numero;
	private String nom;
	private Abonne abonneReserver;
	private boolean isEmprumter;
	
	public Livre(String nom) {
		this.numero = cpt;
		cpt++;
		this.nom = nom;
		this.abonneReserver = null;
		this.isEmprumter = false;
	}
	
	@Override
	public int numero() {
		return this.numero;
	}

	@Override
	public void reserver(Abonne ab) throws EmpruntException {
		if(this.abonneReserver != null && this.isEmprumter == false ) {
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
			this.abonneReserver = null;
		}
		else {
			throw new EmpruntException();
		}
	}

	@Override
	public void retour() throws RetourException {
		if (this.isEmprumter == false) {
			throw new RetourException();
		}
		this.isEmprumter = false;
	}

}
