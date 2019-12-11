package modele;

import java.sql.Connection;
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
			PreparedStatement statement = (PreparedStatement) ConnexionBDD.getConnexion().prepareStatement(query);
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
	public boolean modification() {
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
		ArrayList<I_recherche> musiqueList = new ArrayList<>();
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = "SELECT\r\n" + 
					"    duree_musique,\r\n" + 
					"    annee_sortie_media,\r\n" + 
					"    nom_oeuvre,\r\n" + 
					"    GROUP_CONCAT(DISTINCT surnom_artiste)AS surnom_artiste,\r\n" + 
					"    GROUP_CONCAT(nom_genre)AS nom_genre,\r\n" + 
					"    id_genre,\r\n" + 
					"	 nom_genre,\r\n"+
					"    nom_etat\r\n" + 
					"FROM\r\n" + 
					"    `nestix_musique`\r\n" + 
					"JOIN nestix_media ON nestix_media.id_media = media_id\r\n" + 
					"JOIN nestix_oeuvre ON nestix_oeuvre.id_oeuvre = nestix_media.id_media\r\n" + 
					"JOIN nestix_artiste_metier_media ON nestix_artiste_metier_media.media_id = nestix_media.id_media\r\n" + 
					"JOIN nestix_artiste ON nestix_artiste.id_artiste = nestix_artiste_metier_media.artiste_id\r\n" + 
					"JOIN nestix_media_genre ON nestix_media.id_media = nestix_media_genre.media_id\r\n" + 
					"JOIN nestix_genre ON nestix_media_genre.genre_id = nestix_genre.id_genre\r\n" + 
					"JOIN nestix_etat ON nestix_media.etat_id=nestix_etat.id_etat\r\n" + 
					"GROUP BY\r\n" + 
					"    nestix_media.id_media LIMIT ?";
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, limit);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Musiques musique=new Musiques();
				musique.concat_genre=result.getString("nom_genre");
				musique.setTitre_media(result.getString("nom_oeuvre"));
				musique.concat_artistes=result.getString("surnom_artiste");
				musique.etat.setNom(result.getString("nom_etat"));
				musique.setAnnee_sortie_media(result.getString("annee_sortie_media"));
				musiqueList.add(musique);
			}
			//success = (statement.executeUpdate()>1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return musiqueList;
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
//		genre.setNom("romann");
//		genre.getId();
//		System.out.println(genre);
//		genre.setNom("Roman");
//		genre.modification();
//		System.out.println(genre);
		System.out.println(musique.lectureTout(10).toString());
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
