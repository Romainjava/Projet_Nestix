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
			String query = "SELECT nom_artiste,prenom_artiste,surnom_artiste,etat_artiste,dob_artiste FROM nestix_artiste LIMIT ?";
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, limite);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Artiste artiste = new Artiste();
				artiste.setNom_artiste(result.getString("nom_artiste"));
				artiste.setPrenom_artiste(result.getString("surnom_artiste"));
				artiste.setSurnom_artiste(result.getString("surnom_artiste"));
				artiste.setEtat(result.getString("etat_artiste"));
				artiste.setDob_artiste(result.getString("dob_artiste"));
				
				artistes.add(artiste);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(artistes);
		return artistes;
	}
}
