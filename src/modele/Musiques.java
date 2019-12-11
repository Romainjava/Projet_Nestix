package modele;

import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Musiques extends Media {

	private int duree_musique;

	public int getDuree_musique() {
		return duree_musique;
	}

	public void setDuree_musique(int duree_musique) {
		this.duree_musique = duree_musique;
	}

	@Override
	public String toString() {
		return "Musiques [duree_musique=" + duree_musique + ", toString()=" + super.toString() + "]";
	}

	@Override
	public String[] toRowData() {
		String[] data = { this.titre_media, this.genres.toString(), this.annee_sortie_media };
		return data;
	}

	@Override
	public String[] toHeaderData() {
		String[] data = { "Titre", "Annee de sortie", "Duree", "Album" };
		return data;
	}

	@Override
	public boolean creation() {
		boolean success;
		try {
			String query = "INSERT INTO nestix_media(titre_media,date_crea_media,annee_sortie_media,admin_id,univers_id,image_id,saga_id,etat_id) VALUES(?,?)";
			PreparedStatement statement = (PreparedStatement) ConnexionBDD.getConnexion().prepareStatement(query);
		} catch (Exception e) {
			// TODO: handle exception
		}
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

//	public static void main(String[] args) {
//		Musiques musique= new Musiques();
//		musique.setTitre_media("test");
//		Genre genre = new Genre();
//		genre.setNom_genre("rock");
//		Genre genre2 = new Genre();
//		genre2.setNom_genre("pop");
//		musique.addGenre(genre);
//		musique.addGenre(genre2);
//		ConnexionBDD.startConnection();
//		musique.setAnnee_sortie_media("1060");
//		for (String iterable_element : musique.toRowData()) {
//			System.out.println(iterable_element);
//		}
//	}
}
