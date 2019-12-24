package Serveur;

import java.io.IOException;
import java.net.ServerSocket;

import services.ServiceEmprunt;

public class ServeurEmprunt implements Runnable {
	//private final int PORTreservation= 2500;
	private final int PORTemprunt = 2600;
	//private final int PORTretours = 2700;
	private ServerSocket serverSocket;

	ServeurEmprunt() throws IOException {
		serverSocket = new ServerSocket(PORTemprunt);
	}

	
	@Override
	public void run() {
		try {
			while(true) {
				new Thread(new ServiceEmprunt(serverSocket.accept())).start();
				}
		}
		catch (IOException e) { 
			try {this.serverSocket.close();} catch (IOException e1) {}
			System.err.println("Pb sur le port d'écoute :"+e);
		}
	}
	
	
}
