package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import bibliotheque.RetourException;
import serveur.Mediatheque;

public class ServiceRetour extends Service implements Runnable{
	
	public ServiceRetour(Socket socket, Mediatheque mediatheque) {
		super(socket, mediatheque);
	}
	
	@Override
	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader(
					new InputStreamReader(this.getSocket().getInputStream())
					);
			out = new PrintWriter (this.getSocket().getOutputStream ( ), true);
			out.println("Entrez le numero du document � retourner :");
			String reponse = in.readLine();
			Integer numeroLu = null;
			try {
				 numeroLu = Integer.valueOf(reponse);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			if (numeroLu != null) {
				try {
					getMediatheque().retournerDocument(numeroLu.intValue());
					out.println("votre retour d'emprunt/annulation de r�servation"
							+ " a bien �t� pris en compte. Merci");
				} catch (RetourException e) {
					out.println(e.getMsgUtilisateur());
					e.printStackTrace();
				}
			}
			in.close();
			out.close();
			getSocket().close();
		}
		catch (IOException e) {
			System.out.println("Erreur lors de la manipulation de socket");
		}
		finally {
			try {
				in.close();
				out.close();
				getSocket().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	
}