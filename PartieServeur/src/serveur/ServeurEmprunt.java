package serveur;

import java.io.IOException;
import services.ServiceEmprunt;

public class ServeurEmprunt extends Serveur implements Runnable {
	private static final int PORTemprunt = 2600;

	public ServeurEmprunt(Mediatheque mediatheque) throws IOException {
		super(PORTemprunt,mediatheque);
	}

	
	@Override
	public void run() {
		try {
			while(true) {
				new Thread(new ServiceEmprunt(getServerSocket().accept(), getMediatheque())).start();
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
