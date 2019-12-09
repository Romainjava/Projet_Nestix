package modele;

public class Univers extends ConnexionBDD implements RequetesSql{

	private int id_univers;
	private String nom_univer;
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
		return "Univers [id_univers=" + id_univers + ", nom_univer=" + nom_univer + "]";
	}
	
	
}
