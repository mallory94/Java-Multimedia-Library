package services;

import java.net.Socket;
import serveur.Mediatheque;

public abstract class Service {
	private static int cpt = 1;
	private Mediatheque mediatheque;
	
	private final int numero;
	private final Socket client;
	
	public Service(Socket socket, Mediatheque mediatheque) {
		synchronized (this.getClass()) {
			this.numero = cpt++;
		}
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
}
