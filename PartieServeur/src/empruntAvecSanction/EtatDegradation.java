package empruntAvecSanction;

public enum EtatDegradation {
	DEGRADE("�tat d�grad�"),
	NONDEGRADE("�tat non d�grad�");
	
	private String name = "";
	
	EtatDegradation(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
