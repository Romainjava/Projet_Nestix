package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		boolean success=false;
		int id_oeuvre=0;
			id_oeuvre=0;
		 System.out.println(id_oeuvre);
		try {
			String query = "INSERT INTO nestix_media(annee_sortie_media,admin_id,oeuvre_id) VALUES(?,?,?)";
			PreparedStatement statement = (PreparedStatement) ConnexionBDD.startConnection().prepareStatement(query);
			statement.setString(1, this.annee_sortie_media);
			statement.setInt(2, 4);
			statement.setInt(3, id_oeuvre);
			success = (statement.executeUpdate()>1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
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
		Genre genre = new Genre();
		genre.setNom("Roman");
		genre.creation();
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
//		musique.addGenre(genre);
//		musique.setTitre_media("Agnes veux ca aussoi");
//		musique.setAnnee_sortie_media("2019");
//		musique.saga.setNom_saga("bref");
//		System.out.println(musique.creation());
		
	}
}
