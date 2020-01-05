package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Serveur.Mediatheque;

import bibliotheque.EmpruntException;
public class ServiceEmprunt extends Service implements Runnable {
	
	public ServiceEmprunt(Socket socket, Mediatheque mediatheque) {
		super(socket, mediatheque);
	}
	
	public void run() {

		System.out.println("*********Connexion "+this.getNumero()+" démarrée");
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(this.getSocket().getInputStream())
					);
			PrintWriter out = new PrintWriter (this.getSocket().getOutputStream ( ), true);
			out.println("Tapez le numéro d'un livre a Emprunter :");
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
						enregistrerEmprunt(numDocLu, idAboLu);
						System.out.println("L'abonné possèdant l'identifiant " + idAboLu + " vient "
								+ "d'emprunter le document numéro " + numDocLu);
						out.println("votre emprunt a bien été pris en compte. Merci");
					} catch (EmpruntException e) {
						out.println(e.getMsgUtilisateur());
						e.printStackTrace();
					}
					
				}
			}
			
			
		}
		//catch l'Emprunt exception
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("*********Connexion "+this.getNumero()+" terminée");
		try {
			this.getSocket().close();
		} 
		catch (IOException e2) {
			e2.printStackTrace();
		}
		
	}
	
	protected void finalize() throws Throwable {
		this.getSocket().close(); 
	}
	
	
	public void enregistrerEmprunt(int numeroDocument, int idAbonne) throws EmpruntException {
			try {
				this.getMediatheque().emprunterDocument(numeroDocument, idAbonne);
			} catch (EmpruntException e) {
				throw e;
			}
	}
	
}
