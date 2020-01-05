package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.imageio.IIOException;

import Serveur.Mediatheque;

public abstract class Service {
	private static int cpt = 1;
	private Mediatheque mediatheque;
	
	private final int numero;
	private final Socket client;
	
	public Service(Socket socket, Mediatheque mediatheque) {
		this.numero = cpt++;
		this.client = socket;
		this.mediatheque = mediatheque;
	}
	
	protected int getNumero() {
		return numero;
	}
	
	public Mediatheque getMediatheque() {
		return(mediatheque);
	}
	
	protected Socket getSocket() {
		return this.client;
	}
	
	//tentative de factorisation d'une fonction qui lit un nombre dans la socket
	//A SUPPRIMER si la tentative echoue
	public int lireIntDansSocket() {
		Integer numeroLu = null;
		try {
			BufferedReader in = new BufferedReader(
				new InputStreamReader(this.getSocket().getInputStream())
				);
			String reponse = in.readLine();
			try {
				 numeroLu = Integer.valueOf(reponse);
			} 
			catch (NumberFormatException e) {
				e.printStackTrace();
			}
			if (numeroLu != null) {
				return(numeroLu);
			}
		}
		catch (IOException e) {
			System.out.println("Probleme dans la lecture de la socket");
		}
		
		
		return (Integer) null; //empeche l'affichage d'une erreur de return
	}
		
		
}
