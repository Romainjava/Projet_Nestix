package modele;

public class Genre implements I_requeteSQL {

	
	private int id_genre;
	private String nom_genre;

	public int getId_genre() {
		return id_genre;
	}

	public void setId_genre(int id_genre) {
		this.id_genre = id_genre;
	}

	public String getNom_genre() {
		return nom_genre;
	}

	public void setNom_genre(String nom_genre) {
		this.nom_genre = nom_genre;
	}
	@Override
	public String toString() {
		return "Genre [id_genre=" + id_genre + ", nom_genre=" + nom_genre + "]";
	}

	@Override
	public boolean creation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modification(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean lireUn(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean suppression(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
