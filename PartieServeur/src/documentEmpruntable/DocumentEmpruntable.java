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
	private boolean estEmprunte;
	
	public DocumentEmpruntable(String nom) {
		this.numero = cpt;
		cpt++;
		this.nom = nom;
		this.abonneReserver = null;
		this.estEmprunte = false;
	}
	
	
	public int getNumero() {
		return this.numero;
	}
	
	public void reserver(Abonne ab) throws EmpruntException {
		synchronized (this) {
			if (this.abonneReserver == null) {
				if (this.estEmprunte == false) {
					this.abonneReserver = ab;
					minuteur = new Minuteur(this);
				}
				else {
					throw new EmpruntException(new DejaEmprunteException(),
												new ReservationException(ab,this));
					
				}
			}
			else {
				throw new EmpruntException(new DejaReserverException(),
											new ReservationException(ab,this));
				
			}
		}	
	}
	
	public void emprunter(Abonne ab) throws EmpruntException {
		@SuppressWarnings("unused")
		boolean reservationAnnulee = false;
		synchronized (this) {
			if ( estEmprunte == false ) {
				if 	( ab == this.abonneReserver || this.abonneReserver == null) {
					this.estEmprunte = true;
					this.abonneReserver = null;
					/* vérifie si le document a été réservé. Annule le minuteur de la durée de réservation
					*	si c'est le cas
					*/
					if (minuteur != null ) {
						minuteur.annuler();
						minuteur = null;
						reservationAnnulee = true;
					}
				}
				else {
					throw new EmpruntException(new DejaReserverException(ab.getNom()));
				}
				
			} else {
				throw new EmpruntException(new DejaEmprunteException());
			}
		}
		if (reservationAnnulee = true) {
			System.out.println("La réservation du document \"" + nom + "\" s'est vue annulée parce que "
					+ "l'abonné l'ayant réservé a procédé à son emprunt");
		}
	}

	
	public void retour() throws RetourException {
		boolean annulationReservation = false;
		synchronized (this) {
			if (this.estEmprunte == false && this.abonneReserver == null) {
				throw new RetourException(new NonEmprunteNonReserverException(this.numero));
			}
			else if (this.abonneReserver != null) {
				this.abonneReserver = null;
				minuteur.annuler();
				minuteur = null;
				annulationReservation = true;
			}
			this.estEmprunte = false;
			this.notifyAll();
		}
		if (annulationReservation) {
			System.out.println("La réservation du document \"" + nom + "\" s'est vue annulée parce que "
				+ "l'abonné l'ayant réservé a procédé l'annulation de la réservation");
		}
	}
	
	public void supprimerReservation() {
		synchronized (this) {
			if (this.abonneReserver != null && estEmprunte == false) {
				this.abonneReserver = null;
				minuteur.annuler();
				minuteur = null;
			}
		}
	}
	
	public String getTitre() {
		return nom;
	}
	
	public synchronized boolean estEmprunte() {
		return(estEmprunte);
	}
}
