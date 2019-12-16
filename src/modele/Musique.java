package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Musique extends Media {

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
				this.annee_sortie_media.substring(0, 4) };
		return data;
	}

	@Override
	public String[] toHeaderData() {
		String[] data = { "Titre", "Genre", "Interprete", "Etat", "Date de sortie" };
		return data;
	}

	private void fetchGenre(int id) {
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = Query.queryFetchGenre();
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			this.getGenres().clear();
			while (result.next()) {
				Genre genre = new Genre();
				genre.setInfo(result);
				this.addGenre(genre);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void fetchArtiste(int id) {
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = Query.queryFetchArtiste();
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			this.getArtistes().clear();
			while (result.next()) {
				Artiste artiste = new Artiste();
				artiste.setSurnom_artiste(result.getString("surnom_artiste"));
				artiste.setId_artiste(result.getInt("artiste_id"));
				artiste.getAllMetierById(id);
				this.addArtiste(artiste);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * update la table nestix_musique aprés la creation d'une musique pour set la duree et l'album si il y'en a un
	 * @return boolean
	 */
	public boolean updateDureeAlbum() {
		boolean success = false;
		System.out.println(this.album.getId());
		try {
			String query="UPDATE `nestix_musique` SET `duree_musique`=?,`album_id`=? WHERE musique_id=?";
			PreparedStatement statement = (PreparedStatement) ConnexionBDD.getConnexion().prepareStatement(query);
			ConnexionBDD.prepareInt(statement, 1, this.duree_musique);
			ConnexionBDD.prepareInt(statement, 2, this.album.getId());
			statement.setInt(3, this.id_media);
			success=(statement.executeUpdate()>0);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return success;
	}
	
	@Override
	public boolean creation() {
		boolean success = false;
		try {
			String query = "INSERT INTO `nestix_media`( `annee_sortie_media`, `admin_id`, `univers_id`, `image_id`,`etat_id`, `oeuvre_id`,type_media) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement statement = (PreparedStatement) ConnexionBDD.getConnexion().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, this.annee_sortie_media);
			statement.setInt(2, 4);
			ConnexionBDD.prepareInt(statement, 3, this.univers.getId());
			ConnexionBDD.prepareInt(statement, 4, this.image.getId());
			statement.setInt(5, (this.etat.getId() == 0) ? 2 : this.etat.getId());
			ConnexionBDD.prepareInt(statement, 6, this.oeuvre.getId());
			statement.setString(7, this.getType());
			success = (statement.executeUpdate() > 0);
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				success = true;
				this.id_media = (int) generatedKeys.getLong(1);
			} else {
				throw new SQLException("Creating music failed, no ID obtained.");
			}
			if (success) {
				query="INSERT INTO `nestix_musique`(`musique_id`, `duree_musique`, `album_id`) VALUES(?,?,?)";
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean modification() {
		boolean success = false;
		try {
			String query = "UPDATE `nestix_media` SET `annee_sortie_media`=?,`admin_id`=?,`univers_id`=?,`image_id`=?,`etat_id`=?,`oeuvre_id`=?"
					+ " WHERE id_media=?";
			PreparedStatement statement = (PreparedStatement) ConnexionBDD.getConnexion().prepareStatement(query);
			statement.setString(1, this.annee_sortie_media);
			statement.setInt(2, 4);
			ConnexionBDD.prepareInt(statement, 3, this.univers.getId());
			ConnexionBDD.prepareInt(statement, 4, this.image.getId());
			statement.setInt(5, (this.etat.getId() == 0) ? 2 : this.etat.getId());
			ConnexionBDD.prepareInt(statement, 6, this.oeuvre.getId());
			statement.setInt(7, this.id_media);
			success = (statement.executeUpdate() > 0);
			if (success) {
				query = "UPDATE `nestix_musique` SET `duree_musique`=?,`album_id`=? WHERE musique_id=?";
				statement = (PreparedStatement) ConnexionBDD.getConnexion().prepareStatement(query);
				ConnexionBDD.prepareInt(statement, 1, this.duree_musique);
				ConnexionBDD.prepareInt(statement, 2, this.album.getId());
				statement.setInt(3, this.id_media);
				success = (statement.executeUpdate() > 0);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
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
				this.fetchArtiste(id);
				this.fetchGenre(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sucess;
	}

	@Override
	public boolean suppression(int id) {
		System.out.println(this.artistes.toString());
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
				Musique musique = new Musique();
				musique.id_media = result.getInt("musique_id");
				musique.concat_genre = result.getString("nom_genre");
				musique.oeuvre.setNom(result.getString("nom_oeuvre"));
				musique.concat_artistes = result.getString("surnom_artiste");
				musique.etat.setNom(result.getString("nom_etat"));
				musique.setAnnee_sortie_media(result.getString("annee_sortie_media"));
				musiqueList.add(musique);
			}
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

	public void setAlbum(String nom) {
		this.album.setId(0);
		this.album.setInfo(nom);
	}

	@Override
	public boolean recherchePar(int limit) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return "Musiques [duree_musique=" + duree_musique + ", nom_album=" + album.toString() + super.toString() + "]";
	}

	public String getTitreAlbum() {
		return this.album.getNom();
	}

	public String[] toRowDataForm() {
		String[] data = { this.getTitre(), this.duree_musique + "", this.getTitreAlbum(), this.getNomunivers(),
				this.annee_sortie_media.substring(0, 4) };
		return data;
	}

	static class Query {

		public static String queryLectureTout() {
			return "SELECT	duree_musique,  annee_sortie_media,  musique_id, 	nom_oeuvre, "
					+ "		GROUP_CONCAT(DISTINCT surnom_artiste)AS surnom_artiste,  "
					+ "		GROUP_CONCAT(DISTINCT nom_genre)AS nom_genre, id_genre, 	nom_etat "
					+ "FROM  `nestix_musique`  " + "LEFT JOIN nestix_media ON nestix_media.id_media = musique_id  "
					+ "LEFT JOIN nestix_oeuvre ON nestix_oeuvre.id_oeuvre = nestix_media.oeuvre_id "
					+ "LEFT JOIN nestix_artiste_metier_media ON nestix_artiste_metier_media.media_id = nestix_media.id_media "
					+ "LEFT JOIN nestix_artiste ON nestix_artiste.id_artiste = nestix_artiste_metier_media.artiste_id  "
					+ "LEFT JOIN nestix_media_genre ON nestix_media.id_media = nestix_media_genre.media_id "
					+ "LEFT JOIN nestix_genre ON nestix_media_genre.genre_id = nestix_genre.id_genre  "
					+ "LEFT JOIN nestix_etat ON nestix_media.etat_id=nestix_etat.id_etat  "
					+ "GROUP BY  nestix_media.id_media LIMIT ?";
		}

		public static String queryFetchGenre() {
			return "SELECT    genre_id,nom_genre FROM    nestix_media_genre "
					+ "JOIN nestix_genre ON nestix_media_genre.genre_id = nestix_genre.id_genre WHERE "
					+ "    media_id = ?";
		}

		public static String queryFetchArtiste() {
			return "SELECT    artiste_id, surnom_artiste " + "FROM    nestix_artiste "
					+ "JOIN nestix_artiste_metier_media ON nestix_artiste_metier_media.artiste_id = nestix_artiste.id_artiste "
					+ "WHERE   media_id = ?";
		}

		public static String queryLectureUn() {
			return "SELECT  id_media, annee_sortie_media, admin_id, nestix_media.univers_id,  nom_univers, saga_id, duree_musique"
					+ "    nom_saga,    image_id,    path_image,    nom_image, "
					+ "    alt_image,  id_album, nom_album,  utilisateur_id,    nom_oeuvre,    id_etat, "
					+ "    nom_etat,    oeuvre_id FROM    `nestix_media` "
					+ "LEFT JOIN nestix_oeuvre ON nestix_oeuvre.id_oeuvre = nestix_media.oeuvre_id "
					+ "LEFT JOIN nestix_musique ON nestix_musique.musique_id = nestix_media.id_media "
					+ "LEFT JOIN nestix_album ON nestix_album.id_album = nestix_musique.album_id  "
					+ "LEFT JOIN nestix_etat ON nestix_media.etat_id = nestix_etat.id_etat "
					+ "LEFT JOIN nestix_univers ON nestix_univers.id_univers = nestix_media.id_media "
					+ "LEFT JOIN nestix_saga ON nestix_saga.id_saga = nestix_media.saga_id "
					+ "LEFT JOIN nestix_image ON nestix_image.id_image=nestix_media.image_id "
					+ " WHERE   id_media = ?";

		}
	}

	@Override
	protected String getType() {
		return "Musique";
	}

}
