package empruntAvecSanction;

public enum EtatDegradation {
	DEGRADE("état dégradé"),
	NONDEGRADE("état non dégradé");
	
	private String name = "";
	
	EtatDegradation(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
