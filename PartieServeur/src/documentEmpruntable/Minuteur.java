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
				System.out.println("La r�servation du document \"" + document.getTitre() + "\" s'est vue annul�e"
						+ " car l'abonn� ayant r�serv� n'a pas proc�d� � l'emprunt avant"
						+ " la fin de la dur�e de la r�servation." );
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
