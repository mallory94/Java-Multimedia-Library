package Serveur;

public interface Document {
	int numero();
	void reserver(Abonne ab) throws EmpruntException ;
	void emprunter(Abonne ab) throws EmpruntException; // retour document ou annulation r�servation  void retour() throws RetourException; 
	void retour() throws RetourException;
}
