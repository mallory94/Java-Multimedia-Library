package documentEmpruntable;

public class DejaReserverException extends Exception{

	private static final long serialVersionUID = 8008134921702441144L;
	
	private String nomAboVoulantReserver; //nom de l'abonne souhaitant reserver.
	
	public DejaReserverException() {
		super();
	}
	
	public DejaReserverException(String nomAboVoulantReserver) {
		super();
		this.nomAboVoulantReserver = nomAboVoulantReserver;
	}
	
	//renvoit le nom de l'abonne souhaitant reserver
	public String getNomAboVoulantReserver() {
		return nomAboVoulantReserver;
	}
	
}
