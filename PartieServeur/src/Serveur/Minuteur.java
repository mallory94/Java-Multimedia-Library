package Serveur;

import java.util.Timer;
import java.util.TimerTask;

public class Minuteur {
	private Livre livre;
	private Timer timer;
	public Minuteur(Livre livreReserve) {
		this.livre = livreReserve;
		timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				timer.cancel();
				livre.supprimerReservation();
				System.out.println("La r�servation du livre \"" + livre.getTitre() + "\" s'est vue annul�e"
						+ " car l'abonn� ayant r�serv� n'a pas proc�d� � l'emprunt avant"
						+ " la fin de la dur�e de la r�servation." );
			}
		}, 1000 * 20);
	}
	
	public void annuler() {
		timer.cancel();
	}
}
