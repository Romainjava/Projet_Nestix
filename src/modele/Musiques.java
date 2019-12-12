package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.cj.protocol.ResultStreamer;
import com.mysql.cj.xdevapi.Result;

public class Musiques extends Media {

	private int duree_musique;
	private Album album = new Album();

	public int getDuree_musique() {
		return duree_musique;
	}

	public void setDuree_musique(int duree_musique) {
		this.duree_musique = duree_musique;
	}


	@Override
	public String[] toRowData() {
		String[] data = { this.oeuvre.getNom(), this.concat_genre, this.concat_artistes, this.etat.getNom(),
				this.annee_sortie_media };
		return data;
	}

	@Override
	public String[] toHeaderData() {
		String[] data = { "Titre", "Genre", "Interprete", "Etat", "Date de sortie" };
		return data;
	}

	@Override
	public boolean creation() {
		boolean success = false;
		int id_oeuvre = 0;
		id_oeuvre = 0;
		System.out.println(id_oeuvre);
		try {

			String query = "INSERT INTO nestix_media(annee_sortie_media,admin_id,oeuvre_id) VALUES(?,?,?)";
			PreparedStatement statement = (PreparedStatement) ConnexionBDD.getConnexion().prepareStatement(query);
			statement.setString(1, this.annee_sortie_media);
			statement.setInt(2, 4);
			statement.setInt(3, id_oeuvre);
			success = (statement.executeUpdate() > 1);
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

	private void fetchGenre(int id) {
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = "SELECT    genre_id,nom_genre FROM    nestix_media_genre "
					+ "JOIN nestix_genre ON nestix_media_genre.genre_id = nestix_genre.id_genre WHERE "
					+ "    media_id = ?";
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Genre genre = new Genre();
				genre.setInfo(result);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void fetchArtiste(int id) {
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = "SELECT    artiste_id, surnom_artiste "
					+ "FROM    nestix_artiste "
					+ "JOIN nestix_artiste_metier_media ON nestix_artiste_metier_media.artiste_id = nestix_artiste.id_artiste "
					+ "WHERE   media_id = ?";
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Artiste artiste = new Artiste();
				artiste.setSurnom_artiste(result.getString("surnom_artiste"));
				artiste.setId_artiste(result.getInt("artiste_id"));
				artiste.getAllMetierById(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean lireUn(int id) {
		boolean sucess = false;
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = Query.queryLectureUn();
			
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				sucess = true;
				this.id_media = result.getInt("id_media");
				this.annee_sortie_media = result.getString("annee_sortie_media");
				this.duree_musique = result.getInt("duree_musique");
				this.setSaga(result);
				this.setImage(result);
				this.setEtat(result);
				this.setOeuvre(result);
				this.setUnivers(result);
				this.setAlbum(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sucess;
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
			String query = Query.queryLectureTout();

			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, limit);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Musiques musique = new Musiques();
				musique.id_media = result.getInt("id_media");
				musique.concat_genre = result.getString("nom_genre");
				musique.oeuvre.setNom(result.getString("nom_oeuvre"));
				musique.concat_artistes = result.getString("surnom_artiste");
				musique.etat.setNom(result.getString("nom_etat"));
				musique.setAnnee_sortie_media(result.getString("annee_sortie_media"));
				musiqueList.add(musique);
			}
			// success = (statement.executeUpdate()>1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return musiqueList;
	}

	public void setAlbum(ResultSet result) {
		try {
			this.album.setId(result.getInt("id_album"));
			this.album.setNom(result.getString("nom_album"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean recherchePar(int limit) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void main(String[] args) {
		Musiques musique = new Musiques();
//		musique.setoeuvre.getNom()("test");
//		Genre genre = new Genre();
//		genre.setNom("romann");
//		genre.getId();
//		System.out.println(genre);
//		genre.setNom("Roman");
//		genre.modification();
//		System.out.println(genre);
		ArrayList<I_recherche> testArrayList = musique.lectureTout(50);
		System.out.println(musique.lireUn(2) + " " + musique.etat.getNom());
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
//		musique.setoeuvre.getNom()("Agnes veux ca aussoi");
//		musique.setAnnee_sortie_media("2019");
//		musique.saga.setNom_saga("bref");
//		System.out.println(musique.creation());

	}

	@Override
	public String toString() {
		return "Musiques [duree_musique=" + duree_musique + ", nom_album=" + album.toString() + super.toString() + "]";
	}

	static class Query {

		public static String queryLectureTout() {
			return "SELECT	duree_musique,  annee_sortie_media,  "
					+ "		id_media, 	nom_oeuvre, "
					+ "		GROUP_CONCAT(DISTINCT surnom_artiste)AS surnom_artiste,  "
					+ "		GROUP_CONCAT(nom_genre)AS nom_genre, 		id_genre, 	nom_genre, "
					+ "		nom_etat " 
					+ "FROM  `nestix_musique`  "
					+ "LEFT JOIN nestix_media ON nestix_media.id_media = media_id  "
					+ "LEFT JOIN nestix_oeuvre ON nestix_oeuvre.id_oeuvre = nestix_media.id_media "
					+ "LEFT JOIN nestix_artiste_metier_media ON nestix_artiste_metier_media.media_id = nestix_media.id_media "
					+ "LEFT JOIN nestix_artiste ON nestix_artiste.id_artiste = nestix_artiste_metier_media.artiste_id  "
					+ "LEFT JOIN nestix_media_genre ON nestix_media.id_media = nestix_media_genre.media_id "
					+ "LEFT JOIN nestix_genre ON nestix_media_genre.genre_id = nestix_genre.id_genre  "
					+ "LEFT JOIN nestix_etat ON nestix_media.etat_id=nestix_etat.id_etat  "
							+ "GROUP BY  nestix_media.id_media LIMIT ?";
		}

		public static String queryLectureUn() {
			return "SELECT  id_media, annee_sortie_media, admin_id, nestix_media.univers_id,  nom_univers, saga_id, duree_musique"
					+ "    nom_saga,    image_id,    path_image,    nom_image, "
					+ "    alt_image,  id_album, nom_album,  utilisateur_id,    nom_oeuvre,    id_etat, "
					+ "    nom_etat,    oeuvre_id FROM    `nestix_media` "
					+ "LEFT JOIN nestix_oeuvre ON nestix_oeuvre.id_oeuvre = nestix_media.oeuvre_id "
					+ "LEFT JOIN nestix_musique ON nestix_musique.media_id = nestix_media.id_media "
					+ "LEFT JOIN nestix_album ON nestix_album.id_album = nestix_musique.album_id  "
					+ "LEFT JOIN nestix_etat ON nestix_media.etat_id = nestix_etat.id_etat "
					+ "LEFT JOIN nestix_univers ON nestix_univers.id_univers = nestix_media.id_media "
					+ "LEFT JOIN nestix_saga ON nestix_saga.id_saga = nestix_media.saga_id "
					+ "LEFT JOIN nestix_image ON nestix_image.id_image=nestix_media.image_id "
					+ " WHERE   id_media = ?";

		}
	}
}
