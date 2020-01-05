package Appli;

import java.io.IOException;

import Serveur.Bibliotheque;
import Serveur.ServeurEmprunt;
import Serveur.ServeurReservation;
import Serveur.ServeurRetour;

public class appliServer {

	public static void main(String[] args) {
		Bibliotheque.ajouterAbonne("Jean");
		Bibliotheque.ajouterAbonne("Claude");
		Bibliotheque.ajouterLivre("Luc va a la plage");
		Bibliotheque.ajouterLivre("Comment réussir tous ses DST en 10 étapes");
		try {
			
			ServeurEmprunt serveurEmprunt = new ServeurEmprunt();
			ServeurReservation serveurReservation = new ServeurReservation();
			ServeurRetour serveurRetour = new ServeurRetour();
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
