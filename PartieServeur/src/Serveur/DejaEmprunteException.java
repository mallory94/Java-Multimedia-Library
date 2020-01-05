package Serveur;

public class DejaEmprunteException extends Exception {

	private static final long serialVersionUID = -4023131409000030540L;
	
	private String nomAboVoulantEmprunter; //nom de l'abonne souhaitant Emprunter.
	
	public DejaEmprunteException(String nomAboVoulantEmprunter) {
		super();
		this.nomAboVoulantEmprunter = nomAboVoulantEmprunter;
	}
	
	//renvoit le nom de l'abonne souhaitant Emprunter
	public String getNomAboVoulantEmprunter() {
		return nomAboVoulantEmprunter;
	}
}
