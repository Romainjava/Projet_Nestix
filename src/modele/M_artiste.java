package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M_artiste {

	public static ArrayList<I_recherche> lireTout(int limite) {
		ArrayList<I_recherche> artistes = new ArrayList<>();
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = "SELECT nom_artiste,prenom_artiste,surnom_artiste,nom_etat,dob_artiste FROM nestix_artiste JOIN nestix_etat "
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
				
				artistes.add(artiste);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(artistes);
		return artistes;
	}
	
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
			
			while(result.next()) {
				id = result.getInt(1);
				System.out.println("Creation de artiste numero "+ id);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
}
