package requete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modele.ConnexionBDD;

public class M_media {

	/**
	 * Retourne l'id de media ou le crée grâce au trigger dans la table Media par
	 * rapport à son type
	 * 
	 * @param titre:String
	 * @param type:String
	 * @return id_media
	 */
	public static int fetchId(String titre, String type) {
		int id = 0;
		int nb_row = 0;
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = "SELECT id_media FROM nestix_media "
					+ "JOIN nestix_oeuvre ON nestix_media.oeuvre_id = nestix_oeuvre.id_oeuvre "
					+ "WHERE nom_oeuvre = ? AND type_media = ?";
			PreparedStatement state = (PreparedStatement) co.prepareStatement(query);
			state.setString(1, titre);
			state.setString(2, type);
			ResultSet result = state.executeQuery();
			if (result.next()) {
				id = result.getInt(1);
			}
			if (id == 0) {
				int id_oeuvre = fetchIdOeuvre(titre);
				query = "INSERT INTO nestix_media(oeuvre_id,type_media,admin_id) VALUES(?,?,?)";
				state = (PreparedStatement) co.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				state.setInt(1, id_oeuvre);
				state.setString(2, type);
				state.setInt(3, 2); // admin id Romain
				nb_row = state.executeUpdate();

				if (nb_row == 0) {
					throw new SQLException("Creation de media échoué, aucune ligne affecté");
				}
				try (ResultSet last_id = state.getGeneratedKeys()) {
					if (last_id.next()) {
						id = last_id.getInt(1);
					} else {
						throw new SQLException("Creation de Media échoué, ID non trouvé");
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Erreur dans M_media fetchId " + e.getMessage());
		}
		return id;
	}

	/**
	 * Recupere l'id de la table oeuvre ou la crée
	 * 
	 * @param titre:String
	 * @return id_oeuvre
	 */
	public static int fetchIdOeuvre(String titre) {
		int id = 0;
		int nb_row = 0;
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = "SELECT id_oeuvre FROM nestix_oeuvre " + "WHERE nom_oeuvre = ? ";
			PreparedStatement state = (PreparedStatement) co.prepareStatement(query);
			state.setString(1, titre);
			ResultSet result = state.executeQuery();
			if (result.next()) {
				id = result.getInt(1);
			}
			if (id == 0) {
				query = "INSERT INTO nestix_oeuvre(nom_oeuvre) VALUES(?)";
				state = (PreparedStatement) co.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				state.setString(1, titre);
				nb_row = state.executeUpdate();

				if (nb_row == 0) {
					throw new SQLException("Creation de l'oeuvre échoué, aucune ligne affecté");
				}
				try (ResultSet last_id = state.getGeneratedKeys()) {
					if (last_id.next()) {
						id = last_id.getInt(1);
					}
				} catch (Exception e) {
					System.out.println("Erreur dans le fetchIdOeuvre, ID introuvable : " + e.getMessage());
				}
			}
		} catch (Exception e) {
			System.out.println("Erreur dans M_media fetchIdOeuvre " + e.getMessage());
		}
		return id;
	}

}
