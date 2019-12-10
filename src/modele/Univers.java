package modele;

public class Univers implements I_requeteSQL{

	private int id_univers;
	private String nom_univer;

	@Override
	public String toString() {
		return "Univers [id_univers=" + id_univers + ", nom_univer=" + nom_univer + "]";
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
