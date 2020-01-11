package serveur;

import java.io.IOException;
import services.ServiceRetour;

public class ServeurRetour extends Serveur implements Runnable {
	private static final int PORTretour = 2700;

	public ServeurRetour(Mediatheque mediatheque) throws IOException {
		super(PORTretour,mediatheque);
	}

	
	@Override
	public void run() {
		try {
			while(true) {
				new Thread(new ServiceRetour(getServerSocket().accept(), getMediatheque())).start();
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

