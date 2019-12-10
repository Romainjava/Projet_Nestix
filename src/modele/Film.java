package modele;

import java.util.ArrayList;

public class Film extends Media {
	
	private String resume_film;
	private int duree_film;

	public String getResume_film() {
		return resume_film;
	}

	public void setResume_film(String resume_film) {
		this.resume_film = resume_film;
	}

	public int getDuree_film() {
		return duree_film;
	}

	public void setDuree_film(int duree_film) {
		this.duree_film = duree_film;
	}
	@Override
	public String toString() {
		return "Film [resume_film=" + resume_film + ", duree_film=" + duree_film + ", toString()=" + super.toString()
				+ "]";
	}

	@Override
	public String[] toRowData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] toHeaderData() {
		// TODO Auto-generated method stub
		return null;
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

}
