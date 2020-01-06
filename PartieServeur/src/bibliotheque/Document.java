package bibliotheque;

public interface Document {
	int getNumero();
	void reserver(Abonne ab) throws EmpruntException ;
	void emprunter(Abonne ab) throws EmpruntException; // retour document ou annulation réservation  void retour() throws RetourException; 
	void retour() throws RetourException;
}