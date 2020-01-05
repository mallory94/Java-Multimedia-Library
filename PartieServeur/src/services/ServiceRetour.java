package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import bibliotheque.Bibliotheque;
import bibliotheque.RetourException;

public class ServiceRetour extends Service implements Runnable{
	
	public ServiceRetour(Socket socket) {
		super(socket);
	}
	
	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(this.getSocket().getInputStream())
					);
			PrintWriter out = new PrintWriter (this.getSocket().getOutputStream ( ), true);
			out.println("Entrez le numero du livre � retourner :");
			String reponse = in.readLine();
			Integer numeroLu = null;
			try {
				 numeroLu = Integer.valueOf(reponse);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			if (numeroLu != null) {
				try {
					Bibliotheque.retourner(numeroLu.intValue());
					out.println("votre retour d'emprunt/annulation de r�servation"
							+ " a bien �t� pris en compte. Merci");
				} catch (RetourException e) {
					out.println(e.getMsgUtilisateur());
					e.printStackTrace();
				}
			}
		}
		catch (IOException e) {
			System.out.println("Erreur lors de la manipulation de socket");
		}
		
	}
	
	
	
}