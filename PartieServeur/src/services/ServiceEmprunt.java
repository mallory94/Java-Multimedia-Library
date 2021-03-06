package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import bibliotheque.EmpruntException;
import serveur.Mediatheque;
public class ServiceEmprunt extends Service implements Runnable {
	
	public ServiceEmprunt(Socket socket, Mediatheque mediatheque) {
		super(socket, mediatheque);
	}
	
	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;
		System.out.println("*********Connexion "+this.getNumero()+" d�marr�e");
		try {
			in = new BufferedReader(
					new InputStreamReader(this.getSocket().getInputStream())
					);
			out = new PrintWriter (this.getSocket().getOutputStream ( ), true);
			out.println("Tapez le num�ro d'un document a Emprunter :");
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
						System.out.println("L'abonn� poss�dant l'identifiant " + idAboLu + " vient "
								+ "d'emprunter le document num�ro " + numDocLu);
						out.println("votre emprunt a bien �t� pris en compte. Merci");
					} catch (EmpruntException e) {
						out.println(e.getMsgUtilisateur());
						e.printStackTrace();
					}
					
				}
			}
			in.close();
			out.close();
			
		}
		//catch l'Emprunt exception
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
		System.out.println("*********Connexion "+this.getNumero()+" termin�e");
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
