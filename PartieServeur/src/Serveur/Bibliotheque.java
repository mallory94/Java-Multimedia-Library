package Serveur;

import java.util.ArrayList;

public class Bibliotheque {
	private static ArrayList<Document> documents = new ArrayList<>();
	private static ArrayList<Abonne> abonnes = new ArrayList<>();
	
	
	
	public static Abonne getAbonne(int idAbonne) throws RechercheAbonneException {
		for(Abonne abonne : abonnes) {
			if (abonne.getId() == idAbonne) {
				return abonne;
			}
		}
		throw new RechercheAbonneException(idAbonne);
	}
	
	public static void ajouterAbonne(String nomAbo) {
		abonnes.add(new Abonne(nomAbo));
	}
	
	public static void ajouterLivre(String nom) {
		documents.add(new Livre(nom));
	}
	
	public static Document getDocuments(int numero) throws RechercheDocumentException {
		for(Document doc : documents) {
			if (doc.numero() == numero) {
				return doc;
			}
		}
		throw new RechercheDocumentException(numero);
	}
	
	public static void reserverDocument(int idDoc, int idAbo) throws EmpruntException {
		try {
			getDocuments(idDoc).reserver(getAbonne(idAbo));
		}
		catch (RechercheDocumentException e) {
			throw new EmpruntException(e, new ReservationException());
		} catch (RechercheAbonneException e2) {
			throw new EmpruntException(e2, new ReservationException());
		}
	}
	
	public static void emprunter(int idDoc, int idAbo) throws EmpruntException {
		try {
			getDocuments(idDoc).emprunter(getAbonne(idAbo));
		}
		catch (RechercheAbonneException e) {
			throw new EmpruntException(e);
		} catch (RechercheDocumentException e) {
			throw new EmpruntException(e);
		}
	}
	
	public static void retourner(int idDoc) throws RetourException {
		try {
			getDocuments(idDoc).retour();
		} catch (RechercheDocumentException e) {
			throw new RetourException(e);
		}
	}
	
}
