package Serveur;

import java.io.IOException;
import java.net.ServerSocket;

import services.ServiceRetour;

public class ServeurRetour implements Runnable {
	//private final int PORTreservation= 2500;
	//private final int PORTemprunt = 2600;
	private final int PORTretours = 2700;
	private ServerSocket serverSocket;

	ServeurRetour() throws IOException {
		serverSocket = new ServerSocket(PORTretours);
	}

	
	@Override
	public void run() {
		try {
			while(true) {
				new Thread(new ServiceRetour(serverSocket.accept())).start();
				}
		}
		catch (IOException e) { 
			try {this.serverSocket.close();} catch (IOException e1) {}
			System.err.println("Pb sur le port d'�coute :"+e);
		}
	}
	
	
}

