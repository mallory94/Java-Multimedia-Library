package documentEmpruntable;

import java.util.Timer;
import java.util.TimerTask;

public class Minuteur {
	private DocumentEmpruntable document;
	private Timer timer;
	public Minuteur(DocumentEmpruntable documentReserve) {
		this.document = documentReserve;
		timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				timer.cancel();
				document.supprimerReservation();
				System.out.println("La réservation du document \"" + document.getTitre() + "\" s'est vue annulée"
						+ " car l'abonné ayant réservé n'a pas procédé à l'emprunt avant"
						+ " la fin de la durée de la réservation." );
				synchronized (documentReserve) {
					documentReserve.notifyAll();
				}
			}
		}, 1000 * 20);
	}
	
	public void annuler() {
		timer.cancel();
	}
}
