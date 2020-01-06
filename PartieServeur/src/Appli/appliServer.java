package Appli;

import java.io.IOException;

import Serveur.Mediatheque;
import Serveur.ServeurEmprunt;
import Serveur.ServeurReservation;
import Serveur.ServeurRetour;
import bibliotheque.Bibliotheque;

public class appliServer {

	public static void main(String[] args) {
		Bibliotheque bibliotheque = new Bibliotheque();
		//l'age est à renseigné en format ANGLAIS !   Mois/Jour/Année
		bibliotheque.ajouterAbonne("Jean", "01/06/1999");
		bibliotheque.ajouterAbonne("Claude", "02/01/1999");
		bibliotheque.ajouterDocumentEmpruntable("Luc va a la plage");
		bibliotheque.ajouterDocumentEmpruntable("Comment réussir tous ses DST en 10 étapes");
		Mediatheque mediatheque = bibliotheque;
		try {
			ServeurEmprunt serveurEmprunt = new ServeurEmprunt(mediatheque);
			ServeurReservation serveurReservation = new ServeurReservation(mediatheque);
			ServeurRetour serveurRetour = new ServeurRetour(mediatheque);
			serveurEmprunt.lancer();
			serveurReservation.lancer();
			serveurRetour.lancer();
			System.out.println("Le serveur a bien été lancé");
		} catch (IOException e) {
			System.out.println("Une ou plusieurs sockets relatives"
					+ " aux serveurs n'ont pas pu être instanciées");
			e.printStackTrace();
		}
	}
}
