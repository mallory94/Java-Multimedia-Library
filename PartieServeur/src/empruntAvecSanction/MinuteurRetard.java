package empruntAvecSanction;

import java.util.Timer;
import java.util.TimerTask;

import bibliotheque.Abonne;

public class MinuteurRetard {
	private Timer timer;
	
	public MinuteurRetard(Abonne ab, DocumentAvecEmpruntSanctionnable doc ,int dureeAutoriseeEnJour) {
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				timer.cancel();
				synchronized (doc) {
					doc.setEnRetard(true);
				}
				System.out.println("L'abonne " + ab.getNom() + " possédant l'identifiant " + ab.getId() + 
						" sera sanctionné lorsqu'il rendra le document \"" + doc.getTitre() + "\"");
			}
		}, 1000 * 15 * dureeAutoriseeEnJour);
//		}, 1000 * 60 * 60 * 24 * dureeAutoriseeEnJour);
			    //min  h    j
	}
	
	public void annuler() {
		timer.cancel();
	}
}
