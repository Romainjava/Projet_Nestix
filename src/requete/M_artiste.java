package requete;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import modele.Artiste;
import modele.ConnexionBDD;
import modele.I_recherche;
import modele.Metier;

public class M_artiste {
	/**
	 * Recupere un artiste via l'id
	 * 
	 * @param artiste
	 */
	public static boolean lireUn(Artiste artiste) {
		boolean success = false;
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = "SELECT * FROM nestix_artiste WHERE id = ?";
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, artiste.getId_artiste());
			success = statement.execute();

		} catch (SQLException e) {
			System.out.println("Erreur attrapé dans lireUn M_artiste : " + e.getMessage());
		}
		return success;
	}

	/**
	 * Récupere tout les artistes dans une limite donnée et les stock dans un
	 * tableau d'artiste
	 * 
	 * @param limite
	 * @return Tableau d'artiste de type I_recherche
	 */
	public static ArrayList<I_recherche> lireTout(int limite) {
		ArrayList<I_recherche> artistes = new ArrayList<>();
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = "SELECT id_artiste,nom_artiste,prenom_artiste,surnom_artiste,nom_etat,dob_artiste,"
					+ " GROUP_CONCAT(DISTINCT nom_metier) as metiers "
					+ " FROM nestix_artiste "
					+ " LEFT JOIN nestix_etat ON nestix_etat.id_etat = nestix_artiste.etat_id"
					+ " LEFT JOIN nestix_artiste_metier_media ON nestix_artiste_metier_media.artiste_id = nestix_artiste.id_artiste"
					+ " LEFT JOIN nestix_metier ON nestix_metier.id_metier = nestix_artiste_metier_media.metier_id"
					+ " GROUP BY nestix_artiste.id_artiste"
					+ " LIMIT ?";
				    
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, limite);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Artiste artiste = new Artiste();
				artiste.setNom_artiste(result.getString("nom_artiste"));
				artiste.setPrenom_artiste(result.getString("prenom_artiste"));
				artiste.setSurnom_artiste(result.getString("surnom_artiste"));
				artiste.setEtat(result.getString("nom_etat"));
				artiste.setDob_artiste(parseFormatDateFromSQL(result.getString("dob_artiste")));
				artiste.setId_artiste(result.getInt("id_artiste"));
				artiste.setGroup_concact(result.getString("metiers"));
				artistes.add(artiste);
			}

		} catch (SQLException e) {
			System.out.println("Erreur attrapé dans lireTout M_artiste : " + e.getMessage());
		}
		System.out.println(artistes);
		return artistes;
	}

	/**
	 * Crée un artiste via les valeurs des textfield et stock dans l'instance
	 * artiste l'id par rapport à l'id crée en BDD
	 * 
	 * @param artiste
	 * @return id d'un artiste crée.
	 */
	public static boolean creation(Artiste artiste) {
		int nb_row = 0;
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = "INSERT INTO nestix_artiste(nom_artiste,prenom_artiste,surnom_artiste,dob_artiste)"
					+ " VALUES(?,?,?,?)";
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, artiste.getNom_artiste());
			statement.setString(2, artiste.getPrenom_artiste());
			statement.setString(3, artiste.getSurnom_artiste());
			statement.setString(4, parseFormatDateFromForm(artiste.getDob_artiste()));
			nb_row = statement.executeUpdate();
			if (nb_row == 0) {
				throw new SQLException("Creation de l'artiste échoué, aucune ligne affecté");
			}
			try (ResultSet last_insert_id = statement.getGeneratedKeys()) {
				if (last_insert_id.next()) {
					artiste.setId_artiste(last_insert_id.getInt(1));
				} else {
					throw new SQLException("Creation de l'artiste échoué, ID non trouvé");
				}
			}

		} catch (SQLException e) {
			System.out.println("Erreur attrapé dans creation M_artiste : " + e.getMessage());
		}
		return (nb_row > 0);
	}

	/**
	 * crée un artiste avec seulement son surnom et renvoie l'id, si il existe renvoie l'id.
	 * 
	 * @return boolean
	 */
	public static void creationRapide(Artiste artiste) {
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = "SELECT id_artiste FROM nestix_artiste WHERE surnom_artiste = ?";
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setString(1, artiste.getSurnom_artiste());
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				artiste.setId_artiste(result.getInt(1));
			}
			if(artiste.getId_artiste() == 0) {
				creation(artiste);			
			}

		} catch (SQLException e) {
			System.out.println("Erreur attrapé dans creationRapide M_artiste : " + e.getMessage());
		}	
	}

	/**
	 * Modifie l'artiste par rapport à l'id de la ligne du tableau
	 * 
	 * @param artiste
	 * @return nombre de lignes executé
	 */
	public static int modifier(Artiste artiste) {
		int nb_row = 0;
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = "UPDATE nestix_artiste SET nom_artiste = ?, prenom_artiste = ?, surnom_artiste = ?"
					+ ", dob_artiste = ? WHERE id_artiste = ?";
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setString(1, artiste.getNom_artiste());
			statement.setString(2, artiste.getPrenom_artiste());
			statement.setString(3, artiste.getSurnom_artiste());
			statement.setString(4, parseFormatDateFromForm(artiste.getDob_artiste()));
			statement.setInt(5, artiste.getId_artiste());
			nb_row = statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Erreur attrapé dans modifier M_artiste : " + e.getMessage());
		}

		return nb_row;
	}

	/**
	 * Récupère les id des metiers de l'artiste et et le nom du métier et les
	 * affecte dans l'instance Metier
	 * 
	 * @param artiste
	 * @param id
	 */
	public static void getAllMetierById(Artiste artiste, int id) {

		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = "SELECT nom_metier, id_metier FROM nestix_artiste JOIN nestix_artiste_metier_media ON nestix_artiste_metier_media.artiste_id = nestix_artiste.id_artiste"
					+ " JOIN nestix_metier ON nestix_metier.id_metier = nestix_artiste_metier_media.metier_id WHERE media_id= ? AND id_artiste = ?";
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, id);
			statement.setInt(2, artiste.getId());
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Metier metier = new Metier();
				metier.setId(result.getInt("id_metier"));
				metier.setNom(result.getString("nom_metier"));
				artiste.setMetiers_artiste(metier);
			}

		} catch (SQLException e) {
			System.out.println("Erreur attrapé dans getAllMetierById M_artiste : " + e.getMessage());
		}
	}

	/**
	 * supprime un artiste par rapport à son id
	 * 
	 * @param artiste
	 * @return boolean
	 */
	public static boolean supprime(Artiste artiste) {
		int nb_row = 0;
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = "DELETE FROM nestix_artiste WHERE id_artiste = ?";
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, artiste.getId());
			nb_row = statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Erreur attrapé dans supprime M_artiste : " + e.getMessage());
		}

		return (nb_row > 0);
	}

	/**
	 * Convertis le format date SQL en format voulu jj/mm/aaaa FRANCE ou renvoie
	 * null
	 * 
	 * @param date
	 * @return String
	 */
	public static String parseFormatDateFromSQL(String date) {
		String date_string = null;
		SimpleDateFormat formatSQL = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
		SimpleDateFormat formatTab = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
		try {
			if (date != null && !date.equals("")) {
				Date d = (Date) formatSQL.parse(date);
				date_string = formatTab.format(d);
			}
		} catch (ParseException e) {
			System.out.println("Erreur dans la conversion de date dans Artiste setDob_artiste : " + e.getMessage());
		}
		return date_string;
	}

	/**
	 * Convertis le format Date jj/mm/aaaa en yyyy-MM-dd pour le SQL ou renvoie null
	 * 
	 * @param date
	 * @return String
	 */
	public static String parseFormatDateFromForm(String date) {
		String date_string = null;
		SimpleDateFormat formatSQL = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
		SimpleDateFormat formatTab = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
		try {
			if (date != null && !date.equals("")) {
				Date d = (Date) formatTab.parse(date);
				date_string = formatSQL.format(d);
			}
		} catch (ParseException e) {
			System.out.println("Erreur dans la conversion de date dans Artiste setDob_artiste : " + e.getMessage());
		}
		return date_string;
	}

}
