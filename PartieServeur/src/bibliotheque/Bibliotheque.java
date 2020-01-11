package bibliotheque;

import java.util.ArrayList;

import documentEmpruntable.ReservationException;
import serveur.Mediatheque;

public class Bibliotheque implements Mediatheque {
	private ArrayList<Document> documents;
	private ArrayList<Abonne> abonnes;
	
	public Bibliotheque() {
		documents = new ArrayList<>();
		abonnes = new ArrayList<>();
	}
	
	public Abonne getAbonne(int idAbonne) throws RechercheAbonneException {
		for(Abonne abonne : abonnes) {
			if (abonne.getId() == idAbonne) {
				return abonne;
			}
		}
		throw new RechercheAbonneException(idAbonne);
	}
	
	public void ajouterAbonne(String nomAbo, String dateNaissance, String mail) {
		abonnes.add(new Abonne(nomAbo, dateNaissance, mail));
	}
	
	public  void ajouterDocument(Document doc) {
		documents.add(doc);
	}
	
	public  Document getDocument(int numero) throws RechercheDocumentException {
		for(Document doc : documents) {
			if (doc.getNumero() == numero) {
				return doc;
			}
		}
		throw new RechercheDocumentException(numero);
	}
	
	public void reserverDocument(int idDoc, int idAbo) throws EmpruntException {
		try {
			getDocument(idDoc).reserver(getAbonne(idAbo));
		}
		catch (RechercheDocumentException e) {
			throw new EmpruntException(e, new ReservationException());
		} catch (RechercheAbonneException e2) {
			throw new EmpruntException(e2, new ReservationException());
		}
	}
	
	public void emprunterDocument(int idDoc, int idAbo) throws EmpruntException {
		try {
			getDocument(idDoc).emprunter(getAbonne(idAbo));
		}
		catch (RechercheAbonneException e) {
			throw new EmpruntException(e);
		} catch (RechercheDocumentException e) {
			throw new EmpruntException(e);
		}
	}
	
	public void retournerDocument(int idDoc) throws RetourException {
		try {
			getDocument(idDoc).retour();
		} catch (RechercheDocumentException e) {
			throw new RetourException(e);
		}
	}

	public Mediatheque getReference() {
		return((Mediatheque) this);
	}
	

}
