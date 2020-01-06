package serveur;

import java.io.IOException;
import java.net.ServerSocket;

import services.ServiceReservation;

public class ServeurReservation extends Serveur implements Runnable {
	private static final int PORTreservation = 2500;

	public ServeurReservation(Mediatheque mediatheque) throws IOException {
		super(PORTreservation, mediatheque);
	}

	
	@Override
	public void run() {
		try {
			while(true) {
				new Thread(new ServiceReservation(getServerSocket().accept(), getMediatheque())).start();
				}
		}
		catch (IOException e) { 
			try {getServerSocket().close();} catch (IOException e1) {}
			System.err.println("Pb sur le port d'écoute :"+e);
		}
	}
	
	public void lancer() {
		new Thread(this).start();
	}
	
}
