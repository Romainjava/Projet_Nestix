package modele;

//-- imports sql
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//-- imports util
import java.util.ArrayList;

//-- imports interne requete
import requete.MessageSqlState;


/**
 * Modèle de Musique à l'immage de la table musique de la BDD.
 * Contiens également les méthodes utiles permettant le traitement des données.
 *
 * @author Kévin
 */
public class Musique extends Media {

	//-- Attributs --\\
	//--
	private Album album = new Album();

	//-- Colonnes de nestix_musique
	private int duree_musique;


	//-- Assesseurs --\\
	//--
	public int getDuree_musique() {
		return duree_musique;
	}
	public void setDuree_musique(int duree_musique) {
		this.duree_musique = duree_musique;
	}


	/**
	 * - Renvoie la ligne d'entête du tableau de l'aside panel.
	 * @return tableau contenant les noms des collones.
	 */
	@Override
	public String[] toHeaderData() {
		return new String[]{ "Titre", "Genre", "Interprete", "Etat", "Date de sortie" };
	}


	/**
	 * - Renvoie une lignes du tableau de l'aside panel.
	 * @return tableau contenant (nom_oeuvre, nom_genre(concat), nom_artist(concat), nom_etat, annee_sortie_media).
	 */
	@Override
	public String[] toRowData() {
		return new String[]{ this.oeuvre.getNom(), this.concat_genre, this.concat_artistes, this.etat.getNom(),
				this.annee_sortie_media.substring(0, 4) };
	}

	/**
	 * ???
	 * @return
	 */
	public String[] toRowDataForm() {
		return new String[]{ this.getTitre(), this.duree_musique + "", this.getTitreAlbum(), this.getNomunivers(),
				this.annee_sortie_media.substring(0, 4) };
	}


	/**
	 * - Requette la BDD pour récupérer les genres d'une musique.
	 * Supprime les genres existant dans l'objet Genre.
	 * Ajouter les nouveaux genres récupérés dans l'objet Genre.
	 *
	 * @param id L'id du media pour lequel on récupère les genres.
	 */
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


	/**
	 * - Requette la BDD pour récupérer les artistes d'une musique.
	 * Supprime les artistes existant dans l'objet Artiste.
	 * Ajouter les nouveaux artistes récupérés dans l'objet Artiste.
	 *
	 * @param id L'id du media pour lequel on récupère les artistes.
	 */
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
	 * ???
	 * update la table nestix_musique aprés la creation d'une musique pour set la
	 * duree et l'album si il y'en a un
	 * 
	 * @return boolean
	 */
	public boolean updateDureeAlbum() {
		boolean success = false;
		try {
			String query = "UPDATE `nestix_musique` SET `duree_musique`=?,`album_id`=? WHERE musique_id=?";
			PreparedStatement statement = (PreparedStatement) ConnexionBDD.getConnexion().prepareStatement(query);
			ConnexionBDD.prepareInt(statement, 1, this.duree_musique);
			ConnexionBDD.prepareInt(statement, 2, this.album.getId());
			statement.setInt(3, this.id_media);
			success = (statement.executeUpdate() > 0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}


	/**
	 * ???
	 * @return
	 */
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
				query = "INSERT INTO `nestix_musique`(`musique_id`, `duree_musique`, `album_id`) VALUES(?,?,?)";
			}
			statement.close();
		} catch (SQLException e) {
			MessageSqlState.message(e.getErrorCode());
			e.printStackTrace();
		}
		return success;
	}


	/**
	 * - Modifie une musique dans la table media de la bdd à l'image du modele Musique.
	 * Si c'est réussi modifie également la table musique de la BDD.
	 * @return la réussite ou non de l'éxécution de la requette.
	 */
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


	/**
	 * - Permet de récupérer une musique dans la BDD en fonction de l'id.
	 * @param id L'id de la musique à chercher.
	 * @return la réussite ou non de l'éxécution de la requette.
	 */
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


	/**
	 * - Permet de supprimer de la BDD une musique dans la BDD en fonction de l'id.
	 * @param id L'id de la musique à supprimer.
	 * @return la réussite ou non de l'éxécution de la requette.
	 */
	@Override
	public boolean suppression(int id) {
		boolean success = false;
		try {
			String query = "DELETE FROM `nestix_media` WHERE id_media=?";
			PreparedStatement statement = (PreparedStatement) ConnexionBDD.getConnexion().prepareStatement(query);
			statement.setInt(1, this.id_media);
			success = (statement.executeUpdate() > 0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}


	/**
	 * - Permet de récupérer plusieurs musique dans la BDD.
	 * @param limit Le nombre de musique à récupérer.
	 * @return une arraylist d'objet Musique contenant les musiques récupérées.
	 */
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


	/**
	 * - Entre dans l'objet Album l'id et le nom de l'album récupéré en BDD.
	 * @param result resultat de la requette SQL SELECT.
	 */
	private void setAlbum(ResultSet result) {
		try {
			this.album.setId(result.getInt("id_album"));
			this.album.setNom(result.getString("nom_album"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * - Entre dans l'objet Album un id à 0 et le nom de l'album passé en param.
	 * @param nom nom de l'album recherché.
	 */
	public void setAlbum(String nom) {
		this.album.setId(0);
		this.album.setInfo(nom);
	}


	/**
	 * ???
	 * @param limit
	 * @return
	 */
	@Override
	public boolean recherchePar(int limit) {
		// TODO Auto-generated method stub
		return false;
	}


	/**
	 * - Surcharge du toString avec durée musique et nom album.
	 * @return toString modifié.
	 */
	@Override
	public String toString() {
		return "Musiques [duree_musique=" + duree_musique + ", nom_album=" + album.toString() + super.toString() + "]";
	}


	/**
	 * - récupère le titre de l'album dans l'objet Album.
	 * @return le nomm de l'album.
	 */
	private String getTitreAlbum() {
		return this.album.getNom();
	}


	/**
	 * - Surcharge de getType, précise que le type est une musique.
	 * @return musique.
	 */
	@Override
	protected String getType() {
		return "musique";
	}


	/**
	 * - Classe interne static.
	 * Contiens les requettes SQL à utiliser pour les musiques.
	 */
	static class Query {

		/**
		 * - Récupère toutes les infos d'une musique.
		 * @return la requette.
		 */
		static String queryLectureUn() {
			return "SELECT  id_media, annee_sortie_media, admin_id, nestix_media.univers_id,  nom_univers, saga_id, duree_musique,"
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

		/**
		 * - Requette SQL pour récupérer toutes les musiques avec toutes leurs infos.
		 * @return la requette.
		 */
		static String queryLectureTout() {
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


		/**
		 * - Récupère les genres d'un media.
		 * @return la requette.
		 */
		static String queryFetchGenre() {
			return "SELECT    genre_id,nom_genre FROM    nestix_media_genre "
					+ "JOIN nestix_genre ON nestix_media_genre.genre_id = nestix_genre.id_genre WHERE "
					+ "    media_id = ?";
		}


		/**
		 * - Récupère les artistes d'un media.
		 * @return la requette.
		 */
		static String queryFetchArtiste() {
			return "SELECT    artiste_id, surnom_artiste " + "FROM    nestix_artiste "
					+ "JOIN nestix_artiste_metier_media ON nestix_artiste_metier_media.artiste_id = nestix_artiste.id_artiste "
					+ "WHERE   media_id = ?";
		}

		public static String queryLectureUn() {
			return "SELECT  id_media, annee_sortie_media, admin_id, nestix_media.univers_id,  nom_univers, saga_id, duree_musique,"
					+ "    nom_saga,    image_id,    path_image,    nom_image, "
					+ "    alt_image,  id_album, nom_album,  utilisateur_id,    nom_oeuvre,    id_etat, "
					+ "    nom_etat,    oeuvre_id FROM    `nestix_media` "
					+ "LEFT JOIN nestix_oeuvre ON nestix_oeuvre.id_oeuvre = nestix_media.oeuvre_id "
					+ "LEFT JOIN nestix_musique ON nestix_musique.musique_id = nestix_media.id_media "
					+ "LEFT JOIN nestix_album ON nestix_album.id_album = nestix_musique.album_id  "
					+ "LEFT JOIN nestix_etat ON nestix_media.etat_id = nestix_etat.id_etat "
					+ "LEFT JOIN nestix_univers ON nestix_univers.id_univers = nestix_media.univers_id "
					+ "LEFT JOIN nestix_saga ON nestix_saga.id_saga = nestix_media.saga_id "
					+ "LEFT JOIN nestix_image ON nestix_image.id_image=nestix_media.image_id "
					+ " WHERE   id_media = ?";

		}
	}
}
