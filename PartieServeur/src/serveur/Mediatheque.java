package serveur;

import bibliotheque.Document;
import bibliotheque.EmpruntException;
import bibliotheque.RechercheDocumentException;
import bibliotheque.RetourException;

public interface Mediatheque {
	public void reserverDocument(int idDoc, int idAbo) throws EmpruntException;
	public void emprunterDocument(int idDoc, int idAbo) throws EmpruntException;
	public void retournerDocument(int idDoc) throws RetourException;
	public Document getDocument(int idDoc) throws RechercheDocumentException;
}