package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import alerteMailDisponibilite.Mailer;
import bibliotheque.Abonne;
import bibliotheque.Document;
import bibliotheque.EmpruntException;
import documentEmpruntable.ReservationException;
import serveur.Mediatheque;

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
						if (e.alerteMailPossible()) {
							boolean aReponduCorrectement = false;
							String reponse3 = null;
							//while (!aReponduCorrectement) {
								out.println("Voulez-vous recevoir une alerte par mail quand le document sera disponible? "
									+ "Entrez O pour 'oui' ou n pour 'non'");
								reponse3 = in.readLine().trim().toLowerCase();
								if (reponse3.equals("o") || reponse3.equals("n") ) {
									aReponduCorrectement = true;
								}
							//}
							if (aReponduCorrectement) {
								if (reponse3.equals("o")) {
									System.out.println(e.getReservException().getAboVoulantEmprunter() + " " + e.getReservException().getDocConcerne());
									new Mailer(e.getReservException());
								}
							}
						}
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
