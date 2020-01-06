package services;

import java.net.Socket;

import Serveur.Mediatheque;

public class ServiceAlerteMail extends Service {

	public ServiceAlerteMail(Socket socket, Mediatheque mediatheque) {
		super(socket, mediatheque);
		
	}
	
}
