package documentEmpruntable;

import bibliotheque.Abonne;
import bibliotheque.Document;
import bibliotheque.EmpruntException;
import bibliotheque.RetourException;

public class DocumentEmpruntable implements Document {

	private Minuteur minuteur;
	private static int cpt = 0;
	private int numero;
	private String nom;
	private Abonne abonneReserver;
	private boolean isEmprunter;
	
	public DocumentEmpruntable(String nom) {
		this.numero = cpt;
		cpt++;
		this.nom = nom;
		this.abonneReserver = null;
		this.isEmprunter = false;
	}
	
	
	public int getNumero() {
		return this.numero;
	}
	
	public void reserver(Abonne ab) throws EmpruntException {
		synchronized (this) {
			if (this.abonneReserver == null) {
				if (this.isEmprunter == false) {
					this.abonneReserver = ab;
					minuteur = new Minuteur(this);
				}
				else {
					throw new EmpruntException(new DejaEmprunteException(ab.getNom()),
												new ReservationException());
				}
			}
			else {
				throw new EmpruntException(new DejaReserverException(ab.getNom()),
											new ReservationException());
			}
			this.notifyAll();
		}
		
	}

	public void emprunter(Abonne ab) throws EmpruntException {
		synchronized (this) {
//			try {
//				if (ab.getId() == 0) {
//					System.out.println("attends");
//					Thread.sleep(1000*30);
//					
//				}
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			if ( isEmprunter == false ) {
				if 	( ab == this.abonneReserver || this.abonneReserver == null) {
					this.isEmprunter = true;
					this.abonneReserver = null;
					/* v�rifie si le document a �t� r�serv�. Annule le minuteur de la dur�e de r�servation
					*	si c'est le cas
					*/
					if (minuteur != null ) {
						minuteur.annuler();
						minuteur = null;
						System.out.println("La r�servation du document \"" + nom + "\" s'est vue annul�e parce que "
								+ "l'abonn� l'ayant r�serv� a proc�d� � son emprunt");
					}
				}
				else {
					throw new EmpruntException(new DejaReserverException(ab.getNom()));
				}

				
			} else {
				throw new EmpruntException(new DejaEmprunteException(ab.getNom()));
			}
			this.notifyAll();
		}
	}

	
	public void retour() throws RetourException {
		synchronized (this) {
			if (this.isEmprunter == false && this.abonneReserver == null) {
				throw new RetourException(new NonEmprunteNonReserverException(this.numero));
			}
			else if (this.abonneReserver != null) {
				this.abonneReserver = null;
				minuteur.annuler();
				minuteur = null;
				System.out.println("La r�servation du document \"" + nom + "\" s'est vue annul�e parce que "
						+ "l'abonn� l'ayant r�serv� a proc�d� l'annulation de la r�servation");
			}
			this.isEmprunter = false;
			this.notifyAll();
		}
	}
	
	public void supprimerReservation() {
		synchronized (this) {
			if (this.abonneReserver != null && isEmprunter == false) {
				this.abonneReserver = null;
				minuteur.annuler();
				minuteur = null;
			}
			this.notifyAll();
		}
	}
	
	public String getTitre() {
		return nom;
	}
}
