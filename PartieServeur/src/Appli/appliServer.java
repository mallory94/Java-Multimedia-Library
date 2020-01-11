package Appli;

import java.io.IOException;

import bibliotheque.Bibliotheque;
import dvd.Dvd;
import empruntAvecSanction.DocumentAvecEmpruntSanctionnable;
import serveur.Mediatheque;
import serveur.ServeurEmprunt;
import serveur.ServeurReservation;
import serveur.ServeurRetour;

public class appliServer {

	public static void main(String[] args) {
		Bibliotheque bibliotheque = new Bibliotheque();
		//l'age est � renseign� en format ANGLAIS !   Mois/Jour/Ann�e
		bibliotheque.ajouterAbonne("Jean", "03/10/2008", "salutamyamigo@gmail.com");
		bibliotheque.ajouterAbonne("Claude", "01/01/2008", "malloga94@gmail.com");
		bibliotheque.ajouterAbonne("Brette", "05/02/1989", "malloga94@gmail.com");
		bibliotheque.ajouterDocument(new DocumentAvecEmpruntSanctionnable("Luc va a la plage"));
		bibliotheque.ajouterDocument(new DocumentAvecEmpruntSanctionnable("Comment r�ussir tous ses DST en 10 �tapes"));
		bibliotheque.ajouterDocument(new Dvd("Le grand bleu"));
		bibliotheque.ajouterDocument(new Dvd("Les dents de la mer", 12));
		bibliotheque.ajouterDocument(new Dvd("Saw", 16));
		Mediatheque mediatheque = bibliotheque;
		try {
			ServeurEmprunt serveurEmprunt = new ServeurEmprunt(mediatheque);
			ServeurReservation serveurReservation = new ServeurReservation(mediatheque);
			ServeurRetour serveurRetour = new ServeurRetour(mediatheque);
			serveurEmprunt.lancer();
			serveurReservation.lancer();
			serveurRetour.lancer();
			System.out.println("Le serveur a bien �t� lanc�");
		} catch (IOException e) {
			System.out.println("Une ou plusieurs sockets relatives"
					+ " aux serveurs n'ont pas pu �tre instanci�es");
			e.printStackTrace();
		}
	}
}
