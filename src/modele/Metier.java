package modele;

public class Metier extends ConnexionBDD implements I_requetesSql {

	private int id_metier;
	private String nom_metier;

	public int getId_metier() {
		return id_metier;
	}

	public void setId_metier(int id_metier) {
		this.id_metier = id_metier;
	}

	public String getNom_metier() {
		return nom_metier;
	}

	public void setNom_metier(String nom_metier) {
		this.nom_metier = nom_metier;
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

}
