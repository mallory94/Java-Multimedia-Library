package serveur;

import bibliotheque.EmpruntException;
import bibliotheque.RetourException;

public interface Mediatheque {
	public void reserverDocument(int idDoc, int idAbo) throws EmpruntException;
	public void emprunterDocument(int idDoc, int idAbo) throws EmpruntException;
	public void retournerDocument(int idDoc) throws RetourException;
}