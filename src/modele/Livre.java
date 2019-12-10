package modele;

import java.util.ArrayList;

public class Livre extends Media {
	private String resume_livre;
	private int tome_livre;
	private int ISBN;
	
	
	public String[] toRowData() {
		String[] data = {this.titre_media, this.ISBN + "", this.annee_sortie_media};
		return data;
	}
	
	public String[] toHeaderData() {
		String[] data = {"Titre","ISBN", "Genre", "Annee"};
		return data;
	}
	
	public String getResume_livre() {
		return resume_livre;
	}

	public void setResume_livre(String resume_livre) {
		this.resume_livre = resume_livre;
	}

	public int getTome_livre() {
		return tome_livre;
	}

	public void setTome_livre(int tome_livre) {
		this.tome_livre = tome_livre;
	}

	public int getISBN() {
		return ISBN;
	}

	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}

	@Override
	public String toString() {
		return "Livre [resume_livre=" + resume_livre + ", tome_livre=" + tome_livre + ", ISBN=" + ISBN + ", toString()="
				+ super.toString() + "]";
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
