package services;

import java.net.Socket;

public abstract class Service {
	private static int cpt = 1;
	
	private final int numero;
	private final Socket client;
	
	public Service(Socket socket) {
		this.numero = cpt ++;
		this.client = socket;
	}
	
	protected int getNumero() {
		return this.getNumero();
		
	}
	
	protected Socket getSocket() {
		return this.client;
	}
}
