package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Serveur.Abonne;
import Serveur.Bibliotheque;
import Serveur.EmpruntException;

public class ServiceEmprunt {
private static int cpt = 1;
	
	private final int numero;
	private final Socket client;
	
	ServiceEmprunt(Socket socket) {
		this.numero = cpt ++;
		this.client = socket;
	}

	public void run() {
		System.out.println("*********Connexion "+this.numero+" démarrée");
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter (client.getOutputStream ( ), true);
			out.println("Tapez le numéro d'un livre a Emprunter :");
			String reponse = in.readLine();
			Integer numeroLu = null;
			try {
				 numeroLu = Integer.valueOf(reponse);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			if (numeroLu != null) {
				out.println("Donnez votre identifiant.");
				enregistrerEmprunt(numeroLu,);
			}
			
			
		}
		catch (IOException e) {
		}
		//catch l'Emprunt exception
		
		System.out.println("*********Connexion "+this.numero+" terminée");
		try {client.close();} catch (IOException e2) {}
	}
	
	protected void finalize() throws Throwable {
		 client.close(); 
	}
	
	public void enregistrerEmprunt(int numeroDocument, Abonne abonne) throws EmpruntException {
		Bibliotheque.getDocuments(numeroDocument).emprunter(abonne);
	}
}
