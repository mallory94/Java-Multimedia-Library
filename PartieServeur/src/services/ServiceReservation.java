package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Serveur.Mediatheque;
import bibliotheque.EmpruntException;

public class ServiceReservation extends Service implements Runnable{
	
	public ServiceReservation(Socket socket, Mediatheque mediatheque) {
		super(socket, mediatheque);
	}
	
	@Override
	public void run() {
		System.out.println("*********Connexion "+this.getNumero()+" d�marr�e");
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(this.getSocket().getInputStream())
					);
			PrintWriter out = new PrintWriter (this.getSocket().getOutputStream ( ), true);
			out.println("Tapez le num�ro d'un document � r�server :");
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
						getMediatheque().reserverDocument(numDocLu, idAboLu);
						out.println("votre r�servation a bien �t� prise en compte. Merci");
						System.out.println("L'abonn� dont l'identifiant est " + idAboLu + " vient de r�server"
								+ " le document num�ro " + numDocLu);
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
		
		System.out.println("*********Connexion "+this.getNumero()+" termin�e");
	}
	

	
}
