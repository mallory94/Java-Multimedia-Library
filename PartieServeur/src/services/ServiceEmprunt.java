package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


import Serveur.Abonne;
import Serveur.Bibliotheque;
import Serveur.EmpruntException;

public class ServiceEmprunt extends Service implements Runnable {
	
	public ServiceEmprunt(Socket socket) {
		super(socket);
	}

	public void run() {
		System.out.println("*********Connexion "+this.getNumero()+" démarrée");
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader(this.getSocket().getInputStream()));
			PrintWriter out = new PrintWriter (this.getSocket().getOutputStream ( ), true);
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
				//enregistrerEmprunt(numeroLu,); // c'est casser
			}
			
			
		}
		catch (IOException e) {
		}
		//catch l'Emprunt exception
		
		System.out.println("*********Connexion "+this.getNumero()+" terminée");
		try {this.getSocket().close();} catch (IOException e2) {}
	}
	
	protected void finalize() throws Throwable {
		this.getSocket().close(); 
	}
	
	public void enregistrerEmprunt(int numeroDocument, Abonne abonne) throws EmpruntException {
		Bibliotheque.getDocuments(numeroDocument).emprunter(abonne);
	}
}
