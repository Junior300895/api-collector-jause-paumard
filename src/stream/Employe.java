package stream;

public class Employe {

	private String id;
	public enum Genre {HOMME, FEMME}
	private int taille;
	private int salaire;
	
	public Employe() {
		super();
	}
	
	

	public Employe(String id, int taille, int salaire) {
		super();
		this.id = id;
		this.taille = taille;
		this.salaire = salaire;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public int getSalaire() {
		return salaire;
	}

	public void setSalaire(int salaire) {
		this.salaire = salaire;
	}
	
	
}
