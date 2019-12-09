package modele;

public class Artiste extends ConnexionBDD implements RequetesSql {

	private int id_artiste;
	private String nom_artiste;
	private String prenom_artiste;
	private String surnom_artiste;
	private String dob_artiste;

	public int getId_artiste() {
		return id_artiste;
	}

	public void setId_artiste(int id_artiste) {
		this.id_artiste = id_artiste;
	}

	public String getNom_artiste() {
		return nom_artiste;
	}

	public void setNom_artiste(String nom_artiste) {
		this.nom_artiste = nom_artiste;
	}

	public String getPrenom_artiste() {
		return prenom_artiste;
	}

	public void setPrenom_artiste(String prenom_artiste) {
		this.prenom_artiste = prenom_artiste;
	}

	public String getSurnom_artiste() {
		return surnom_artiste;
	}

	public void setSurnom_artiste(String surnom_artiste) {
		this.surnom_artiste = surnom_artiste;
	}

	public String getDob_artiste() {
		return dob_artiste;
	}

	public void setDob_artiste(String dob_artiste) {
		this.dob_artiste = dob_artiste;
	}

	@Override
	public boolean creation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modification() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean lecture() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean suppression() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return "Artiste [id_artiste=" + id_artiste + ", nom_artiste=" + nom_artiste + ", prenom_artiste="
				+ prenom_artiste + ", surnom_artiste=" + surnom_artiste + ", dob_artiste=" + dob_artiste + "]";
	}
}
