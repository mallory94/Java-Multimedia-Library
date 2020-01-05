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
				System.out.println("La réservation du livre \"" + livre.getTitre() + "\" s'est vue annulée"
						+ " car l'abonné ayant réservé n'a pas procédé à l'emprunt avant"
						+ " la fin de la durée de la réservation." );
			}
		}, 1000 * 20);
	}
	
	public void annuler() {
		timer.cancel();
	}
}
