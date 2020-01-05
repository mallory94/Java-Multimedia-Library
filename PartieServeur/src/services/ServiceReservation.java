package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Serveur.Bibliotheque;
import Serveur.EmpruntException;

public class ServiceReservation extends Service implements Runnable{
	
	public ServiceReservation(Socket socket) {
		super(socket);
	}
	
	@Override
	public void run() {
		System.out.println("*********Connexion "+this.getNumero()+" démarrée");
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(this.getSocket().getInputStream())
					);
			PrintWriter out = new PrintWriter (this.getSocket().getOutputStream ( ), true);
			out.println("Tapez le numéro d'un livre à réserver :");
			String reponse = in.readLine();
			Integer numDocLu = null;
			try {
				numDocLu = Integer.valueOf(reponse);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			if (numDocLu != null) {
				out.println("Donnez votre identifiant.");
				String reponse2 = in.readLine();
				Integer idAboLu = null;
				try {
					 idAboLu = Integer.valueOf(reponse2);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				if (idAboLu != null) {
					try {
						Bibliotheque.reserverDocument(numDocLu, idAboLu);
						out.println("votre réservation a bien été prise en compte. Merci");
						System.out.println("L'abonné dont l'identifiant est " + idAboLu + " vient de réserver"
								+ " le document numéro " + numDocLu);
					} catch (EmpruntException e) {
						out.println(e.getMsgUtilisateur());
						e.printStackTrace();
					}
				}
			}
			
		}
		//catch l'Emprunt exception
		catch (IOException e) {
			System.out.println("Erreur lors de la manipulation de socket");
		}
		
		System.out.println("*********Connexion "+this.getNumero()+" terminée");
	}
	

	
}
