package modele;

import java.util.ArrayList;

public class Univers implements I_requeteSQL,I_recherche{

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

	@Override
	public ArrayList<I_recherche> lectureTout(int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean recherchePar(int limit) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] toRowData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] toHeaderData() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
