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
			String query = "SELECT nom_oeuvre, nom_metier,type_media, artiste_id, media_id, metier_id FROM nestix_artiste_metier_media "
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
				metier.getArtiste().setId_artiste(result.getInt("artiste_id"));
				metier.setId(result.getInt("metier_id"));
				metiers.add(metier);
			}

		} catch (SQLException e) {

			System.out.println("Erreur dans M_artiste_metier_media lireUn :" + e.getMessage());
		}

		return metiers;

	}

	public static boolean creation(Metier metier) {
		int nb_row = 0;
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = "INSERT INTO nestix_artiste_metier_media(artiste_id, metier_id, media_id) VALUES(?,?,?) ";
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, metier.getArtiste().getId());
			statement.setInt(2, metier.getId());
			System.out.println(metier.getMedia());

			statement.setInt(3, metier.getMedia().getId_media());
			nb_row = statement.executeUpdate();

		} catch (Exception e) {
			System.out.println("Erreur dans M_artiste_metier_media creation : " + e.getMessage());
			e.printStackTrace();
		}
		return (nb_row > 0);
	}

	public static boolean suppression(Metier metier) {
		int nb_row = 0;
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = "DELETE FROM nestix_artiste_metier_media WHERE artiste_id = ? AND media_id = ? AND metier_id = ?";
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, metier.getArtiste().getId());
			statement.setInt(2, metier.getMedia().getId());
			statement.setInt(3, metier.getId());
			nb_row = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erreur dans M_artiste_metier_media suppression : " + e.getMessage());
			e.printStackTrace();
		}
		return (nb_row > 0);
	}
}
