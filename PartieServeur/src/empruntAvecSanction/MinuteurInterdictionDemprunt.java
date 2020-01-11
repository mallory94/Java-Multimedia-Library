package empruntAvecSanction;

import java.util.Timer;
import java.util.TimerTask;

import bibliotheque.Abonne;

public class MinuteurInterdictionDemprunt {
	private Timer timer;
	
	public MinuteurInterdictionDemprunt(Abonne ab, String motif, int dureeInterdictionEnJour) {
		System.out.println("L'abonne " + ab.getNom() + " possédant l'identifiant " + ab.getId() +
				 " s'est vu interdit d'emprunt de livre avec pour motif : " + motif);
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				timer.cancel();
				DocumentAvecEmpruntSanctionnable.retirerInterdictionAbonne(ab);
				System.out.println("L'abonne " + ab.getNom() + " possédant l'identifiant " + ab.getId() + 
						" s'est vu retirer son interdiction d'emprunt de livre");
			}
		}, 1000 * 30);
//		}, 1000 * 60 * 60 * 24 * dureeInterdictionEnJour);
			    //min  h    j
	}
	
	public void annuler() {
		timer.cancel();
	}
	
}
