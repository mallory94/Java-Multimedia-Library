package Serveur;

import java.io.IOException;
import java.net.ServerSocket;

import services.ServiceReservation;

public class ServeurReservation implements Runnable {
	private final int PORTreservation= 2500;
	private ServerSocket serverSocket;

	public ServeurReservation() throws IOException {
		serverSocket = new ServerSocket(PORTreservation);
	}

	
	@Override
	public void run() {
		try {
			while(true) {
				new Thread(new ServiceReservation(serverSocket.accept())).start();
				}
		}
		catch (IOException e) { 
			try {this.serverSocket.close();} catch (IOException e1) {}
			System.err.println("Pb sur le port d'écoute :"+e);
		}
	}
	
	public void lancer() {
		new Thread(this).start();
	}
	
}
