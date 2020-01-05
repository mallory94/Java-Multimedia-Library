package Serveur;

import java.io.IOException;
import java.net.ServerSocket;

public class Serveur {
	private ServerSocket serverSocket;
	Mediatheque mediatheque;
	
	Serveur(int port, Mediatheque mediatheque) throws IOException {
		this.mediatheque = mediatheque;
		serverSocket = new ServerSocket(port);
	}
	
	public ServerSocket getServerSocket() {
		return(serverSocket);
	}
	
	public Mediatheque getMediatheque() {
		return(mediatheque);
	}
}
