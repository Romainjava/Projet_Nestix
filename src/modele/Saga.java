package modele;

import java.util.ArrayList;

public class Saga implements I_requeteSQL,I_recherche {

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
	public String toString() {
		return "Saga [id_saga=" + id_saga + ", nom_saga=" + nom_saga + "]";
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
