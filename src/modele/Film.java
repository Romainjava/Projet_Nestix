package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		return new String[]{ this.oeuvre.getNom(), this.concat_genre, this.concat_artistes, this.annee_sortie_media.substring(0,4),
				 this.etat.getNom()};
	}

	@Override
	public String[] toHeaderData() {
		return new String[]{ "Titre", "Genre", "realisateur", "Date de sortie", "Etat" };
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

	@Override
	public boolean creation() {
		boolean success = false;
		try {
			String query = "INSERT INTO `nestix_media`( `annee_sortie_media`, `admin_id`, `univers_id`, `image_id`,`etat_id`, `oeuvre_id`,type_media) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement statement = (PreparedStatement) ConnexionBDD.getConnexion().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, this.annee_sortie_media);
			statement.setInt(2, 1);
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
				throw new SQLException("Creating film failed, no ID obtained.");
			}
			for (Artiste artiste : this.artistes) {
				query = "INSERT INTO `nestix_artiste_metier_media`(`artiste_id`, `media_id`, `metier_id`) VALUES(?,?,?)";
				statement = (PreparedStatement) ConnexionBDD.getConnexion().prepareStatement(query);
				statement.setInt(1, artiste.getId());
				statement.setInt(2, this.id_media);
				statement.setInt(3, artiste.getMetiers_artiste().get(0).getId());
				success = (statement.executeUpdate() > 0);
			}
			for (Genre genre : this.genres) {
				query = "INSERT INTO `nestix_media_genre` (`media_id`, `genre_id`) VALUES(?,?)";
				statement = (PreparedStatement) ConnexionBDD.getConnexion().prepareStatement(query);
				statement.setInt(1, this.id_media);
				statement.setInt(2, genre.getId());
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
				this.duree_film = result.getInt("duree_film");
				this.setSaga(result);
				this.setImage(result);
				this.setEtat(result);
				this.setOeuvre(result);
				this.setUnivers(result);
				this.resume_film = result.getString("resume_film");
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<I_recherche> lectureTout(int limit) {
		ArrayList<I_recherche> filmList = new ArrayList<>();
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = Query.queryLectureTout();
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, limit);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Film film = new Film();
				film.id_media = result.getInt("film_id");
				film.concat_genre = result.getString("nom_genre");
				film.oeuvre.setNom(result.getString("nom_oeuvre"));
				film.concat_artistes = result.getString("surnom_artiste");
				film.etat.setNom(result.getString("nom_etat"));
				film.setAnnee_sortie_media(result.getString("annee_sortie_media"));
				filmList.add(film);
			}
			// success = (statement.executeUpdate()>1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filmList;
	}
	
	@Override
	public boolean recherchePar(int limit) {
		// TODO Auto-generated method stub
		return false;
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
				query = "UPDATE `nestix_film` SET `duree_film`=?,`resume_film`=? WHERE film_id=?";
				statement = (PreparedStatement) ConnexionBDD.getConnexion().prepareStatement(query);
				ConnexionBDD.prepareInt(statement, 1, this.duree_film);
				statement.setString(2, this.resume_film);
				statement.setInt(3, this.id_media);
				success = (statement.executeUpdate() > 0);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}
	
	public String[] toRowDataForm() {
		return new String[]{ this.getTitre(), this.duree_film + "", this.annee_sortie_media.substring(0,4), this.saga.getNom(),
				this.getNomunivers() };
	}

	static class Query {

		static String queryLectureTout() {
			return "SELECT	duree_film, resume_film,  annee_sortie_media,  film_id, 	nom_oeuvre, "
					+ "		GROUP_CONCAT(DISTINCT surnom_artiste)AS surnom_artiste,  "
					+ "		GROUP_CONCAT(DISTINCT nom_genre)AS nom_genre, id_genre, 	nom_etat "
					+ "FROM  `nestix_film`  " 
					+ "LEFT JOIN nestix_media ON nestix_media.id_media = film_id  "
					+ "LEFT JOIN nestix_oeuvre ON nestix_oeuvre.id_oeuvre = nestix_media.oeuvre_id "
					+ "LEFT JOIN nestix_artiste_metier_media ON nestix_artiste_metier_media.media_id = nestix_media.id_media "
					+ "LEFT JOIN nestix_artiste ON nestix_artiste.id_artiste = nestix_artiste_metier_media.artiste_id  "
					+ "LEFT JOIN nestix_media_genre ON nestix_media.id_media = nestix_media_genre.media_id "
					+ "LEFT JOIN nestix_genre ON nestix_media_genre.genre_id = nestix_genre.id_genre  "
					+ "LEFT JOIN nestix_etat ON nestix_media.etat_id=nestix_etat.id_etat  "
					+ "GROUP BY  nestix_media.id_media LIMIT ?";
		}

		static String queryFetchGenre() {
			return "SELECT    genre_id,nom_genre FROM    nestix_media_genre "
					+ "JOIN nestix_genre ON nestix_media_genre.genre_id = nestix_genre.id_genre WHERE "
					+ "    media_id = ?";
		}

		static String queryFetchArtiste() {
			return "SELECT    artiste_id, surnom_artiste " + "FROM    nestix_artiste "
					+ "JOIN nestix_artiste_metier_media ON nestix_artiste_metier_media.artiste_id = nestix_artiste.id_artiste "
					+ "WHERE   media_id = ?";
		}

		static String queryLectureUn() {
			return "SELECT  id_media, annee_sortie_media, admin_id, nestix_media.univers_id,  nom_univers, saga_id, duree_film, resume_film,"
					+ "    nom_saga,    image_id,    path_image,    nom_image, "
					+ "    alt_image,  utilisateur_id,    nom_oeuvre,    id_etat, "
					+ "    nom_etat,    oeuvre_id FROM    `nestix_media` "
					+ "LEFT JOIN nestix_oeuvre ON nestix_oeuvre.id_oeuvre = nestix_media.oeuvre_id "
					+ "LEFT JOIN nestix_film ON nestix_film.film_id = nestix_media.id_media "
					+ "LEFT JOIN nestix_etat ON nestix_media.etat_id = nestix_etat.id_etat "
					+ "LEFT JOIN nestix_univers ON nestix_univers.id_univers = nestix_media.univers_id "
					+ "LEFT JOIN nestix_saga ON nestix_saga.id_saga = nestix_media.saga_id "
					+ "LEFT JOIN nestix_image ON nestix_image.id_image=nestix_media.image_id "
					+ " WHERE   id_media = ?";

		}
	}
	
	@Override
	protected String getType() {
		
		return "Film";
	}

}