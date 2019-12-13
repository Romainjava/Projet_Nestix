package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M_artiste {
	/**
	 * Recupere un artiste via l'id
	 * 
	 * @param artiste
	 */
	public static void lireUn(Artiste artiste) {
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = "SELECT * FROM nestix_artiste WHERE id = ?";
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, artiste.getId_artiste());
			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
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
			String query = "SELECT id_artiste,nom_artiste,prenom_artiste,surnom_artiste,nom_etat,dob_artiste FROM nestix_artiste JOIN nestix_etat "
					+ "ON nestix_etat.id_etat = nestix_artiste.etat_id LIMIT ?";
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, limite);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Artiste artiste = new Artiste();
				artiste.setNom_artiste(result.getString("nom_artiste"));
				artiste.setPrenom_artiste(result.getString("prenom_artiste"));
				artiste.setSurnom_artiste(result.getString("surnom_artiste"));
				artiste.setEtat(result.getString("nom_etat"));
				artiste.setDob_artiste(result.getString("dob_artiste"));
				artiste.setId_artiste(result.getInt("id_artiste"));
				artistes.add(artiste);
			}

		} catch (SQLException e) {
			e.printStackTrace();
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
	public static int creation(Artiste artiste) {
		int id = 0;
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = "INSERT INTO nestix_artiste(nom_artiste,prenom_artiste,surnom_artiste,dob_artiste)"
					+ " VALUES(?,?,?,?)";
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setString(1, artiste.getNom_artiste());
			statement.setString(2, artiste.getPrenom_artiste());
			statement.setString(3, artiste.getSurnom_artiste());
			statement.setString(4, artiste.getDob_artiste());
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				id = result.getInt(1);
				artiste.setId_artiste(id);
				System.out.println("Creation de artiste numero " + id);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
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
			statement.setString(4, artiste.getDob_artiste());
			statement.setInt(5, artiste.getId_artiste());
			nb_row = statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
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
					+ " JOIN nestix_metier ON nestix_metier.id_metier = nestix_artiste_metier_media.metier_id WHERE media_id= ?";
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Metier metier = new Metier();
				metier.setId(result.getInt("id_metier"));
				metier.setNom(result.getString("nom_metier"));
				artiste.setMetiers_artiste(metier);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void supprime(Artiste artiste) {
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = "DELETE FROM nestix_artiste WHERE id = ?";
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
