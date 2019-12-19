package Serveur;

import java.util.ArrayList;

public class Bibliotheque {
	private static ArrayList<Document> documents = new ArrayList<>();
	
	/*public boolean emprunter(int numeroDoc) {
		this.getDocuments(numeroDoc).emprunter(ab);
		return false;
	}*/
	
	public static Document getDocuments(int numero) {
		for(Document livre : documents) {
			if (livre.numero() == numero) {
				return livre;
			}
		}
		return null;
	}
}
