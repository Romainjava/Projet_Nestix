package requete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.Artiste;
import modele.ConnexionBDD;
import modele.Metier;


public class M_artiste_metier_media {

	public static ArrayList<Metier> readAllJobsForOneArtiste(Artiste artiste) {
		ArrayList<Metier> metiers = new ArrayList<>();
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = "SELECT nom_oeuvre, nom_metier,type_media FROM nestix_artiste_metier_media "
					+ "JOIN nestix_media ON nestix_artiste_metier_media.media_id = nestix_media.id_media "
					+ "JOIN nestix_oeuvre ON nestix_media.oeuvre_id = nestix_oeuvre.id_oeuvre "
					+ "JOIN nestix_metier ON nestix_metier.id_metier = nestix_artiste_metier_media.metier_id "
					+ "WHERE nestix_artiste_metier_media.artiste_id = ?";
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, artiste.getId());
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Metier metier = new Metier();
				metier.setNom(result.getString("nom_metier"));
				metier.addMedia(result.getString("nom_oeuvre"), result.getString("type_media"));
				metiers.add(metier);
			}

		} catch (SQLException e) {

			System.out.println("Erreur dans M_artiste_metier_media lireUn :" + e.getMessage());
		}
		
		return metiers;

	}

	public static void creation() {
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = "INSERT INTO ";

		} catch (Exception e) {
			System.out.println("Erreurdans M_artiste_metier_media creation : " + e.getMessage());
		}
	}
}
