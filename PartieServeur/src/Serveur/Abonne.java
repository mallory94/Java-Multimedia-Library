package Serveur;

public class Abonne {
	
	private static int cpt = 0;
	
	private int id;
	private String nom;
	
	public Abonne() {
		id = cpt++;
	}
	
	public Abonne(String nom) {
		this();
		this.nom = nom;
	}
	
	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

}
