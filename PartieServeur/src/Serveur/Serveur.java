//package Serveur;
//
//import java.io.IOException;
//import java.net.ServerSocket;
//
//public class Serveur implements Runnable {
//	private final int PORTreservation= 2500;
//	private final int PORTemprunt = 2600;
//	private final int PORTretours = 2700;
//	private ServerSocket serverSocket;
//
//	Serveur(int port) throws IOException {
//		serverSocket = new ServerSocket(port);
//	}
//
//	
//	@Override
//	public void run() {
//		try {
//			while(true) {
//				//new Thread(new ServiceInversion(serverSocket.accept())).start();
//				switch(serverSocket.getLocalPort()) {
//					case PORTreservation:  
//						break;
//					case PORTemprunt:
//						break;
//					case PORTretours:
//						break;
//					default:
//				}
//				
//				
//				System.out.println("ça tourne en rond");
//			}
//		}
//		catch (IOException e) { 
//			try {this.serverSocket.close();} catch (IOException e1) {}
//			System.err.println("Pb sur le port d'écoute :"+e);
//		}
//	}
//	
//	
//}
