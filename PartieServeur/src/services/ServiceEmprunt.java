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
		System.out.println("*********Connexion "+this.numero+" d�marr�e");
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter (client.getOutputStream ( ), true);
			String line = in.readLine();
			System.out.println("Connexion "+this.numero+" <-- "+line);
			String invLine = new String (new StringBuffer(line).reverse());
			out.println(invLine);
			System.out.println("Connexion "+this.numero+" --> "+invLine);
		}
		catch (IOException e) {
		}
		//catch l'Emprunt exception
		
		System.out.println("*********Connexion "+this.numero+" termin�e");
		try {client.close();} catch (IOException e2) {}
	}
	
	protected void finalize() throws Throwable {
		 client.close(); 
	}
	
	public void enregistrerEmprunt(int numeroDocument, Abonne abonne) throws EmpruntException {
		Bibliotheque.getDocuments(numeroDocument).emprunter(abonne);
	}
}
