package modele;

import java.util.ArrayList;

public class Univers extends Info{
	
	@Override
	public boolean modification(int id) {
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

	@Override
	protected String getTableName() {
		
		return "univers";
	}
	
	
}
