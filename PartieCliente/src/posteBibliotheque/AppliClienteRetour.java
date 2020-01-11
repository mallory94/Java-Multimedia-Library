package posteBibliotheque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class AppliClienteRetour {
	public static void main(String[] args) {
		int PORTretours = 2700;
		Scanner sc = new Scanner(System.in);
		
		try {
			Socket maSocket = new Socket("127.0.0.1",PORTretours);
			BufferedReader socketIn = new BufferedReader(
					new InputStreamReader(maSocket.getInputStream()));
			PrintWriter socketOut = new PrintWriter(maSocket.getOutputStream(),true);
			
			System.out.println(socketIn.readLine());
			socketOut.println(sc.nextLine());

			String reponse;
			for (int i = 0; i <= 2; i++) {
				reponse = null;
				reponse = socketIn.readLine();
				if (reponse != null) {
					System.out.println(reponse);
				}
			}
			
			
			try {
				maSocket.close();
			} catch (Exception e2) {
				System.out.println("Erreur lors de la fermeture de la socket");
				e2.printStackTrace();
			}
			sc.close();
		} catch (UnknownHostException e) {
			System.out.println("Probleme de connexion au resesau");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Erreur lors de l'ouverture de la socket.");
			e.printStackTrace();
		}
		finally {
			try {
				sc.close();
			} catch (Exception e2) {
				System.out.println("Erreur lors de la fermeture du scanner");
			}
		}
	}

}
