package livre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bibliotheque.Abonne;
import bibliotheque.EmpruntException;
import bibliotheque.RetourException;
import documentEmpruntable.DocumentEmpruntable;
import minuteur.MinuteurInterdictionDemprunt;

public class Livre extends DocumentEmpruntable{
	private static HashMap<Abonne, MinuteurInterdictionDemprunt> abonnesInterditsDemprunt;
	private EtatDegradation etatDegradation;
	private Abonne emprunteur;
	
	
	public Livre(String nom) {
		super(nom);
	}
	
	
	@Override
	public void reserver(Abonne ab) throws EmpruntException {
		
		if (!ParamAboEstInterdit(ab)) {
			super.reserver(ab);
		}
		else {
			throw 
		}
		
	}
	
	@Override
	public void emprunter(Abonne ab) throws EmpruntException  {
		super.emprunter(ab);
		emprunteur = ab;
	}
	
	@Override
	public void retour() throws RetourException {
		if (super.estEmprunte()) {
			boolean aReponduCorrectement = false;
			String reponse = null;
			Scanner sc = new Scanner(System.in);
			while (!aReponduCorrectement) {
				System.out.println("Indiquez si le livre a subi une dégradation.\nEntrez O pour oui et "
						+ "N pour Non");
				
				reponse = sc.nextLine().trim().toLowerCase();
				if (reponse.equals("o") || reponse.equals("n") ) {
					aReponduCorrectement = true;
				}
			}
			sc.close();
			if (reponse.equals("o")) {
				getEmprunteur();
			}
		}
		super.retour();
		emprunteur = null;
	}
	
	public boolean aSubiDegradation() {
		return(etatDegradation == EtatDegradation.DEGRADE);
	}
	
	public Abonne getEmprunteur() {
		return(emprunteur);
	}
	
	
	public void sanctionnerEmprunteur(String motif) {
		int sanctionStandarde = 
		synchronized (abonnesInterditsDemprunt) {
			abonnesInterditsDemprunt.put(getEmprunteur(), new MinuteurInterdictionDemprunt(getEmprunteur(), motif, ));
			abonnesInterditsDemprunt.notifyAll();
		}
	}
	
	public static void retirerInterdictionAbonne(Abonne ab) {
		synchronized (abonnesInterditsDemprunt) {
			abonnesInterditsDemprunt.remove(ab);
			abonnesInterditsDemprunt.notifyAll();
		}
	}
	
	//renvoit vrai si l'abonne en paramètre est interdit d'emprunt
	public static boolean ParamAboEstInterdit(Abonne ab) {
		boolean estInterdit = false;
		synchronized (abonnesInterditsDemprunt) {
			if (abonnesInterditsDemprunt.containsKey(ab)) {
				estInterdit = true;
			}
			abonnesInterditsDemprunt.notifyAll();
		}
		return(estInterdit);
	}
}
