package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class Musiques extends Media {

	private int duree_musique;
	private String nom_album;

	public int getDuree_musique() {
		return duree_musique;
	}

	public void setDuree_musique(int duree_musique) {
		this.duree_musique = duree_musique;
	}

	public String getAlbum_musique() {
		return nom_album;
	}

	public void setAlbum_musique(String album_musique) {
		this.nom_album = album_musique;
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
		Date date=new Date();
		int id_oeuvre=GlobalRequete.creaRapide("id_oeuvre", "nestix_oeuvre","nom_oeuvre", this.titre_media);
		if(this.nom_album!=null) {
			int id_album=GlobalRequete.creaRapide("id_album", "nestix_album","nom_album", this.nom_album);
		}
		
		try {
			String query = "INSERT INTO nestix_media(date_crea_media,annee_sortie_media,admin_id,univers_id,image_id,saga_id,etat_id,oeuvre_id) VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement statement = (PreparedStatement) ConnexionBDD.startConnection().prepareStatement(query);
			statement.setDate(1, (java.sql.Date)date);
			statement.setString(2, this.annee_sortie_media);
			statement.setInt(3, 3);
			statement.setString(3, this.univers_media);
			
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

	public static void main(String[] args) {
		Musiques musique= new Musiques();
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
//		System.out.println(GlobalRequete.creaRapide("id_oeuvre", "nestix_oeuvre","nom_oeuvre", "pour que tu m'aime encore"));
	}
}
