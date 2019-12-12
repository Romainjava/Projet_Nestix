package modele;

import java.util.ArrayList;

public class Editeur extends Info{
	
	public Editeur() {
		super();
	}

	public Editeur(int pId, String pNom) {
		super(pId, pNom);
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

	@Override
	protected String getTableName() {
		return "editeur";
	}
	
	

}
