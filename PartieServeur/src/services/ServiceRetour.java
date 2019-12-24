package services;

import java.net.Socket;

public class ServiceRetour extends Service implements Runnable{
	
	public ServiceRetour(Socket socket) {
		super(socket);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}