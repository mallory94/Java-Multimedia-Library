package bibliotheque;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Abonne {
	
	private static int cpt = 0;
	private Date dateNaissance;
	private int id;
	private String nom;
	
	public Abonne() {
		id = cpt++;
	}
	
	@SuppressWarnings("deprecation")
	public Abonne(String nom, String Age) {
		this();
		dateNaissance = new Date(Age);
		System.out.println(this.getAge());
		this.nom = nom;
	}
	
	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}
	
	public int getAge() {
			    DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			    int d1 = Integer.parseInt(formatter.format(dateNaissance));
			    int d2 = Integer.parseInt(formatter.format(new Date()));
			    int age = (d2 - d1)/10000;
			    return age;
	}
	
}
