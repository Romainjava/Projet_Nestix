package modele;

public class Saga extends ConnexionBDD implements RequetesSql {

	private int id_saga;
	private String nom_saga;

	public String getNom_saga() {
		return nom_saga;
	}

	public void setNom_saga(String nom_saga) {
		this.nom_saga = nom_saga;
	}

	public int getId_sage() {
		return id_saga;
	}

	public void setId_sage(int id_sage) {
		this.id_saga = id_sage;
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
		return "Saga [id_saga=" + id_saga + ", nom_saga=" + nom_saga + "]";
	}
}
